package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Scanner;

public class Sumsets2229 {

    public static void main(String[] args) {
        new Sumsets2229().solve();
    }

    int n;
    int[] dp = new int[1000005];
    int mod = 1000000000;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if ((i & 1) == 0) {
                dp[i] = (dp[i - 1] + dp[i / 2]) % mod;
            } else {
                dp[i] = dp[i - 1];
            }
        }

        out.println(dp[n] % mod);
        out.flush();
    }
}
