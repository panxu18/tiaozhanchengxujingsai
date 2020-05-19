package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

public class FarmTour2135 {

    public static void main(String[] args) throws IOException {
        new FarmTour2135().solve();
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

    int MAXN = 2005;
    int INF = 1000000007;
    int N, M;
    Edge[] head = new Edge[MAXN];
    int sindex, tindex;
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        M = (int) readDouble();
        sindex = 1;
        tindex = N;
        for (int i = 1; i <= M; i++) {
            int u = (int) readDouble();
            int v = (int) readDouble();
            long cost = (long) readDouble();
            addEdges(u,v,1,cost);
            addEdges(v,u, 1, cost);
        }
        doSolve();
        out.println(ans);
        out.flush();

    }

    Edge[] pre = new Edge[MAXN];
    private void doSolve() {
        int f = 2;
        while (f > 0) {

            spfa();
            if (dis[N] == INF) {
                return;
            }
            Edge e = pre[N];
            long max = f;
            while (e != null) {
                max = min(max, e.rEdge.cap);
                e = pre[e.to];
            }
            f-=max;
            ans += dis[N];
            e = pre[N];
            while (e!= null){
                e.cap += max;
                e.rEdge.cap -= max;
                e = pre[e.to];
            }

        }
    }

    long[] dis = new long[MAXN];
    boolean[] inq = new boolean[MAXN];
    private void spfa() {
        Arrays.fill(dis, INF);
        Arrays.fill(inq, false);
        Arrays.fill(pre, null);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(1);
        dis[1] = 0;
        inq[1] = true;
        while (!que.isEmpty()){
            int f = que.poll();
            inq[f] = false;
            for (Edge e = head[f]; e != null; e = e.next){
                long d = e.cost + dis[f];
                if (e.cap > 0 && d < dis[e.to]){
                    dis[e.to] = d;
                    pre[e.to] = e.rEdge;
                    if (!inq[e.to]) {
                        inq[e.to] = true;
                        que.offer(e.to);
                    }
                }
            }
        }
    }


    private void addEdges(int u, int v, long cap, long cost) {
        head[u] = new Edge(v, cap, cost, head[u], null);
        head[v] = new Edge(u, 0, -cost, head[v], head[u]);
        head[u].rEdge = head[v];

    }

    class Edge {
        long cost;
        int to;
        long cap;
        Edge next, rEdge;
        Edge(int to, long cap, long cost, Edge next, Edge rEdge){
            this.cap = cap;
            this.cost = cost;
            this.to = to;
            this.next = next;
            this.rEdge = rEdge;
        }
    }
}
