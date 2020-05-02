package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PropertyDistribution_A0118 {
    public static void main(String[] args) throws IOException {
        new PropertyDistribution_A0118().solve();
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

    String next() throws IOException {
        while (st == null  || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken();
    }

    long W, H;
    char[][] arr = new char[105][2105];
    boolean[][] vis = new boolean[105][2105];
    void solve() throws IOException {
        while (true) {
            H = readInt();
            W = readInt();
            if (W == 0 && H == 0) {
                break;
            }
            for (int i = 0; i < H; i++) {
                String str = next();
                str.getChars(0, str.length(), arr[i], 0);
            }
            int ans = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (!vis[i][j]) {
                        dfs(i, j);
                        ++ans;
                    }
                }
            }
            out.println(ans);

        }
        out.flush();
    }

    private void dfs(int x, int y) {
        vis[x][y] = true;
        char c = arr[x][y];
        if (x > 0 && !vis[x - 1][y] && arr[x - 1][y] == c) {
            dfs(x - 1, y);
        }
        if (y > 0 && !vis[x][y - 1] && arr[x][y - 1] == c) {
            dfs(x, y - 1);
        }
        if (x < H - 1 && !vis[x + 1][y] && arr[x + 1][y] == c) {
            dfs(x + 1, y);
        }
        if (y < H - 1 && !vis[x][y + 1] && arr[x][y + 1] == c) {
            dfs(x, y + 1);
        }
    }
}
