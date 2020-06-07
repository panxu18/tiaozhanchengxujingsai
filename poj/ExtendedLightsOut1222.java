package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class ExtendedLightsOut1222 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new ExtendedLightsOut1222().solve();
    }

    int MAXN = 6;
    int INF = 1000000007;
    int ans = INF;
    int N = 20;
    boolean[][] sta  = new boolean[MAXN][MAXN];
    boolean[][] flips  = new boolean[MAXN][MAXN];
    private void solve() throws IOException {
        N = read();
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 6; k++) {
                    sta[j][k] = read() == 0;
                }
            }
            doSolve();
            out.printf("PUZZLE #%d\n",i);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 6; k++) {
                    if (k + 1 < 6){
                        out.printf("%d ", flips[j][k]?1:0);
                    } else {
                        out.printf("%d\n", flips[j][k]?1:0);
                    }
                }
            }
        }
        out.flush();
    }

    private void doSolve() {

        out:
        for (int s = 0; s < 1 << 6; s++) {
            for (int i = 0; i < MAXN; i++) {
                Arrays.fill(flips[i], false);
            }
            for (int i = 0; i < 6; i++) {
                if ((s>>i & 1) == 1){
                    flips[0][6-i-1] = true;
                }
            }
            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!checkSta(i-1, j)) {
                        flips[i][j] = true;
                    }
                }
            }
            for (int i = 0; i < 6; i++) {
                if (!checkSta(4, i)){
                    continue out;
                }
            }
            return;
        }
    }

    int[][] dir = {{0,-1},{0,1},{0,0},{-1,0},{1,0}};
    private boolean checkSta(int x, int y) {
        boolean s = sta[x][y];
        for (int i = 0; i < dir.length; i++) {
            int nx = dir[i][0] + x;
            int ny = dir[i][1] + y;
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 6){
                s^=flips[nx][ny];
            }
        }
        return s;
    }
}
