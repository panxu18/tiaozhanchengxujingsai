package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Layout3169 {

    public static void main(String[] args) throws IOException {
        new Layout3169().solve();
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

    int N, ML, MD;
    Edge[] head = new Edge[1005];
    void solve() throws IOException {
        N = readInt();
        ML = readInt();
        MD = readInt();
        Arrays.fill(head, null);
        for (int i = 0; i < ML; i++) {
            int a = readInt();
            int b = readInt();
            long d = readInt();
            addEdge(a, b, d);
        }
        for (int i = 0; i < MD; i++) {
            int a = readInt();
            int b = readInt();
            long d = readInt();
            addEdge(b, a, -d);
        }
        // 补全约束关系
        for (int i = 1; i < N; i++) {
            addEdge(i +1, i, 0L);
        }
        out.println(spfa(1));
        out.flush();
    }

    long[] dis = new long[1005];
    boolean[] inq = new boolean[1005];
    int[] sum = new int[1005];
    private long spfa(int s) {

        LinkedList<Integer> que = new LinkedList<Integer>();
        Arrays.fill(dis, Long.MAX_VALUE);
        Arrays.fill(inq, false);
        Arrays.fill(sum, 0);
        que.offer(s);
        dis[s] = 0L;
        inq[s] = true;
        while (!que.isEmpty()) {
            int v = que.poll();
            inq[v] = false;
            for (Edge e = head[v]; e != null; e = e.next) {
                long d = dis[v] + e.dis;
                if (d < dis[e.to]) {
                    dis[e.to] = d;
                    if (!inq[e.to]) {
                        que.offer(e.to);
                        if (++sum[e.to] > N) {
                            return -1L;
                        }
                    }
                }
            }
        }
        return dis[N] == Long.MAX_VALUE ? -2 : dis[N];
    }


    private void addEdge(int a, int b, long d) {
        head[a] = new Edge(b, d, head[a]);
    }

    class Edge{
        int to;
        long dis;
        Edge next;

        Edge(int to, long dis, Edge next){
            this.to = to;
            this.dis = dis;
            this.next = next;
        }
    }
}
