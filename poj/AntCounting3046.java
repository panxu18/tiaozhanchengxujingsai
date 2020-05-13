package tiaozhanchengxujingsai.poj;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class AntCounting3046 {
    public static void main(String[] args) {
        new AntCounting3046().solve();
    }

    int mod = 1000000;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        T = in.nextInt();
        A = in.nextInt();
        S = in.nextInt();
        B = in.nextInt();
        for (int i = 0; i < A; i++) {
            arr[in.nextInt()]++;
        }
        out.println(getAns());
        out.flush();
    }

    int T, A, S, B;
    int[] arr = new int[1005];
    int[][] dp = new int[2][100005];
    int getAns(){
        Arrays.fill(dp[0], 0); // 边界
        for (int i = 1; i <= T; i++) {
            dp[i&1][0] = 1; // 取0个物品方案数为1
            for (int j = 1; j <= B; j++) {
                if (j >= arr[i] + 1) {
                    dp[i&1][j] = dp[(i+1)&1][j] + dp[i&1][j - 1] - dp[(i+1)&1][j - 1 - arr[i]] + mod; // 防止出现负数
                } else {
                    dp[i&1][j] = dp[(i+1)&1][j] + dp[i&1][j - 1];
                }
                dp[i&1][j] = dp[i&1][j] % mod;
            }
        }
        int ans = 0;
        for (int i = S; i <= B; i++) {
            ans = (dp[T&1][i] + ans) % mod;
        }
        return ans;
    }
}
