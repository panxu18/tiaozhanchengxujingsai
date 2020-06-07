package tiaozhanchengxujingsai.poj;

import java.io.*;

public class CornFields3254 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new CornFields3254().solve();
    }

    int MAXN = 12;
    int N, M;
    boolean[][] map = new boolean[MAXN][MAXN];
    int[][] dp = new int[2][1<<MAXN];
    int ans = 0;
    int mod = 100000000;
    private void solve() throws IOException {
        M = read();
        N = read();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = read() == 1;
            }
        }
        doSolve();
        out.println(ans);
        out.flush();
    }

    private void doSolve() {

        int cnt = 0;
        dp[cnt++][0] = 1;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++, cnt++) {
                for (int s = 0; s < 1 << N; s++) {
                    if ((s>>j & 1) == 1){
                        if (!map[i][j] || j>0 && (s>>j-1&1) == 1) {
                            dp[cnt&1][s] = 0;
                        } else {
                            dp[cnt&1][s] = dp[(cnt+1)&1][1<<j^s];
                        }
                    } else {
                        dp[cnt&1][s] = (dp[(cnt+1)&1][s] + dp[(cnt+1)&1][1<<j|s])%mod;
                    }
                }
            }
        }
        for (int s = 0; s < 1 << N; s++) {
            ans = (ans + dp[(cnt-1)&1][s]) % mod;
        }
    }
}
