package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Scanner;

public class CharmBracelet3624 {

    public static void main(String[] args) {
        new CharmBracelet3624().solve();
    }

    int n, m;
    int[] w = new int[4000];
    int[] d = new int[4000];
    int[] dp = new int[15000];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        m = in.nextInt();
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
            d[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= 0; j--) {
                if (j >= w[i])
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + d[i]);
            }
        }

        out.println(dp[m]);
        out.flush();
    }

}
