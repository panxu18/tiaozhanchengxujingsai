package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MeteorShower3669 {
    public static void main(String[] args) throws IOException {
        new MeteorShower3669().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Integer.parseInt(st.nextToken());
    }

    String readLine() throws IOException {
        return in.readLine();
    }

    int M;
    int[][] untime = new int[305][305];
    int[][] time = new int[305][305];
    void solve() throws IOException {
        M = readInt();
        for (int i = 0; i < 305; i++) {
            Arrays.fill(untime[i], Integer.MAX_VALUE);
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < M; i++) {
            int x = readInt();
            int y = readInt();
            int t = readInt();
            untime[x][y] = Math.min(t, untime[x][y]);
            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                if (nx >= 0 && nx < 305
                        && ny >= 0 && ny < 305) {
                    untime[nx][ny] = Math.min(t, untime[nx][ny]);
                }
            }
        }
        ans = 2000;
        bfs();
        out.println(ans == 2000 ? -1 : ans);
        out.flush();
    }

    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    int ans = 2000;

    private void bfs() {
        int x = 0;
        int y = 0;
        LinkedList<Point> que = new LinkedList<Point>();
        if (untime[x][y] == 0) {
            return;
        }
        time[x][y] = 0;
        que.offer(new Point(x, y));
        while (!que.isEmpty()) {
            Point p = que.pop();
            x = p.x;
            y = p.y;
            if (untime[x][y] == Integer.MAX_VALUE) {
                ans = Math.min(ans, time[x][y]);
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < 305
                        && ny >= 0 && ny < 305) {
                    if (time[x][y] + 1 < untime[nx][ny] && time[x][y] + 1 < time[nx][ny]) {
                        que.offer(new Point(nx, ny));
                        time[nx][ny] = time[x][y] + 1;
                    }
                }
            }
        }
    }

    private void astar() {
        int x = 0;
        int y = 0;
        PriorityQueue<Point> que = new PriorityQueue<Point>();
        if (untime[x][y] == 0) {
            return;
        }
        time[x][y] = 0;
        que.offer(new Point(x, y, time[x][y] + g[x][y]));
        while (!que.isEmpty()) {
            Point p = que.poll();
            x = p.x;
            y = p.y;
            if (untime[x][y] == Integer.MAX_VALUE) {
                ans = Math.min(ans, time[x][y]);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < 305
                        && ny >= 0 && ny < 305) {
                    if (time[x][y] + 1 < untime[nx][ny] && time[x][y] + 1 < time[nx][ny]) {
                        time[nx][ny] = time[x][y] + 1;
                        que.offer(new Point(nx, ny, time[nx][ny] + g[nx][ny]));
                    }
                }
            }
        }
    }
    int[][] g = new int[305][305];
    void preBfs() {
        for (int i = 0; i < 305; i++) {
            Arrays.fill(g[i], Integer.MAX_VALUE);
        }
        LinkedList<Point> que = new LinkedList<Point>();
        for (int i = 0; i < 305; i++) {
            for (int j = 0; j < 305; j++) {
                if (untime[i][j] == Integer.MAX_VALUE) {
                    que.offer(new Point(i, j, 0));
                    g[i][j] = 0;
                }
            }
        }
        while (!que.isEmpty()) {
            Point p = que.poll();
            int x = p.x;
            int y = p.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < 305
                        && ny >= 0 && ny < 305) {
                    if (g[x][y] + 1 < g[nx][ny]) {
                        g[nx][ny] = g[x][y] + 1;
                        que.offer(new Point(nx, ny, 0));
                    }
                }
            }
        }
    }

    class Point implements Comparable<Point>{
        int x;
        int y;
        int f;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int f) {
            this.x = x;
            this.y = y;
            this.f = f;
        }

        @Override
        public int compareTo(Point o) {
            return f - o.f;
        }
    }
}
