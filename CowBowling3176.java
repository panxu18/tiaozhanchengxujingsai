package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CowBowling3176 {

    public static void main(String[] args) throws IOException {
        new CowBowling3176().solve();
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

    int N;
    int[][] dp = new int[2][400];

    void solve() throws IOException {
        N = readInt();
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                int cur = readInt();
                dp[i&1][j] = cur + Math.max(dp[(i + 1)&1][j], dp[(i + 1)&1][j - 1]);
                ans = Math.max(ans, dp[i&1][j]);
            }
        }
        out.println(ans);
        out.flush();
    }
}
