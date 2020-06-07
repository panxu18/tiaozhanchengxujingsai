package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

public class Firing2987 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Firing2987().solve();
    }

    int MAXN = 5005;
    int N, M;
    Edge[] head = new Edge[MAXN];
    int source, sink;
    long profit, num;
    boolean[] vis = new boolean[MAXN];
    private void solve() throws IOException {
        N = read();
        M = read();
        source = N + 1;
        sink = source + 1;
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            int p = read();
            if(p > 0){
                addEdge(source, i, p);
                sum += p;
            } else {
                addEdge(i, sink, -p);
            }
        }
        for (int i = 0; i < M; i++) {
            int u = read();
            int v = read();
            addEdge(u, v, MAX_VALUE);
        }
        doSolve();
        out.printf("%d %d\n", num-1, sum - profit);
        out.flush();
    }

    private void doSolve() {
        isap();
        Arrays.fill(vis, false);
        num = dfs(source);
    }

    private int dfs(int v) {
        vis[v] = true;
        int res = 1;
        for (Edge e = head[v]; e != null; e = e.next) {
            if (e.cap > 0 && !vis[e.to]){
                res += dfs(e.to);
            }
        }
        return res;
    }

    private void addEdge(int u, int v, int cap) {
        head[u] = new Edge(u, v, cap, head[u], null);
        head[v] = new Edge(v, u, 0, head[v], head[u]);
        head[u].reEdge = head[v];
    }

    int[] gap = new int[MAXN];
    boolean hasGap = false;
    int[] dis = new int[MAXN];
    Edge[] cur = new Edge[MAXN];
    private void isap(){
        bfs();
        System.arraycopy(head, 0, cur, 0, sink+1);
        while (!hasGap && dis[source] < N + 2) {
            profit += aug(source, MAX_VALUE);
        }
    }

    private long aug(int v, long cap) {
        if (v == sink){
            return cap;
        }
        long flow = 0;
        for (Edge e = cur[v]; e != null; cur[v] = e,e = e.next){
            if (e.cap > 0 && dis[e.to] +1 == dis[v]){
                long d = aug(e.to, min(cap-flow, e.cap));
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
        if (--gap[dis[v]] == 0){
            hasGap = true;
        }
        gap[++dis[v]]++;
        cur[v] = head[v];
        return flow;
    }

    private void bfs() {
        Arrays.fill(dis, MAX_VALUE);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.addLast(sink);
        dis[sink] = 0;
        gap[dis[sink]]++;
        while (!que.isEmpty()) {
            int now = que.poll();
            for (Edge e = head[now]; e != null; e = e.next) {
                if (dis[e.to] == MAX_VALUE && e.reEdge.cap > 0) {
                    que.addLast(e.to);
                    dis[e.to] = dis[now] + 1;
                    gap[dis[e.to]]++;
                }
            }
        }
    }

    class Edge{
        int from, to;
        long cap;
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
