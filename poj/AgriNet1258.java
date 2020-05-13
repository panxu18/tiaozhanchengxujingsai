package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AgriNet1258 {

    public static void main(String[] args) throws IOException {
        new AgriNet1258().solve();
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

    int N;
    int ans = 0;
    int[][] graph = new int[105][105];
    private void solve() throws IOException {
        while (canRead()) {
            N = readInt();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = readInt();
                }
            }
            prime();
            ans = 0;
            for (int i = 0; i < N; i++) {
                ans += dis[i];
            }
            out.println(ans);
        }
        out.flush();
    }

    int[] dis = new int[105];
    boolean[] visit = new boolean[105];
    private void prime() {
        Arrays.fill(dis, Integer.MAX_VALUE);
        Arrays.fill(visit, false);
        int s = 0;
        dis[s] = 0;
        for (int i = 1; i < N; i++) {
            visit[s] = true;
            int t = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!visit[j]) {
                    dis[j] = Math.min(graph[s][j], dis[j]);
                    if (dis[j] < min) {
                        min = dis[j];
                        t =j;
                    }
                }
            }
            s = t;
        }

    }
}
