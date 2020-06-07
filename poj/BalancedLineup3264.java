package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class BalancedLineup3264 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new BalancedLineup3264().solve();
    }

    int MAXN = 50005;
    int N, Q;
    int ansMax, ansMin;
    int[] height = new int[MAXN];
    private void solve() throws IOException {
        N = read();
        Q = read();
        for (int i = 1; i <= N; i++) {
            height[i] = read();
        }
        initLineTree();
        for (int i = 0; i < Q; i++) {
            int l = read();
            int r = read();
            query(l,r);
        }
        out.flush();
    }

    int[][] node = new int[MAXN<<2][2];
    private void initLineTree() {
        Arrays.fill(node[0], 0);
        Arrays.fill(node[1], 0);
        build(1,1,N);
    }

    private void query(int s, int t) {
        ansMax = Integer.MIN_VALUE;
        ansMin = Integer.MAX_VALUE;
        query(s, t, 1, 1, N);
        out.println(ansMax-ansMin);
    }

    private void query(int s, int t, int i, int l, int r) {
        if (s <= l && t >= r){
            ansMin = min(ansMin, node[i][0]);
            ansMax = max(ansMax, node[i][1]);
        } else {
            int mid = (l + r)>>1;
            if (s <= mid) {
                query(s, t, i<<1, l, mid);
            }
            if (t > mid){
                query(s, t, i<<1|1, mid + 1, r);
            }
        }

    }

    private void build(int i, int l, int r) {
        if (l == r){
            node[i][0] = node[i][1] = height[l];
            return;
        }
        build(i<<1, l, (l + r)>>1);
        build(i<<1|1, ((l + r)>>1) + 1, r);
        up(i);

    }

    private void up(int i) {
        node[i][0] = min(node[i<<1][0], node[i<<1|1][0]);
        node[i][1] = max(node[i<<1][1], node[i<<1|1][1]);
    }
}
