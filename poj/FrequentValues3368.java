package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;

public class FrequentValues3368 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new FrequentValues3368().solve();
    }

    int MAXN = 100005;
    int N, Q;
    int[] arr = new int[MAXN];
    int[] right = new int[MAXN];
    int[] nodes = new int[MAXN<<2];
    private void solve() throws IOException {
        while (true){
            N = read();
            if (N == 0){
                break;
            }
            Q = read();
            int pre = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                int a = read();
                if (a == pre){
                    arr[i] = arr[i-1]+1;
                }else {
                    arr[i] = 1;
                }
                pre = a;
            }
            for (int i = N; i > 0; i--) {
                int r = i;
                while (arr[i]!=1) {
                    right[i--] = r;
                }
                right[i] = r;
            }
            initLineTree();
            for (int i = 0; i < Q; i++) {
                int l = read();
                int r = read();
                if (right[l] >= r){
                    out.println(r- l + 1);
                } else {
                    out.println(max(right[l] - l + 1, query(right[l]+1, r)));
                }
            }
        }
        out.flush();
    }

    private int query(int l, int r) {
        return query(l, r, 1, 1, N);
    }

    private int query(int s, int t, int i, int l, int r) {
        int res = 0;
        if (s<=l && t>=r){
            return nodes[i];
        } else {
            int mid = (l + r) >> 1;
            if (s <= mid) {
                res = max(query(s, t, i << 1, l, mid), res);
            }
            if (t > mid){
                res = max(query(s, t, i << 1|1, mid+1, r), res);
            }
        }
        return res;
    }


    private void initLineTree() {
        Arrays.fill(nodes, 0);
        build(1,1,N);
    }

    private void build(int i, int s, int t) {
        if (s == t){
            nodes[i] = arr[s];
        } else {
            build(i<<1, s, (s + t)>>1);
            build(i<<1|1, ((s + t)>>1)+1, t);
            up(i);
        }
    }

    private void up(int i) {
        nodes[i] = max(nodes[i<<1], nodes[i<<1|1]);
    }
}
