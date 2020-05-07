package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CowExhibition2184 {
    public static void main(String[] args) throws IOException {
        new CowExhibition2184().solve();
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
    int[] dp = new int[200005];
    int[] sarr = new int[105];
    int[] farr = new int[105];
    int mid = 100000;
    int maxn = 200005;
    int IFN = 1000000007;
    int ans = 0;
    private void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            sarr[i] = readInt();
            farr[i] = readInt();
        }
        computDP();
        out.println(ans);
        out.flush();
    }

    private void computDP() {
        Arrays.fill(dp, -IFN);
        dp[mid] = 0;
        for (int i = 0; i < N; i++) {
            if (sarr[i] > 0) {
                for (int j = maxn - 1; j >= sarr[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - sarr[i]] + farr[i]);
                }
            } else {
                for (int j = 0; j < maxn + sarr[i]; j++) {
                    dp[j] = Math.max(dp[j], dp[j - sarr[i]] + farr[i]);
                }
            }

        }
        for (int i = mid; i < maxn; i++) {
            if (dp[i] > 0) {
                ans = Math.max(ans, dp[i] + i - mid);
            }
        }

    }

    class Block implements Comparable<Block>{
        int h,a,c;
        Block(int h, int a, int c){
            this.h = h;
            this.a = a;
            this.c = c;
        }

        @Override
        public int compareTo(Block o) {
            return a - o.a;
        }
    }
}
