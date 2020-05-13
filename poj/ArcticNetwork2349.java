package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class ArcticNetwork2349 {

    public static void main(String[] args) throws IOException {
        new ArcticNetwork2349().solve();
    }

    int n, s, p;
    long[][] map = new long[505][505];
    Point[] points = new Point[505];
    boolean[] vis = new boolean[505];
    long[] dis = new long[505];

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            // 多组数据初始化
            Arrays.fill(points, null);
            Arrays.fill(vis, false);
            Arrays.fill(dis, Long.MAX_VALUE);
            String[] instr = in.readLine().split(" ");
            s = Integer.parseInt(instr[0]);
            p = Integer.parseInt(instr[1]);
            for (int k = 0; k < p; k++) {
                Arrays.fill(map[k], Long.MAX_VALUE);
            }
            for (int j = 0; j < p; j++) {
                instr = in.readLine().split(" ");
                int x = Integer.parseInt(instr[0]);
                int y = Integer.parseInt(instr[1]);
                points[j] = new Point(x, y);
                for (int k = 0; k <= j; k++) {
                    long d = dis(points[j], points[k]);
                    map[j][k] = d;
                    map[k][j] = d;

                }
            }
            out.printf("%.2f\n", Math.sqrt(prime()));
        }

        out.flush();
    }

    private long prime() {
        int cur = 0;
        vis[cur] = true;
        dis[cur] = 0;
        for (int i = 0; i < p; i++) {
            dis[p] = map[cur][p];
        }
        for (int i = 1; i < p; i++) {
            long min = Long.MAX_VALUE;
            for (int j = 0; j < p; j++) {
                if (!vis[j] && dis[j] < min){
                    cur = j;
                    min = dis[j];
                }
            }
            vis[cur] = true;
            for (int j = 0; j < p; j++) {
                if (!vis[j] && map[cur][j] < dis[j]){
                    dis[j] = map[cur][j];
                }
            }
        }
        Arrays.sort(dis);

        return dis[p - s];
    }

    long dis(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y)* (a.y - b.y);
    }

    class Edge implements Comparable<Edge>{
        int from;
        int to;
        long dist;
        Edge next;

        public Edge(int from, int to, long dist, Edge next) {
            this.from = from;
            this.to = to;
            this.dist = dist;
            this.next = next;
        }


        @Override
        public int compareTo(Edge o) {
            return (dist < o.dist) ? -1 : ((dist == o.dist) ? 0 : -1);
        }
    }

    class Point{
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[] par = new int[505 * 500];
    int[] rank = new int[505 * 500];
    private void init(int n) {
        for (int i = 0; i < n; i++) {
            par[i] = i;
            Arrays.fill(rank, 0);
        }
    }

    private int find(int x) {
        return par[x] == x ? x : (par[x] = find(par[x]));
    }

    private boolean same(int x, int y) {
        return find(x) == find(y);
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        if (rank[px] < rank[py]) {
            par[px] = py;
        } else {
            par[py] = px;
            if (rank[px] == rank[py]) rank[px]++;
        }
    }
}
