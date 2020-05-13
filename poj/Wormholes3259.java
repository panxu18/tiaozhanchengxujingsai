package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Wormholes3259 {

    public static void main(String[] args) throws IOException {
        new Wormholes3259().solve();
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

    int F, N, M, W;
    Edge[] head = new Edge[505];
    private void solve() throws IOException {
        F = readInt();
        for (int f = 0; f < F; f++) {
            Arrays.fill(head, null);
            N = readInt();
            M = readInt();
            W = readInt();
            for (int i = 0; i < M; i++) {
                int s = readInt();
                int e = readInt();
                int t = readInt();
                addEdges(s, e, t);
            }
            for (int i = 0; i < W; i++) {
                int s = readInt();
                int e = readInt();
                int t = readInt();
                addEdge(s, e, -t);
            }
            out.println(SPFA() ? "NO" : "YES");
        }
        out.flush();
    }
    int[] dis = new int[505];
    int[] sum = new int[505];
    boolean[] inq = new boolean[505];
    boolean SPFA(){
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(inq, false);
        Arrays.fill(sum, 0);
        LinkedList<Integer> que = new LinkedList<Integer>();
        int s = 1;
        que.offer(s);
        sum[s]++;
        inq[s] = true;
        dis[s] = 0;
        while (!que.isEmpty()) {
            s = que.poll();
            inq[s] = false;
            for (Edge e = head[s]; e != null; e = e.next){
                int d2 = dis[s] + e.dis;
                if (d2 < dis[e.to]) {
                    dis[e.to] = d2;
                    if (!inq[e.to]) {
                        que.offer(e.to);
                        inq[e.to] = true;
                        if (++sum[e.to] > N){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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
