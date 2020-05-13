package tiaozhanchengxujingsai.poj;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CommonSubsequence1458 {

    public static void main(String[] args) {
        new CommonSubsequence1458().solve();
    }

    int[][] dp = new int[2][1000];
    int n,m;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (in.hasNext()) {
            String s1 = in.next();
            String s2 = in.next();
            n = s1.length();
            m = s2.length();
            out.println(lcs(s1, s2));
        }
        out.flush();
    }

    private int lcs(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.fill(dp[0], 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr1[i - 1] == arr2[j - 1])
                    dp[i&1][j] = dp[(i+ 1) & 1][j - 1] + 1;
                else
                    dp[i&1][j] = Math.max(dp[(i+ 1) & 1][j], dp[i&1][j - 1]);
            }
        }
        return dp[n&1][m];
    }
}
