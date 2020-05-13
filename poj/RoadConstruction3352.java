package tiaozhanchengxujingsai.poj;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class RoadConstruction3352 {

    public static void main(String[] args) {
        new RoadConstruction3352().solve();
    }

    int n, m;
    Edge[] head = new Edge[1005]; // 邻接表
    boolean[] visited = new boolean[2010]; // 边访问标记
    int eId; // 边索引
    int[] dfn = new int[1005];
    int[] low = new int[1005];
    int time;
    int[] color = new int[1005]; // 边双连通分量标记
    int cnt; // 边双联通分量索引
    int[] degree = new int[1005]; // 连通分量缩点深度
    Stack<Integer> stack = new Stack<Integer>();
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        m = in.nextInt();
        Arrays.fill(head, 0, n + 1, null);
        Arrays.fill(color, 0, n + 1,0);
        for (int j = 0; j < m; j++) {
            int a = in.nextInt();
            int b = in.nextInt();
            addEdges(a, b);
        }

        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0) tarjan(i, -1);
        }
        for (int i = 1; i <= n ; i++) {
            for (Edge e = head[i]; e != null; e = e.next) {
                if (color[i] != color[e.to])
                    degree[color[i]]++;
            }
        }
        int k = 0;
        for (int i = 1; i <= cnt ; i++) {
            if (degree[i] == 1) k++;
        }
        out.println((k + 1) / 2);
        out.flush();
    }

    private void tarjan(int v, int fa) {

        dfn[v] = low[v] = ++time;
        stack.push(v);
        for (Edge e = head[v]; e != null; e = e.next) {
            if (visited[e.id] == true) continue;
            visited[e.id] = visited[e.id ^ 1] = true;
            if (dfn[e.to] == 0) {
                tarjan(e.to, v);
                low[v] = Math.min(low[v], low[e.to]);
            } else {
                low[v] = Math.min(low[v], dfn[e.to]);
            }
        }
        // 找到一个边双联通分量
        if (low[v] == dfn[v]) {
            // fa-v 是桥
            int u = 0;
            cnt++;
            do {
                u  = stack.pop();
                color[u] = cnt;
            } while (u != v);
        }

    }

    private void addEdges(int a, int b){
        head[a] = new Edge(b, head[a], eId++);
        head[b] = new Edge(a, head[b], eId++);
    }

    class Edge{
        int to;
        Edge next;
        int id; // 处理重边

        public Edge(int to, Edge next, int id) {
            this.to = to;
            this.next = next;
            this.id = id;
        }
    }
}
