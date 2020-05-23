package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class DroppingTests2976 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int readInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new DroppingTests2976().solve();
    }

    int N, K, MAXN = 1005;
    double W;
    int INF = 1000000007;
    Pair[] pairs = new Pair[MAXN];
    long[] arrA = new long[MAXN];
    long[] arrB = new long[MAXN];

    private void solve() throws IOException {
        while (true){
            N = readInt();
            K = readInt();
            if (N == 0 && K == 0){
                break;
            }
            for (int i = 0; i < N; i++) {
                arrA[i] = (long)readInt();
            }
            for (int i = 0; i < N; i++) {
                arrB[i] = (long)readInt();
            }
            for (int i = 0; i < N; i++) {
                pairs[i] = new Pair(arrA[i], arrB[i]);
            }

            double lb = 0, ub = 1;
            for (int i = 0; i < 20; i++) {
                double mid = (lb + ub) / 2;
                if (check(mid)){
                    lb = mid;
                } else {
                    ub = mid;
                }
            }

            out.printf("%.0f\n",100*ub);
        }
        out.flush();
    }


    private boolean check(double mid) {
        W = mid;
        Arrays.sort(pairs, 0, N, comparator);
        double sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += pairs[i].a - W * pairs[i].b;
        }
        return sum>=0;
    }

    Comparator<Pair> comparator = new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            double a = o1.a - W*o1.b;
            double b = o2.a - W*o2.b;
            return a > b ? -1 : (a == b ? 0 : 1);
        }
    };

    class Pair{
        long a, b;

        public Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }

    }
}
