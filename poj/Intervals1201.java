package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Intervals1201 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Intervals1201().solve();
    }

    int MAXN = 50005;
    int N, Q;
    Edge[] head = new Edge[MAXN];
    int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
    int[] dis = new int[MAXN];
    boolean[] inq = new boolean[MAXN];
    private void solve() throws IOException {
        N = read();
        for (int i = 0; i < N; i++) {
            int a = read();
            int b = read();
            int c = read();
            minValue = min(minValue, a);
            maxValue = max(maxValue, b+1);
            addEdge(a, b + 1, c);
        }
        for (int i = minValue; i <= maxValue; i++) {
            addEdge(i, i+1, 0);
            addEdge(i+1, i, -1);
        }
        spfa();
        out.println(dis[maxValue]);
        out.flush();
    }

    private void spfa() {
        Arrays.fill(dis, Integer.MIN_VALUE);
        Arrays.fill(inq, false);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.add(minValue);
        dis[minValue] = 0;
        inq[minValue] = true;
        while (!que.isEmpty()){
            int now = que.poll();
            inq[now] = false;
            for (Edge e = head[now]; e != null; e = e.next){
                int d = e.dis + dis[e.from];
                if (d > dis[e.to]){
                    dis[e.to] = d;
                    inq[e.to] = true;
                    if (!que.isEmpty() && d > que.peek()) {
                        que.addFirst(e.to);
                    } else {
                        que.addLast(e.to);
                    }
                }
            }
        }
    }

    private void addEdge(int u, int v, int dis){
        head[u] = new Edge(u, v, dis, head[u]);
    }

    class Edge{
        int from, to, dis;
        Edge next;

        public Edge(int from, int to, int dis, Edge next) {
            this.from = from;
            this.to = to;
            this.dis = dis;
            this.next = next;
        }
    }
}
