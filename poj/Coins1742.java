package tiaozhanchengxujingsai.poj;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Coins1742 {

    public static void main(String[] args) {
        new Coins1742().solve();
    }

    int[] dp = new int[100010];
    int n,m;
    int[] as = new int[110];
    int[] cs = new int[110];
    boolean[] used = new boolean[100010];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (in.hasNext()) {
            n = in.nextInt();
            m = in.nextInt();
            if (n == 0 && m == 0) break;
            for (int i = 0; i < n; i++) {
                as[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                cs[i] = in.nextInt();
            }
            out.println(getAns());
        }
        out.flush();
    }

    private int getAns(){
        Arrays.fill(used, false);
        Arrays.fill(dp,-1);
        dp[0] = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[j] >= 0) {
                    dp[j] = cs[i];
                    used[j] = true;
                } else if (j >= as[i] && dp[j - as[i]] > 0) {
                    dp[j] = dp[j - as[i]] - 1;
                    used[j] = true;
                } else {
                    dp[j] = -1;
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            if (used[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
