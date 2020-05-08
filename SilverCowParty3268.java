package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SilverCowParty3268 {

    public static void main(String[] args) throws IOException {
        new SilverCowParty3268().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    String readLine() throws IOException {
        String s;
        if (st != null &&st.hasMoreTokens()) {
            s = st.nextToken("");
        } else {
            s = in.readLine();
        }
        if (s == null) {
            throw new NoSuchElementException();
        }
        st = null;
        return s;

    }

    char readChar() throws IOException {
        if (canRead()) {
            return st.nextToken().charAt(0);
        }
        throw new NoSuchElementException();
    }

    boolean canRead() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = in.readLine();
            if (s != null) {
                st = new StringTokenizer(s, " ");
            } else {
                return false;
            }
        }
        return true;
    }

    int N, M, X;
    Edge[] head = new Edge[1005];
    Edge[] reHead = new Edge[1005];
    int ans = 0;
    private void solve() throws IOException {
        N = readInt();
        M = readInt();
        X = readInt();
        for (int i = 0; i < M; i++) {
            int u = readInt();
            int v = readInt();
            int d = readInt();
            addEdge(u, v, d);
            addReEdge(v, u, d);
        }
        dijkstra(head, X);
        System.arraycopy(dis, 1, dis2, 1, N);
        dijkstra(reHead, X);
        ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dis[i] + dis2[i]);
        }
        out.println(ans);
        out.flush();
    }

    int[] dis = new int[1005];
    int[] dis2 = new int[1005];
    private void dijkstra(Edge[] g, int s) {

        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<PairVecDis> que = new PriorityQueue<PairVecDis>();
        dis[s] = 0;
        que.add(new PairVecDis(s, 0));
        while (!que.isEmpty()) {
            PairVecDis p = que.poll();
            if (dis[p.v] < p.dis){
                continue;
            }
            for (Edge e = g[p.v]; e != null; e = e.next) {
                int d = e.dis + dis[p.v];
                if (d < dis[e.to]) {
                    que.add(new PairVecDis(e.to, d));
                    dis[e.to] = d;
                }
            }
        }
    }

    class PairVecDis implements Comparable<PairVecDis>{
        int v,dis;
        PairVecDis(int v, int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(PairVecDis o) {
            return dis - o.dis;
        }
    }

    private void addReEdge(int v, int u, int d) {
        reHead[v] = new Edge(u, d, reHead[v]);
    }



    private void addEdges(int s, int e, int t) {
        head[s] = new Edge(e, t, head[s]);
        head[e] = new Edge(s, t, head[e]);
    }

    private void addEdge(int s, int e, int t) {
        head[s] = new Edge(e, t, head[s]);
    }

    class Edge{
        int to;
        int dis;
        Edge next;

        Edge(int to, int dis, Edge next){
            this.to = to;
            this.dis = dis;
            this.next = next;
        }
    }
}
