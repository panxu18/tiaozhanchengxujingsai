package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class LakeCounting2386 {

    public static void main(String[] args) {
        new LakeCounting2386().solve();
    }
    int MAXN = 1000;
    char[][] map = new char[MAXN][MAXN];
    boolean[][] visit = new boolean[MAXN][MAXN];
    int n, m;
    void solve() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        try {
            String str = in.readLine();
            String[] num = str.split(" ");
            n = Integer.parseInt(num[0]);
            m = Integer.parseInt(num[1]);
            for (int i = 0; i < n; i++) {
                map[i] = in.readLine().toCharArray();
            }
            System.out.println(count());;
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.flush();
    }

    private int count() {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != '.') {
                    dfs(i,j);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        if (map[i][j] == '.') return;
        map[i][j] = '.';
        if (i > 0 && map[i - 1][j] != '.') dfs(i - 1, j); // 上
        if (i > 0 && j + 1 < m && map[i - 1][j + 1] != '.') dfs(i - 1, j + 1); // 右上
        if (j + 1 < m && map[i][j + 1] != '.') dfs(i, j + 1); // 右
        if (i + 1 < n && j + 1 < m && map[i + 1][j + 1] != '.') dfs(i + 1, j + 1); // 右下
        if (i + 1 < n && map[i + 1][j] != '.') dfs(i + 1, j); // 下
        if (i + 1 < n && j > 0 && map[i + 1][j - 1] != '.') dfs(i + 1, j - 1); //  左下
        if (j > 0 && map[i][j - 1] != '.') dfs(i, j - 1); // 左
        if (i > 0 && j > 0 && map[i - 1][j - 1] != '.') dfs(i - 1, j - 1); // 左上
    }


}
