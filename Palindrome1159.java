package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Palindrome1159 {
    public static void main(String[] args) throws IOException {
        new Palindrome1159().solve();
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

    int N;
    char[] arr = new char[5005];
    int[][] dp = new int[2][5005];
    void solve() throws IOException {
        N = readInt();
        String s = readLine();
        s.getChars(0, N, arr, 0);
        LPS();
        out.println(N - dp[0][N - 1]);
        out.flush();
    }


    private void LPS() {
        for (int i = N - 1; i >= 0 ; i--) {
            dp[i&1][i] = 1;
            for (int j = i + 1; j < N; j++) {
                if (arr[i] == arr[j]) {
                    dp[i&1][j] = dp[(i+1)&1][j-1] + 2;
                } else {
                    dp[i&1][j] = Math.max(dp[i&1][j-1], dp[(i+1)&1][j]);
                }
            }
        }
    }
}
