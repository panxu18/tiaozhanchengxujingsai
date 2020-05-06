package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bridgingsignals1631 {

    public static void main(String[] args) throws IOException {
        new Bridgingsignals1631().solve();
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

    int N, P;
    int[] ports = new int[40005];
    int[] dp = new int[40005];
    int ans;
    void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            P = readInt();
            for (int j = 0; j < P; j++) {
                ports[j] = readInt();
            }
            LIS();
            out.println(ans);
        }
        out.flush();
    }

    void LIS() {
        int len = 0;
        for (int i = 0; i < P; i++) {
            int idx = Arrays.binarySearch(dp, 0, len, ports[i]);
            if (idx < 0) {
                int j = -idx - 1;
                dp[j] = ports[i];
                if (j == len){
                    len++;
                }
            }
        }
        ans = len;
    }
}
