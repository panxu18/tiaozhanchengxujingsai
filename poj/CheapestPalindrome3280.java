package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CheapestPalindrome3280 {

    public static void main(String[] args) throws IOException {
        new CheapestPalindrome3280().solve();
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

    int N, M;
    char[] arr = new char[2005];
    int[][] dp = new int[2][2005];
    int[] cost = new int[28];
    void solve() throws IOException {
        N = readInt();
        M = readInt();
        String s = readLine();
        s.getChars(0, M, arr, 0);
        for (int i = 0; i < N; i++) {
            char c = readChar();
            int a = readInt();
            int d = readInt();
            cost[c-'a'] = Math.min(a, d);
        }
        for (int i = M - 1; i >= 0; i--) {
            dp[i&1][i] = 0;
            for (int j = i + 1; j < M; j++) {
                if (arr[i] == arr[j]){
                    dp[i&1][j] = dp[(i+1)&1][j-1];
                } else {
                    dp[i&1][j] = Math.min(dp[(i+1)&1][j] + cost[arr[i]-'a'], dp[i&1][j-1] + cost[arr[j]-'a']);
                }
            }
        }
        out.println(dp[0][M-1]);
        out.flush();
    }
}
