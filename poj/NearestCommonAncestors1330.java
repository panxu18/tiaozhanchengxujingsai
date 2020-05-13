package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NearestCommonAncestors1330 {

    public static void main(String[] args) throws IOException {
        new NearestCommonAncestors1330().solve();
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

    int N, T;
    int MAX_LOG_V;
    Edge[] head = new Edge[10005];
    int[] depth = new int[10005];
    int[][] parent = new int[32][10005];
    void solve() throws IOException {
        T = readInt();
        for (int i = 0; i < T; i++) {
            Arrays.fill(head, null);
            Arrays.fill(depth, -1);
            for (int j = 0; j < 32; j++) {
                Arrays.fill(parent[j], 0);
            }
            N = readInt();
            for (int j = 1; j < N; j++) {
                int a = readInt();
                int b = readInt();
                addEdge(a, b);
            }
            int root = 0;
            for (int j = 1; j <= N; j++) {
                if (parent[0][j] == 0) {
                    root = j;
                    break;
                }
            }
            MAX_LOG_V = 32 - Integer.numberOfLeadingZeros(N - 2);
            init(root, N);
            int a = readInt();
            int b = readInt();
            out.println(search(a, b));
        }
        out.flush();

    }

    int search(int a, int b) {
        if (depth[a] < depth[b]) {
            a ^= b; b ^= a; a^=b;
        }
        for (int k = 0; k < MAX_LOG_V; k++) {
            if ((depth[a] - depth[b] >> k & 1) == 1) {
                a = parent[k][a];
            }
        }
        if (a == b) {
            return a;
        }
        for (int k = MAX_LOG_V - 1; k >= 0 ; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        return parent[0][a];

    }

    void dfs(int v, int fa, int d) {
        parent[0][v] = fa;
        depth[v] = d;
        for (Edge e = head[v]; e != null; e = e.next) {
            if (depth[e.to] == -1){
                dfs(e.to, v, d + 1);
            }
        }
    }

    void init(int root, int V) {
        dfs(root, -1, 0);
        for (int k = 0; k + 1 < MAX_LOG_V; k++) {
            for (int i = 1; i <= V ; i++) {
                if (parent[k][i] < 1) {
                    parent[k + 1][i] = -1;
                } else {
                    parent[k + 1][i] = parent[k][parent[k][i]];
                }
            }
        }
    }

    private void addEdge(int a, int b) {
        head[a] = new Edge(b, head[a]);
        parent[0][b] = a;
    }

    class Edge {
        int to;
        Edge next;
        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }


}
