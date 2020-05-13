package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Curling3009 {

    public static void main(String[] args) throws IOException {
        new Curling3009().solve();
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

    int W, H;
    int[][] map = new int[25][25];
    void solve() throws IOException {
        while (true) {
            W = readInt();
            H = readInt();
            if (W == 0 && H == 0) {
                break;
            }
            int sx = 0;
            int sy = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = readInt();
                    if (map[i][j] == 2) {
                        sx = i;
                        sy = j;
                        map[i][j] = 0;
                    }
                }
            }
            ans = 10;
            for (int i = 1; i <= 10; i++) {

                dfs(sx, sy, 0, i);
            }
            out.println(ans == 10 ? -1 : ans + 1);
        }
        out.flush();
    }

    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    int ans = 10;
    private void dfs(int x, int y, int d, int maxDep) {
        if (d >= ans || d == maxDep || d == 10) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            move(x, y, i, d,  maxDep);
        }
    }

    void move(int x, int y, int drect, int dep, int maxDep) {
        int nx = x + dx[drect];
        int ny = y + dy[drect];
        while (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == 0) {
            nx += dx[drect];
            ny += dy[drect];
        }
        int newx = nx - dx[drect];
        int newy = ny - dy[drect];
        if (nx >= 0 && nx < H && ny >= 0 && ny < W){
            if (map[nx][ny] == 1){
                if (newx == x && newy == y ) {
                    return;
                }
                map[nx][ny] = 0;
                dfs(newx, newy, dep + 1, maxDep);
                map[nx][ny] = 1;
            }
            if (map[nx][ny] == 3) {
                ans = Math.min(ans, dep);
                return;
            }
        }
    }
}
