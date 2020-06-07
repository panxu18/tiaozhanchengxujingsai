package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

public class TransferringSylla3713 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
    String readString() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        new TransferringSylla3713().solve();
    }

    int MAXN = 505;
    int N, M;
    Edge[] head = new Edge[MAXN];
    private void solve() throws IOException {
        while ((N = read()) != 0){
            M = read();
            Arrays.fill(head, null);
            for (int i = 0; i < M; i++) {
                int u = read();
                int v = read();
                addEdge(u, v);
            }
            boolean flag = true;
            out:
            for (int i = 0; i < N; i++) {
                Arrays.fill(dfn, -1);
                Arrays.fill(low, -1);
                cnt = 0;
                cutPointNum = 0;
                tarjan((i+1) % N, -1, (i+1) % N, i);
                if (cutPointNum > 0 || cnt < N-1){
                    flag = false;
                    break out;
                }
            }
            out.println(flag ? "YES" : "NO");
        }
        out.flush();
    }

    int[] dfn = new int[MAXN];
    int[] low = new int[MAXN];
    int cnt = 0;
    int cutPointNum = 0;
    private void tarjan(int v, int fa, int root, int x){
        dfn[v] = low[v] = cnt++;
        int children = 0;
        for (Edge e = head[v]; e != null; e = e.next){
            if (e.to == fa || e.to == x){
                continue;
            }
            if (dfn[e.to] == -1){
                children++;
                tarjan(e.to, v, root, x);
                low[v] = min(low[v], low[e.to]);
                // 割点
                if (v == root && children>1 || v != root && low[e.to] >= dfn[v]){
                    cutPointNum++;
                }
            }else {
                low[v] = min(low[v], dfn[e.to]);
            }
        }
    }

    private void addEdge(int u, int v) {
        head[u] = new Edge(u, v, head[u]);
        head[v] = new Edge(v, u, head[v]);
    }

    class Edge{
        int from, to;
        Edge next;
        public Edge(int from, int to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
        }
    }
}
