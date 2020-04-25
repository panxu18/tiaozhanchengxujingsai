package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Coins1742 {

    public static void main(String[] args) {
        new Coins1742().solve();
    }

    int[][] dp = new int[2][100010];
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

    private int getAns() {
        Arrays.fill(used, false);
        Arrays.fill(dp[1], -1); // 边界条件
        dp[1][0] = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[(i + 1) & 1][j] >= 0) {
                    dp[i & 1][j] = cs[i];
                } else if (j >= as[i] && dp[i & 1][j - as[i]] > 0) {
                    dp[i & 1][j] = dp[i & 1][j - as[i]] - 1;
                } else {
                    dp[i & 1][j] = -1;
                }
                if (dp[i & 1][j] >= 0 && used[j] == false) {
                    used[j] = true;
                    cnt++;
                }
            }
        }
        return cnt - 1;
    }
}
