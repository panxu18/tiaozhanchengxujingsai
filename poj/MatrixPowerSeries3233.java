package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class MatrixPowerSeries3233 {
    public static void main(String[] args) throws IOException {
        new MatrixPowerSeries3233().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 40;
    int MAXM = 50;
    int INF = 1000000007;
    int N, K, MOD;
    int[][] A = new int[MAXN][MAXN];
    int[][] B = new int[MAXN<<1][MAXN<<1];
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        K = (int) readDouble();
        MOD = (int) readDouble();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = (int) readDouble();

            }
        }
        doSovle();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j < N-1) {
                    out.printf("%d ", B[i][j]);
                } else {
                    out.printf("%d\n", B[i][j]);
                }
            }
        }
        out.flush();

    }

    int[][] a0 = new int[MAXN<<1][MAXN<<1];
    private void doSovle() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                B[i][j] = B[i][j+N] = A[i][j];
            }
        }
        for (int i = N; i < N<<1; i++) {
            B[i][i] = 1;
        }
        pow(B,N<<1, K-1);
        for (int i = 0; i < N; i++) {
            System.arraycopy(A[i], 0, a0[i], 0, N);
            a0[i+N][i] = 1;

        }
        mul(B,a0, N<<1,N<<1, N);
    }

    int[][] temp = new int[MAXN<<1][MAXN<<1];;
    private void pow(int[][] a, int n, int b) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(temp[i], 0);
            temp[i][i] = 1;
        }
        while (b > 0) {
            if ((b&1)==1){
                mul(temp, a, n,n,n);
            }
            mul(a, a, n,n,n);
            b>>=1;
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(temp[i], 0, a[i], 0, n);
        }
    }

    int[][] tempmul = new int[MAXN<<1][MAXN<<1];
    private void mul(int[][] a, int[][] b, int n, int q, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int aij = 0;
                for (int k = 0; k < q; k++) {
                    aij = (aij + a[i][k] * b[k][j] % MOD) % MOD;
                }
                tempmul[i][j] = aij;
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(tempmul[i], 0, a[i], 0, m);
        }
    }
}
