package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class WirelessNetwork2236 {
    public static void main(String[] args) throws IOException {
        new WirelessNetwork2236().solve();
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
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), "");
        }
        return st.nextToken();
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

    int N, D;
    Point[] points = new Point[1005];
    Edge[] head = new Edge[1005];
    boolean[] visit = new boolean[1005];
    int[] par = new int[1005];
    int[] rank = new int[1005];

    private void solve() throws IOException {
        N = readInt();
        D = readInt();
        long d2 = D * D;
        for (int i = 1; i <= N; i++) {
            points[i] = new Point(readInt(),readInt());
            for (int j = 1; j < i; j++) {
                if (dis(points[i], points[j]) <= d2) {
                    addEdge(i,j);
                }
            }
        }
        init();
        while (canRead()){
            char o = readChar();
            if (o == 'O') {
                int p = readInt();
                repaire(p);
            } else {
                int p = readInt();
                int q = readInt();
                out.println(isSame(p, q) ? "SUCCESS" : "FAIL");
            }
        }

        out.flush();
    }

    private void repaire(int p) {
        visit[p] = true;
        for (Edge e = head[p]; e != null; e = e.next){
            if (visit[e.to] == true) {
                union(p, e.to);
            }
        }
    }

    void init() {
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }
        Arrays.fill(rank,0);
    }

    void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        if (rank[x] < rank[y]) {
            par[px] = py;
        } else {
            par[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
        }
    }

    private int find(int x) {
        return par[x] == x ? x : (par[x] = find(par[x]));
    }

    private boolean isSame(int x, int y) {
        return find(x) == find(y);
    }

    private void addEdge(int i, int j) {
        head[i] = new Edge(j, head[i]);
        head[j] = new Edge(i, head[j]);
    }

    private void reverse(int[] arr, int s, int t) {
        t--;
        while (s < t){
            int temp = arr[s];
            arr[s++] = arr[t];
            arr[t--] = temp;
        }
    }

    private long dis(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    class Edge {
        int to;
        Edge next;

        Edge (int to, Edge next){
            this.to = to;
            this.next = next;
        }
    }

}
