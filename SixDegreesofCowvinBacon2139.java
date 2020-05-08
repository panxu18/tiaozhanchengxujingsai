package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class SixDegreesofCowvinBacon2139 {

    public static void main(String[] args) throws IOException {
        new SixDegreesofCowvinBacon2139().solve();
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
    int[][] graph = new int[305][305];
    int[] movArr = new int[305];
    int INF = 1000000007;
    int ans = INF;
    private void solve() throws IOException {
        N = readInt();
        M = readInt();

        for (int i = 0; i < 305; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            int mov = readInt();
            for (int j = 0; j < mov; j++) {
                movArr[j] = readInt();
                for (int k = 0; k < j; k++) {
                    graph[movArr[j]][movArr[k]] = 1;
                    graph[movArr[k]][movArr[j]] = 1;
                }
            }
        }
        floyd();
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (i!= j && graph[i][j] != INF) {
                    sum += graph[i][j];
                }
            }
            ans = Math.min(ans, sum * 100/(N-1));
        }
        out.println(ans);
        out.flush();
    }

    void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

}
