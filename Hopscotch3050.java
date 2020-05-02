package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Hopscotch3050 {

    public static void main(String[] args) throws IOException {
        new Hopscotch3050().solve();
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

    int[][] arr = new int[6][6];
    Set<String> set = new HashSet<String>();
    int ans = 0;
    void solve() throws IOException {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = readInt();
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, 0);
            }
        }
        out.println(ans);
        out.flush();
    }

    int[][] dre = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int[] path = new int[6];
    private void dfs(int x, int y, int d) {
        if (d == 6) {
            if (set.add(arr2String(path, 6))) {
                ans++;
            }
            return;
        }
        path[d] = arr[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dre[i][0];
            int ny = y + dre[i][1];
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                dfs(nx, ny, d + 1);
            }
        }
    }

    private String arr2String(int[] arr, int len){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
