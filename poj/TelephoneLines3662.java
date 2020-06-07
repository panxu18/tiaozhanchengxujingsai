package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TelephoneLines3662 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int readInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new TelephoneLines3662().solve();
    }

    int  N, K, P;
    int MAXN = 1005;
    int INF = 1000000007;
    Edge[] head = new Edge[MAXN];
    int[] dis = new int[MAXN];

    private void solve() throws IOException {
        N = readInt();
        P = readInt();
        K = readInt();
        for (int i = 0; i < P; i++) {
            int u = readInt();
            int v = readInt();
            int d = readInt();
            addEdge(u, v, d);
        }
        int k = bfs();
        if (k <= K){
            out.println(0);
        } else if (k == INF){
            out.println(-1);
        } else {
            int lb = 1, ub = 1000007;
            while (lb < ub){
                int mid = (lb + ub) >> 1;
                if (check(mid)){
                    ub = mid;
                } else {
                    lb = mid + 1;
                }
            }
            out.println(ub);
        }
        out.flush();
    }

    // 计算到N经过的边数
    private int bfs() {
        Arrays.fill(dis, INF);
        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(1);
        dis[1] = 0;
        while (!que.isEmpty()){
            int now = que.poll();
            for (Edge e = head[now]; e != null; e = e.next){
                if (dis[e.to] == INF){
                    dis[e.to] = dis[now] + 1;
                    que.offer(e.to);
                }
            }
        }
        return dis[N];
    }


    private void addEdge(int u, int v, int d) {
        head[u] = new Edge(u, v, d, head[u]);
        head[v] = new Edge(v, u, d, head[v]);
    }

    boolean[] used = new boolean[MAXN];
    // 判断到达N要经过条长度大于d的边不超过K条
    private boolean check(long d) {
        Arrays.fill(dis, INF);
        Arrays.fill(used, false);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(1);
        dis[1] = 0;
        while (!que.isEmpty()){
            int now = que.poll();
            if (used[now]) {
                continue;
            }
            used[now] = true;
            for (Edge e = head[now]; e != null; e = e.next){
                int d2 = dis[now];
                if (e.dis > d) {
                    d2++;
                }
                if (d2 < dis[e.to]){
                    if (e.dis <= d){
                        dis[e.to] = d2;
                        que.offerFirst(e.to);
                    } else {
                        dis[e.to] = d2;
                        que.offerLast(e.to);
                    }
                }
            }
        }
        return dis[N] <= K;
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
