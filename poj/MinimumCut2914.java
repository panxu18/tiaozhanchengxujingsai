package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

public class MinimumCut2914 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new MinimumCut2914().solve();
    }

    int MAXN = 505;
    int N, M;
    int[][] grap = new int[MAXN][MAXN];
    int[] w = new int[MAXN];
    int[] v = new int[MAXN];
    boolean[] vis = new boolean[MAXN];
    private void solve() throws IOException {
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            N = (int) in.nval;
            M = read();
            for (int i = 0; i < MAXN; i++) {
                Arrays.fill(grap[i], 0);
            }
            for (int i = 0; i < M; i++) {
                int u = read();
                int v = read();
                int d = read();
                grap[u][v] += d;
                grap[v][u] += d;
            }
            out.println(stoerWagner(N));
        }
        out.flush();
    }

    private int stoerWagner(int n) {

        int minCut = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            v[i] = i;
        }
        while (n>1){
            Arrays.fill(vis, false);
            Arrays.fill(w, 0);
            int pre = 0;
            for (int i = 1; i < n; i++) {
                // 选择边权最大的顶点加入到集合中
                int k = -1;
                for (int j = 1; j < n; j++) {
                    if (!vis[v[j]]) {
                        // 计算j到集合中所有点的边权和，pre是上一个加入的点
                        w[v[j]] += grap[v[pre]][v[j]];
                        if (k == -1 || w[v[j]] > w[v[k]]) {
                            k = j;
                        }
                    }
                }
                vis[v[k]] = true;
                if (i == n - 1){
                    int s = v[pre], t = v[k];
                    minCut = min(minCut, w[t]);
                    // 把顶点t合并到顶点s
                    for (int j = 0; j < n; j++) {
                        grap[s][v[j]] += grap[v[j]][t];
                        grap[v[j]][s] += grap[v[j]][t];
                    }
                    v[k] = v[--n]; // 删除顶点t
                }
                pre = k;
            }
        }
        return minCut;
    }
}
