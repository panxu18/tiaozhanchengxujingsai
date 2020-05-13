package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WoodenSticks1065 {

    public static void main(String[] args) throws IOException {
        new WoodenSticks1065().solve();
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

    int T, N;
    Stick[] sticks = new Stick[5005];
    int[] dp = new int[5005];
    int ans;
    void solve() throws IOException {
        T = readInt();
        for (int i = 0; i < T; i++) {
            N = readInt();
            for (int j = 0; j < N; j++) {
                sticks[j] = new Stick(readInt(), readInt());
            }
            LIS();
            out.println(ans);
        }
        out.flush();
    }

    void LIS() {
        Arrays.sort(sticks, 0, N);
        int len = 0;
        for (int i = 0; i < N; i++) {
            // 取反转化为最大上升子序列
            int idx = Arrays.binarySearch(dp, 0, len, -sticks[i].w);
            if (idx < 0) {
                int j = -idx - 1;
                dp[j] = -sticks[i].w;
                len = Math.max(len, j + 1);
            }
        }
        ans = len;
    }
    class Stick implements Comparable<Stick>{
        int h,w;

        Stick(int h, int w){
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Stick o) {
            return h - o.h;
        }
    }
}
