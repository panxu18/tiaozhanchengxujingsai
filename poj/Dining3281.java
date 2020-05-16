package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

public class Dining3281 {
    public static void main(String[] args) throws IOException {
        new Dining3281().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    String readString() throws IOException {
        in.nextToken();
        return in.sval;
    }

    int MAXN = 105;
    int MAXM = 10005;
    int INF = 1000000007;
    int N, F, D;
    Edge[] head = new Edge[MAXN<<2];
    int foodOffset, drinkOffset, cowLeftOffset, cowRightOffset;
    int sindex, tindex;
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        F = (int) readDouble();
        D = (int) readDouble();
        foodOffset = -1;
        drinkOffset = foodOffset + F;
        cowLeftOffset = drinkOffset + D;
        cowRightOffset = cowLeftOffset + N;
        sindex = cowRightOffset + N + 1;
        tindex = sindex + 1;
        for (int i = 1; i <= N; i++) {
            addEdges(cowLeftOffset + i, cowRightOffset + i);
            int fn = (int) readDouble();
            int dn = (int) readDouble();
            for (int j = 0; j < fn; j++) {
                // 食物-牛
                int f = (int) readDouble();
                addEdges(foodOffset + f, cowLeftOffset + i);
            }
            for (int j = 0; j < dn; j++) {
                int d = (int) readDouble();
                addEdges(cowRightOffset + i, drinkOffset + d);
            }
        }
        for (int i = 1; i <= F; i++) {
            addEdges(sindex, foodOffset+i);
        }
        for (int i = 1; i <= D; i++) {
            addEdges(drinkOffset+i, tindex);
        }
        ek();
        out.println(ans);
        out.flush();

    }

    boolean[] used = new boolean[MAXN<<2];

    long[] flow = new long[MAXN<<2];
    Edge[] pre = new Edge[MAXN<<2];
    private void ek(){
        ans = 0;
        while (true){
            Arrays.fill(flow, 0);
            Arrays.fill(pre, null);
            LinkedList<Integer> que = new LinkedList<Integer>();
            que.offer(sindex);
            flow[sindex] = INF;
            while (!que.isEmpty()) {
                int f = que.poll();
                if (f == tindex) {
                    break;
                }
                for (Edge e = head[f]; e != null; e = e.next) {
                    if (flow[e.to] == 0 && e.cap > 0) {
                        flow[e.to] = min(e.cap, flow[f]);
                        pre[e.to] = e.rEdge;
                        que.offer(e.to);
                    }
                }
            }

            if (flow[tindex] == 0) {
                return;
            }
            ans += flow[tindex];
            Edge preEdge = pre[tindex];
            while (preEdge!= null) {
                preEdge.cap += flow[tindex];
                preEdge.rEdge.cap -= flow[tindex];
                preEdge = pre[preEdge.to];
            }
        }
    }

    private void addEdges(int a, int b) {
        head[a] = new Edge(b, 1, head[a], null);
        head[b] = new Edge(a, 0, head[b], head[a]);
        head[a].rEdge = head[b];
    }

    class Edge {
        int to;
        long cap;
        Edge next, rEdge;
        Edge(int to, long cap, Edge next, Edge rEdge){
            this.cap = cap;
            this.to = to;
            this.next = next;
            this.rEdge = rEdge;
        }
    }
}
