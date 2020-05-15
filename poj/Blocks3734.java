package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class Blocks3734 {

    public static void main(String[] args) throws IOException {
        new Blocks3734().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 15;
    int MAXM = 50;
    int INF = 1000000007;
    int T, N;
    double[] tikets = new double[MAXN];
    long ans;
    long mod = 10007;
    private void solve() throws IOException {
        T = (int) readDouble();
        for (int t = 0; t < T; t++) {
            N = (int) readDouble();
            doSolve();
            out.println(res[0][0]);
        }
        out.flush();

    }

    private void doSolve() {
        long[][] a = {{2, 1, 0},{2,2,2},{0,1,2}};
        powMatricMod(a, N, res,3, mod);
        matricmMul(res, A, res, 3,3, 1, mod);
    }
    long[][] res = new long[MAXN][MAXN];
    long[][] A = {{1},{0},{0}};
    long[][] temp = new long[MAXN][MAXN];

    void matricmMul(long[][] a, long[][] b, long[][] result, int n, int q, int m, long mod) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long aij = 0;
                for (int k = 0; k < q; k++) {
                    aij = (aij + (a[i][k] * b[k][j])%mod)%mod;
                }
                temp[i][j] = aij;
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(temp[i], 0, result[i], 0, m);
        }
    }

    void powMatricMod(long[][] a, int b, long[][] result, int n, long mod) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(result[i], 0);
            result[i][i] = 1;
        }
        while (b > 0) {
            if ((b&1) == 1) {
                matricmMul(result, a, result, n, n, n, mod);
            }
            matricmMul(a, a, a,n, n, n, mod);
            b>>=1;
        }
    }
}
