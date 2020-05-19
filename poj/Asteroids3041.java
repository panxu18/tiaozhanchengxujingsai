package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class Asteroids3041 {

    public static void main(String[] args) throws IOException {
        new Asteroids3041().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 505;
    int MAXM = 10005;
    int INF = 1000000007;
    int N, M;
    Edge[] head = new Edge[MAXN<<1];
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        M = (int) readDouble();
        for (int i = 0; i < M; i++) {
            int r = (int) readDouble();
            int c = (int) readDouble();
            head[r] = new Edge(c+N, head[r]);
        }

        out.println(binaryMatch());
        out.flush();

    }

    boolean[] vis = new boolean[MAXN<<1];
    int[] match = new int[MAXN<<1];
    int binaryMatch() {
        Arrays.fill(match, 0);
        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (match[i] == 0){
                Arrays.fill(vis, false);
                if(dfs(i)){
                    res++;
                }
            }
        }
        return res;
    }

    private boolean dfs(int i) {
        vis[i] = true;
        for (Edge e = head[i]; e != null; e = e.next){
            int w = match[e.to];
            if (!vis[w] && (w == 0 || dfs(w))) {
                match[i] = e.to;
                match[e.to] = i;
                return true;
            }
        }
        return false;
    }

    class Edge{
        int to;
        Edge next;
        Edge(int to, Edge next){
            this.to = to;
            this.next = next;
        }
    }
}
