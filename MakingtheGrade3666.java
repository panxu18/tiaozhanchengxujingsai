package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MakingtheGrade3666 {

    public static void main(String[] args) throws IOException {
        new MakingtheGrade3666().solve();
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
    int[] arr = new int[2005];
    int[] b = new int[2005];
    int[][] dp = new int[2][2005];
    int ans = Integer.MAX_VALUE;
    private void solve() throws IOException {
        N = readInt();
        for (int i = 1; i <= N; i++) {
            arr[i] = readInt();
        }
        computeDP();
        reverse(arr, 1, N + 1);
        computeDP();
        out.println(ans);
        out.flush();
    }

    private void computeDP() {
        System.arraycopy(arr, 1, b, 0, N);
        int k = unique(b, 0, N);
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1], 0);
        for (int i = 0; i < N; i++) {
            int min = dp[i&1][0];
            for (int j = 0; j < k; j++) {
                min = Math.min(min, dp[i&1][j]);
                dp[(i+1)&1][j] = Math.abs(arr[i + 1] - b[j]) + min;
            }
        }
        for (int i = 0; i < k; i++) {
            ans = Math.min(dp[N&1][i], ans);
        }
    }

    private void reverse(int[] list, int s, int t) {
        t -= 1;
        while (s < t) {
            int temp = list[s];
            list[s++] = list[t];
            list[t--] = temp;
        }
    }

    private int unique(int[] list, int s, int t){
        Arrays.sort(list, s, t);
        int k = s;
        for (int i = s + 1; i < t; i++) {
            if (list[i] != list[k]) {
                list[++k] = list[i];
            }
        }
        return k + 1;
    }
}
