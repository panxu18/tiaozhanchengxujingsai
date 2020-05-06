package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SpaceElevator2392 {

    public static void main(String[] args) throws IOException {
        new SpaceElevator2392().solve();
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

    int K;
    int[] dp = new int[40005];
    Block[] blocks = new Block[405];
    int ans = 0;
    private void solve() throws IOException {
        K = readInt();
        for (int i = 0; i < K; i++) {
            blocks[i] = new Block(readInt(), readInt(), readInt());
        }
        computDP();
        out.println(ans);
        out.flush();
    }

    private void computDP() {
        Arrays.sort(blocks, 0, K);
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j <= blocks[i].a; j++) {
                if (dp[j] >= 0) {
                    dp[j] = blocks[i].c;
                } else if (j >= blocks[i].h && dp[j - blocks[i].h] > 0) {
                    dp[j] = dp[j - blocks[i].h] - 1;
                } else {
                    dp[j] = -1;
                }
            }
        }
        for (int i = 0; i < 40005; i++) {
            if (dp[i] >= 0) {
                ans = i;
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
