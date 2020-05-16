package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Evacuation3057 {

    public static void main(String[] args) throws IOException {
        new Evacuation3057().solve();
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

    int MAXN = 15;
    int MAXM = 10005;
    int INF = 1000000007;
    int T, N, M;
    char[][] map = new char[15][15];
    long ans;
    private void solve() throws IOException {
        T = (int) readDouble();
        for (int t = 0; t < T; t++) {
            N = (int) readDouble();
            M = (int) readDouble();
            for (int i = 0; i < N; i++) {
                readString().getChars(0, M, map[i], 0);
            }
            doSolve();
            System.out.println(ans==-1?"impossible" : ans);
        }
        out.flush();

    }
    int pMax = MAXN * MAXN;
    int gMax = MAXN << 2;
    int tMax = MAXN * MAXN;
    Point[] p = new Point[pMax];
    Point[] g = new Point[gMax];
    Edge[] head = new Edge[pMax + gMax * tMax];
    int[][] dis = new int[gMax][pMax];
    int[] match = new int[pMax + gMax * tMax];
    boolean[] vis = new boolean[pMax + gMax * tMax];
    int pcnt, gcnt;

    private void doSolve() {
        Arrays.fill(head, null);
        Arrays.fill(match, 0);
        pcnt = gcnt = 0;
        ans = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'D'){
                    g[gcnt++] = new Point(i, j);
                } else if(map[i][j] == '.'){
                    p[pcnt++] = new Point(i, j);
                }
            }
        }
        // 计算距离
        for (int i = 0; i < gcnt; i++) {
            bfs(g[i].x, g[i].y);
            for (int j = 0; j < pcnt; j++) {
                int d = tempDis[p[j].x][p[j].y];
                dis[i][j] = d;
            }
        }
        for (int i = 0; i < pcnt; i++) {
            boolean flag = false;
            for (int j = 0; j < gcnt; j++) {
                if (dis[j][i] != INF) {
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N*M; i++) {
            for (int j = 0; j < gcnt; j++) {
                int v = pcnt+(i-1)*gcnt+j;
                for (int k = 0; k < pcnt; k++) {
                    if (dis[j][k] <= i) {
                        addEdges(v, k);
                    }
                }
                Arrays.fill(vis, false);
                if(dfs(v) && ++cnt == pcnt){
                    ans = i;
                    return;
                }
            }
        }

    }

    int[][] tempDis = new int[MAXN][MAXN];
    int[][] dir = {{0,-1}, {0,1}, {-1,0}, {1,0}};

    // 计算门到每个坐标的距离
    private void bfs(int x, int y) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(tempDis[i], INF);
        }
        LinkedList<Point> que = new LinkedList<Point>();
        que.add(new Point(x,y));
        tempDis[x][y] = 0;
        while (!que.isEmpty()) {
            Point p = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dir[i][0];
                int ny = p.y + dir[i][1];
                if (nx >= 0 && nx < N
                        && ny >= 0 && ny < M
                        && map[nx][ny] == '.' && tempDis[nx][ny] == INF){
                    tempDis[nx][ny] = tempDis[p.x][p.y] + 1;
                    que.add(new Point(nx, ny));
                }
            }
        }
    }

    private void addEdges(int a, int b) {
        head[a] = new Edge(b, head[a]);
        head[b] = new Edge(a, head[b]);
    }

    // 匈牙利算法
    private boolean dfs(int v) {
        vis[v] = true;
        for (Edge e = head[v]; e != null; e = e.next){
            int w = match[e.to];
            if (w == 0 || !vis[w] && dfs(w)){
                match[v] = e.to;
                match[e.to] = v;
                return true;
            }
        }
        return false;
    }

    class Edge{
        int to;
        Edge next;
        Edge(int to, Edge next){
            this.to = to;
            this.next = next;
        }
    }

    class Point{
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
