package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AppleCatching2385 {

    public static void main(String[] args) throws IOException {
        new AppleCatching2385().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Integer.parseInt(st.nextToken());
    }

    String readLine() throws IOException {
        return in.readLine();
    }

    int T, M;
    int[][] dp = new int[1005][2];
    int[] apples = new int[1005];
    int[][] sum = new int[2][1005];
    void solve() throws IOException {
        T = readInt();
        M = readInt();
        for (int i = 0; i < T; i++) {
            if (readInt() == 1)
                apples[i] = 1;
        }
        int s = 0;
        for (int i = T - 1; i >= 0 ; i--) {
            s += apples[i];
            sum[0][i] = s;
            sum[1][i] = T - i - s;
        }
//        computeDP();
        fastDP();
        out.println(dp[0][0]);
        out.flush();
    }

    private void computeDP() {
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j < T; j++) {
                for (int k = j; k <= T ; k++) {
                    dp[j][0] = Math.max(dp[k][1] + sum[0][j] - sum[0][k], dp[j][0]);
                    dp[j][1] = Math.max(dp[k][0] + sum[1][j] - sum[1][k], dp[j][1]);
                }
            }
        }
    }

    private void fastDP() {
        for (int i = 0; i <= M; i++) {
            for (int j = T - 1; j >= 0; j--) {
                int temp = dp[j][0];
                dp[j][0] = sum[0][j] + Math.max(dp[j + 1][0] - sum[0][j + 1], dp[j][1] - sum[0][j]);
                dp[j][1] = sum[1][j] + Math.max(dp[j + 1][1] - sum[1][j + 1], temp - sum[1][j]);
            }
        }
    }
}
