package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class HiewiththePie3311 {

    public static void main(String[] args) throws IOException {
        new HiewiththePie3311().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }
    long readLong() throws IOException {
        if (canRead()) {
            return Long.parseLong(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    char readChar() throws IOException {
        if (canRead()) {
            return st.nextToken().charAt(0);
        }
        throw new NoSuchElementException();
    }

    boolean canRead() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = in.readLine();
            if (s != null) {
                st = new StringTokenizer(s, " ");
            } else {
                return false;
            }
        }
        return true;
    }

    int MAXN = 15;
    int INF = 1000000007;
    int N;
    int[][] map = new int[MAXN][MAXN];
    private void solve() throws IOException {
        while (true){
            N = readInt();
            if (N == 0) {
                break;
            }
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    map[i][j] = readInt();
                }
            }
            floyd();
            computeDP();
            out.println(dp[(1<<N+1) - 1][0]);
        }


        out.flush();
    }

    int[][] dp = new int[1<<MAXN][MAXN];
    // 枚举所有可能情况
    private void computeDP() {
        for (int i = 0; i < 1<<N+1; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for (int j = 0; j < 1 << (N+1); j++) {
            for (int i = 0; i <= N; i++) {
                if ((j >> i & 1) == 1 || i == 0) {
                    // 当前在i点
                    for (int k = 0; k < N + 1; k++) {
                        if ((j >> k & 1) == 0){
                            // 从i点到k点
                            dp[j|(1<<k)][k] = min(dp[j|(1<<k)][k], dp[j][i] + map[i][k]);
                        }
                    }
                }
            }
        }

//        for (int i = 0; i <= N; i++) {
//            dp[1<<i][i] = map[0][i];
//        }
//        int k = 1;
//        while (k <= N) {
//            int comb = (1<<k)-1;
//            while (comb < 1<<N+1) {
//                for (int i = 0; i < N + 1; i++) {
//                    if ((comb >> i & 1) == 1) {
//                        for (int j = 0; j < N + 1; j++) {
//                            if ((comb >> j & 1) == 0) {
//                                dp[comb|(1<<j)][j] = min(dp[comb|(1<<j)][j], dp[comb][i] + map[i][j]);
//                            }
//                        }
//                    }
//                }
//
//                comb = nextSub(comb);
//            }
//            k++;
//        }


    }

    int nextSub(int comb) {
        int x = comb & -comb; // 低位1
        int y = x + comb; //  进位
        comb = ((comb & ~y)/x)>>1; // 低位
        return comb | y;
    }

    private void floyd() {
        for (int k = 0; k <= N; k++) {
            for (int i = 0; i <=N ; i++) {
                for (int j = 0; j <=N ; j++) {
                    map[i][j] = min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }
}
