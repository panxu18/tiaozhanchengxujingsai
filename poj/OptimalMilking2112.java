package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

public class OptimalMilking2112 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new OptimalMilking2112().solve();
    }

    int MAXN = 300;
    int N, M, K;
    Edge[] head = new Edge[MAXN];
    int[][] graph = new int[MAXN][MAXN];
    int[][] dis = new int[MAXN][MAXN];
    int ans;
    int INF = 1000000007;
    int source, sink;
    int[] level = new int[MAXN];
    int[] gap = new int[MAXN];
    Edge[] cur = new Edge[MAXN];
    boolean stop;

    private void solve() throws IOException {
        K = read();
        N = read();
        M = read();
        source = K+N;
        sink = source + 1;
        for (int i = 0; i < K + N; i++) {
            for (int j = 0; j < K + N; j++) {
                int d = read();
                graph[i][j] = d == 0? INF : d;
            }
            graph[i][i] = 0;
        }
        out.println(doSolve());
        out.flush();
    }

    private int doSolve() {
        floyd();
        int lb = 0, ub = INF;
        while (lb < ub){
            int mid = (lb + ub)>>1;
            contructGraph(mid);
            if (isap()) {
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        return ub;
    }

    private boolean isap() {
        int flow = 0;
        System.arraycopy(head, 0, cur, 0, MAXN);
        stop = false; // 忘记初始化，导致WA
        bfs();
        while (!stop && level[source] < K + N + 2) {
            flow += dfs(source, INF);
            if (flow >= N){
                return true;
            }
        }
        return flow >= N;
    }

    private int dfs(int v, int cap) {
        if (v == sink){
            return cap;
        }
        int flow = 0;
        for (Edge e = cur[v]; e != null; cur[v] = e, e = e.next){
            if (e.cap > 0 && level[v] == level[e.to] + 1) {
                int d = dfs(e.to, min(cap - flow, e.cap));
                if (d > 0) {
                    e.cap -= d;
                    e.reEdge.cap += d;
                    flow += d;
                    if (flow == cap) {
                        return flow;
                    }
                }
            }
        }
        if (--gap[level[v]] == 0){
            stop = true;
        }
        gap[++level[v]]++;
        cur[v] = head[v];
        return flow;
    }

    private void bfs() {
        Arrays.fill(level, -1);
        Arrays.fill(gap, 0);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.addLast(sink);
        level[sink] = 0;
        gap[level[sink]]++;
        while (!que.isEmpty()) {
            int now = que.poll();
            for (Edge e = head[now]; e != null; e = e.next) {
                if (level[e.to] == -1 && e.reEdge.cap > 0){
                    que.addLast(e.to);
                    level[e.to] = level[now] + 1; // 没有加1导致WA
                    gap[level[e.to]]++;
                }
            }
        }
    }

    private void contructGraph(int d) {
        Arrays.fill(head, null);
        for (int i = 0; i < N; i++) {
            addBiEdge(source, K + i, 1);
        }
        for (int i = 0; i < K; i++) {
            addBiEdge(i, sink, M);
        }
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j + K] <= d){
                    addBiEdge(j + K, i, 1);
                }
            }
        }
    }

    private void floyd(){

        for (int k = 0; k < K + N; k++) {
            for (int i = 0; i < K + N; i++) {
                for (int j = 0; j < K + N; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    private void addBiEdge(int u, int v, int cap) {
        head[u] = new Edge(u, v, cap, head[u], null);
        head[v] = new Edge(v, u, 0, head[v], head[u]);
        head[u].reEdge = head[v];
    }


    private class Edge {
        int from, to, cap;
        Edge next, reEdge;

        public Edge(int from, int to, int cap, Edge next, Edge reEdge) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.next = next;
            this.reEdge = reEdge;
        }
    }
}
