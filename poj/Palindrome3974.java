package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Palindrome3974 {

    public static void main(String[] args) {
        new Palindrome3974().solve();
    }

    void solve() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String str = null;
        try {
            for (int i = 1 ; !"END".equals(str = in.readLine()); i++) {
                out.printf("Case %d: %d\n", i, getParlidrome(str));
            }
        } catch (IOException e) {
            return;
        }
        out.flush();
    }
    char[] arr = new char[2000010];
    int[] dp = new int[2000010];
    int len;
    private int getParlidrome(String str) {
        len = str.length();
        int k = 0;
        // 转换字符串
        str.getChars(0, str.length(), arr, 0);
        for (int i = len - 1; i >= 0 ; i--) {
            arr[2 * i] = '#';
            arr[2 * i + 1] = str.charAt(i);
        }
        arr[len * 2] = '#';
        manacher(arr, dp, 2 * len + 1);
        int ans = 0;
        for (int i = 0; i < 2 * len + 1 ; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans - 1;
    }

    void manacher(char[] arr, int[] dp, int n) {
        int p = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int j = 2 * p - i; // 对称点
            int h = j < 0 ? 0 : dp[j]; // 对称点半长
            if (h < dp[p] + p - i) { // 情况1
                dp[i] = h;
            } else { // 情况2/3
                h = Math.max(0, dp[p] + p - i);
                while (i + h < n && i >= h && arr[i + h] == arr[i - h]) h++;
                dp[i] = h;
                p = i;
            }
        }
    }
}
