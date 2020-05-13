package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class PrimePath3126 {

    public static void main(String[] args) throws IOException {
        new PrimePath3126().solve();
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

    long readLong() throws IOException {
        if(canRead()) {
            return Long.parseLong(st.nextToken());
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

    int T;
    int ans = 0;
    private void solve() throws IOException {
        T = readInt();
        checkPrime();
        build();
        for (int t = 0; t < T; t++) {
            ans = bfs(readInt(), readInt());
            out.println(ans == -1 ? "Impossible" : ans);
        }
        out.flush();
    }

    static boolean[] isPirme = new boolean[10005];

    void checkPrime() {
        Arrays.fill(isPirme, true);
        for (int i = 2; i * i< 10005; i++) {
            if (isPirme[i]) {
                for (int j = i + i; j < 10005; j += i) {
                    isPirme[j] = false;
                }
            }
        }
    }
    boolean[] vis = new boolean[10005];
    int[] dep = new int[10005];

    int bfs(int s, int t) {
        Arrays.fill(vis, false);
        Arrays.fill(dep, 0);
        ans = Integer.MAX_VALUE;
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(s);
        vis[s] = true;
        while (!que.isEmpty()) {
            s = que.poll();
            if (s == t) {
                return dep[t];
            }
            for (Edge e = head[s]; e != null; e = e.next){
                if (!vis[e.to]){
                    que.offer(e.to);
                    vis[e.to] = true;
                    dep[e.to] = dep[s] + 1;
                }
            }
        }
        return -1;
    }

    Edge[] head = new Edge[10005];

    void build() {
        for (int v = 1000; v < 10000; v++) {
            if (isPirme[v]) {
                for (int h = v, mul = 1; h > 0 ; h /= 10, mul *= 10) {
                    int d = h % 10;
                    for (int i = 1; i + d <= 9; i++) {
                        int u = v + i * mul;
                        if (isPirme[u]) {
                            addEdges(v, u);
                        }
                    }
                }
            }
        }

    }

    private void addEdges(int v, int u) {
        head[v] = new Edge(u, head[v]);
        head[u] = new Edge(v, head[u]);
    }

    class Edge{
        int to;
        Edge next;

        Edge(int t, Edge n){
            this.to = t;
            this.next = n;
        }
    }
}
