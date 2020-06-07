package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

public class HardLife3155 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new HardLife3155().solve();
    }

    int MAXN = 105;
    int MAXM = 1005;
    int N, M;
    Edge[] head = new Edge[MAXN];
    int[] dig = new int[MAXN];
    int source, sink;
    boolean[] vis = new boolean[MAXN];
    int[] U = new int[MAXM];
    int[] V = new int[MAXM];
    private void solve() throws IOException {
        N = read();
        M = read();
        if (M == 0){
            out.println("1");
            out.println("1");
            out.flush();
            return;
        }
        source = N + 1;
        sink = source + 1;
        for (int i = 0; i < M; i++) {
            U[i] = read();
            V[i] = read();
            dig[U[i]]++;
            dig[V[i]]++;
        }
        doSolve();
        out.println(cnt-1);
        for (int i = 1; i < cnt; i++) {
            out.println(ans[i]);
        }
        out.flush();
    }

    private void doSolve() {
        double lb = 0, ub = 100;
        // double类型有效位不超过15位，精度不能太高
        for (int i = 0; i < 20; i++) {
            double mid = (lb+ub)/2;
            constructGrap(mid);
            if (N * M - isap() > 0){
                lb = mid;
            } else {
                ub = mid;
            }
        }
        constructGrap(lb);
        isap();
        Arrays.fill(vis, false);
        dfs(source);
        Arrays.sort(ans, 1, cnt);
    }

    int[] ans = new int[MAXN];
    int cnt = 0;
    private void dfs(int v) {
        vis[v] = true;
        ans[cnt++] = v;
        for (Edge e = head[v]; e != null; e = e.next) {
            if (e.cap > 0 && !vis[e.to]){
                dfs(e.to);
            }
        }
    }


    private void constructGrap(double d){
        Arrays.fill(head, null);
        for (int i = 0; i < M; i++) {
            addBiEdge(U[i], V[i], 1);
            addBiEdge(V[i], U[i], 1);
        }
        for (int i = 1; i <= N; i++) {
            addBiEdge(source, i, M);
            addBiEdge(i, sink, M - dig[i] + 2*d);
        }
    }

    private void addBiEdge(int u, int v, double cap) {
        head[u] = new Edge(u, v, cap, head[u], null);
        head[v] = new Edge(v, u, 0, head[v], head[u]);
        head[u].reEdge = head[v];
    }

    int[] level = new int[MAXN];
    Edge[] cur = new Edge[MAXN];
    int[] gap = new int[MAXN];
    boolean hasGap;

    private double isap() {
        double res = 0;
        bfs();
        System.arraycopy(head, 0, cur, 0, MAXN);
        hasGap = false;
        while (!hasGap && level[source] <= N+2){
            res += aug(source, Integer.MAX_VALUE);
        }
        return res;
    }

    private double aug(int v, double cap) {
        if (v == sink) {
            return cap;
        }
        double flow = 0;
        for (Edge e = cur[v]; e != null; e = e.next){
            cur[v] = e;
            if (level[e.to] +1 == level[v] && e.cap > 0){
                double d = aug(e.to, min(cap-flow, e.cap));
                if (d > 0){
                    e.cap -= d;
                    e.reEdge.cap += d;
                    flow+=d;
                    if (flow == cap) {
                        return cap;
                    }
                }
            }
        }
        // 当前点增广路径处理完毕
        if (--gap[level[v]] == 0) {
            hasGap = true; // 断层
        }
        gap[++level[v]]++;
        cur[v] = head[v];
        return flow;
    }

    private void bfs() {
        Arrays.fill(level, 0);
        Arrays.fill(gap, 0);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(sink);
        gap[1] = level[sink] = 1;
        while (!que.isEmpty()){
            int f = que.poll();
            for (Edge e = head[f]; e != null; e = e.next){
                if (level[e.to] == 0 && e.reEdge.cap > 0) {
                    level[e.to] = level[f]+1;
                    gap[level[e.to]]++;
                    que.offer(e.to);
                }
            }
        }
    }

    class Edge{
        int from, to;
        double cap;
        Edge next, reEdge;

        public Edge(int from, int to, double cap, Edge next, Edge reEdge) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.next = next;
            this.reEdge = reEdge;
        }
    }
}
