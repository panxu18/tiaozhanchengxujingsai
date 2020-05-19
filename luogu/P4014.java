package tiaozhanchengxujingsai.luogu;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;

public class P4014 {

    public static void main(String[] args) throws IOException {
        new P4014().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    String readString() throws IOException {
        in.nextToken();
        return in.sval;
    }

    int MAXN = 105;
    int INF = 1000000007;
    int T, N, M;
    long ans;
    long[][] map = new long[MAXN][MAXN];
    private void solve() throws IOException {
        N = (int) readDouble();
        for (int i = 0; i < MAXN; i++) {
            Arrays.fill(map[i], INF);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = (long)-readDouble();
            }
        }
        km();
        out.println(-ans);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = -map[i][j];
            }
        }
        km();
        out.println(ans);

        out.flush();

    }

    int[] match = new int[MAXN];
    boolean[] visy = new boolean[MAXN];
    long[] lablex = new long[MAXN];
    long[] labley = new long[MAXN];
    long[] slack = new long[MAXN];
    int[] pre = new int[MAXN];
    void km(){
        Arrays.fill(match, -1);
        Arrays.fill(labley, 0);
        for (int i = 0; i < N; i++) {
            lablex[i] = -INF;
            for (int j = 0; j < N; j++) {
                lablex[i] = max(lablex[i], map[i][j]);
            }
        }
        for (int i = 0; i < N; i++) {
            bfs(i);
        }
        ans  = 0;
        for (int i = 0; i < N; i++) {
            if (match[i] >= 0){
                ans += map[match[i]][i];
            }
        }
    }


    private void bfs(int x){
        Arrays.fill(slack, INF);
        Arrays.fill(visy, false);

        long d;
        int y = N; // 临时使用
        match[y] = x;
        int ny = N;
        while (x!=-1){
            // 找x的匹配
            d = INF;
            visy[y] = true;
            for (int i = 0; i < N; i++) {
                if (!visy[i] && map[x][i] != INF){
                    if (lablex[x] + labley[i] - map[x][i] < slack[i]) {
                        slack[i] = lablex[x] + labley[i] - map[x][i];
                        pre[i] = y;
                    }
                    if (slack[i] < d) {
                        d = slack[i];
                        ny = i;
                    }
                }
            }
            for (int i = 0; i <= N; i++) {
                if (visy[i]){
                    lablex[match[i]] -= d;
                    labley[i] += d;
                } else {
                    slack[i] -= d;
                }
            }
            y = ny;
            x = match[y];
        }
        while (y<N){
            match[y] = match[pre[y]];
            y = pre[y];
        }
    }
}
