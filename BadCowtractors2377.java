package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class BadCowtractors2377 {

    public static void main(String[] args) throws IOException {
        new BadCowtractors2377().solve();
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

    int N, M;
    int ans = 0;
    Edge[] edges = new Edge[40005];
    private void solve() throws IOException {
        N = readInt();
        M = readInt();
        for (int i = 0; i < M; i++) {
            edges[i] = new Edge(readInt(), readInt(), readInt());
        }
        ans = kruskal();
        out.println(ans);
        out.flush();
    }


    private int kruskal() {
        Arrays.sort(edges, 0, M);
        init();
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < M; i++) {
            Edge e = edges[i];
            if (!isSame(e.from, e.to)) {
                union(e.from, e.to);
                cnt++;
                sum += e.dis;
            }
        }
        return cnt == N - 1 ? sum : -1;

    }

    private void union(int from, int to) {
        int px = find(from);
        int py = find(to);
        if (px == py) {
            return;
        }
        if (rank[px] < rank[py]){
            par[px] = py;
        } else {
            par[py] = px;
            if (rank[px] == rank[py]) {
                rank[py]++;
            }
        }
    }

    private boolean isSame(int from, int to) {
        return find(from) == find(to);
    }

    private int find(int x) {
        return par[x] == x ? x : (par[x] = find(par[x]));
    }

    int[] par = new int[1005];
    int[] rank = new int[1005];

    private void init() {
        for (int i = 1; i <= N; i++) {
            par[i] = i;
        }
        Arrays.fill(rank,0);
    }




    class Edge implements Comparable<Edge>{
        int from;
        int to;
        int dis;
        Edge(int f, int t, int d) {
            this.from = f;
            this.to = t;
            this.dis = d;
        }

        @Override
        public int compareTo(Edge o) {
            return o.dis - dis;
        }
    }
}
