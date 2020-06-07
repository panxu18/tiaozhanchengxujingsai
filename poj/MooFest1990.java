package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class MooFest1990 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new MooFest1990().solve();
    }

    int MAXN = 20005;
    int INF = 1000000007;
    long ans = 0;
    int N ;
    long[] arr = new long[MAXN];
    Pair[] pairs = new Pair[MAXN];
    private void solve() throws IOException {
        N = read();
        for (int i = 0; i < N; i++) {
            pairs[i] = new Pair(read(), read());
        }
        Arrays.sort(pairs, 0, N);
        init();
        for (int i = 0; i < N; i++) {
            long lcnt = sum(bit1, pairs[i].x);
            long rcnt = sum(bit1, MAXN) - lcnt;
            long lsum = sum(bit2, pairs[i].x);
            long rsum = sum(bit2, MAXN) - lsum;
            ans += pairs[i].v * (lcnt*pairs[i].x - lsum + rsum - rcnt*pairs[i].x);
            add(bit1,pairs[i].x, 1);
            add(bit2,pairs[i].x, pairs[i].x);
        }
        out.println(ans);
        out.flush();
    }

    int[] bit1 = new int[MAXN+1];
    int[] bit2 = new int[MAXN+1];
    private void init(){
        Arrays.fill(bit1,0);
        Arrays.fill(bit2,0);
    }
    private long sum(int[] bit, int i){
        long res = 0;
        while (i > 0){
            res = res + bit[i];
            i -= i&-i;
        }
        return res;
    }

    private void add(int[] bit, int i, int a){
        while (i<=MAXN){
            bit[i] += a;
            i += i&-i;
        }
    }

    class Pair implements Comparable<Pair>{
        int v,x;

        public Pair(int v, int x) {
            this.v = v;
            this.x = x;
        }

        @Override
        public int compareTo(Pair o) {
            return v - o.v;
        }
    }
}
