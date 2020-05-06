package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DollarDayz3181 {
    public static void main(String[] args) throws IOException {
        new DollarDayz3181().solve();
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
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), "");
        }

        return st.nextToken();
    }

    char readChar() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken().charAt(0);
    }

    int N, K;
    BigInteger[][] dp = new BigInteger[2][1005];
    BigInteger one = BigInteger.ONE;
    BigInteger zore = BigInteger.ZERO;
    BigInteger ans = zore;
    void solve() throws IOException {
        N = readInt();
        K = readInt();
        computDP();
        out.println(ans);
        out.flush();
    }

    void computDP() {
        Arrays.fill(dp[0], zore);
        dp[0][0] = one;
        for (int k = 1; k <= K; k++) {
            dp[k&1][0] = BigInteger.valueOf(1);
            for (int i = 1; i <= N; i++) {
                if (i >= k) {
                    dp[k&1][i] = dp[k&1][i - k].add(dp[(k+1)&1][i]);
                } else {
                    dp[k&1][i] = dp[(k+1)&1][i];
                }

            }
        }
        ans = dp[K&1][N];
    }
}
