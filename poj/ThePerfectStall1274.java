package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class ThePerfectStall1274 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new ThePerfectStall1274().solve();
    }

    int MAXN = 410;
    int N, M;
    Edge[] head = new Edge[MAXN];
    int[] match = new int[MAXN];
    boolean[] vis = new boolean[MAXN];
    int ans;
    private void solve() throws IOException {

        while (in.nextToken() != StreamTokenizer.TT_EOF){
            N = (int) in.nval;
            M = read();
            Arrays.fill(head, null);
            for (int i = 1; i <= N; i++) {
                int s = read();
                for (int j = 0; j < s; j++) {
                    addBiEdge(i, N + 1 + read());
                }
            }
            biMaxMatch();
            out.println(ans);
        }
        out.flush();
    }

    private void biMaxMatch() {
        Arrays.fill(match, 0);
        ans = 0;
        for (int i = 1; i <= N; i++) {
            if (match[i] == 0){
                Arrays.fill(vis, false);
                if (dfs(i)){
                    ans++;
                }
            }
        }
    }

    private boolean dfs(int v) {
        vis[v] = true;
        for (Edge e = head[v]; e != null; e = e.next){
            int m = match[e.to];
            if (m == 0 || !vis[m] && dfs(m)){
                match[v] = e.to;
                match[e.to] = v;
                return true;
            }
        }
        return false;
    }

    private void addBiEdge(int u, int v) {
        head[u] = new Edge(u, v, 1, head[u]);
        head[v] = new Edge(v, u, 0, head[v]);
    }


    private class Edge {
        int from, to, cap;
        Edge next;

        public Edge(int from, int to, int cap, Edge next) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.next = next;
        }
    }
}
