package tiaozhanchengxujingsai.luogu;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

public class P4014 {

    public static void main(String[] args) throws IOException{
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

    int MAXN = 3000;
    int INF = 1000000007;
    long ans;
    int N;
    private void solve() throws IOException {
        N = (int) readDouble();
        source = N <<1;
        sink = source +1;

        for (int i = 0; i < N; i++) {
            addEdge(source, i, 0);
            addreEdge(source, i, 0);
            for (int j = 0; j < N; j++) {
                long cost = (long) readDouble();
                addEdge(i, j+N, cost);
                addreEdge(i, j+N, -cost);
            }
        }
        for (int i = 0; i < N; i++) {
            addEdge(i+N, sink, 0);
            addreEdge(i+N, sink, 0);
        }
        zkw();
        out.println(ans);
        head = rehead;
        zkw();
        out.println(-ans);
        out.flush();

    }

    private void addreEdge(int u, int v, long cost) {
        rehead[u] = new Edge(u, v, 1, cost, rehead[u], null);
        rehead[v] = new Edge(v, u, 0, -cost, rehead[v], rehead[u]);
        rehead[u].rEdge = rehead[v];
    }
    private void addEdge(int u, int v, long cost) {
        head[u] = new Edge(u, v, 1, cost, head[u], null);
        head[v] = new Edge(v, u, 0, -cost, head[v], head[u]);
        head[u].rEdge = head[v];
    }

    int source, sink;
    boolean[] used = new boolean[MAXN];
    boolean[] inq = new boolean[MAXN];
    long[] dis = new long[MAXN];
    Edge[] head = new Edge[MAXN];
    Edge[] rehead = new Edge[MAXN];
    long[] slack = new long[MAXN];
    private void zkw() {
        ans = 0;
        Arrays.fill(dis, 0);
        do{
            Arrays.fill(slack,INF);
            do{
                Arrays.fill(used, false);
            } while (aug(source, INF) > 0);
        } while (update());
    }

    private boolean update(){
        long d = INF;
        for (int i = 0; i < 2*N+2; i++) {
            if (!used[i]) {
                d = min(d, slack[i]);
                slack[i] = INF;
            }
        }
        if (d == INF) {
            return false;
        }
        for (int i = 0; i < 2*N+2; i++) {
            if (used[i]){
                dis[i] += d;
            }
        }
        return true;
    }

    // 在最短路上进行多路增广
    private long aug(int v, long cap) {
        if (v == sink){
            return cap;
        }
        used[v] = true;
        int f = 0;
        for (Edge e = head[v]; e != null; e = e.next){
            if (!used[e.to] && e.cap > 0 ){
                long t =  dis[e.to] + e.dis - dis[v];
                if (t == 0){
                    used[e.to] = true;
                    long d = aug(e.to, min(cap-f, e.cap));
                    ans += d * e.dis;
                    e.cap -= d;
                    e.rEdge.cap += d;
                    f += d;
                    if (f == cap){
                        break;
                    }
                } else {
                    slack[e.to] = min(slack[e.to], t);
                }

            }
        }
        return f;
    }

    class Edge{
        int from, to;
        long cap, dis;
        Edge next, rEdge;

        public Edge(int from, int to, long cap, long dis, Edge next, Edge rEdge) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.dis = dis;
            this.next = next;
            this.rEdge = rEdge;
        }
    }

}