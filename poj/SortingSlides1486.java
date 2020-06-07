package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class SortingSlides1486 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new SortingSlides1486().solve();
    }

    int MAXN = 100;
    int N, M, K;
    Edge[] head = new Edge[MAXN];
    Rec[] recs = new Rec[MAXN];
    int[] match = new int[MAXN];
    boolean[] vis = new boolean[MAXN];
    int deletedu = -1, deletedv = -1;

    private void solve() throws IOException {
        int cases = 0;
        while ((N=read()) != 0){
            cases++;
            caseInit();
            for (int i = 0; i < N; i++) {
                recs[i] = new Rec(read(), read(), read(), read());
            }
            for (int i = 0; i < N; i++) {
                int x = read();
                int y = read();
                for (int j = 0; j < N; j++) {
                    if (recs[j].contains(x, y)) {
                        head[j] = new Edge(j, i + N, head[j]);
                    }
                }
            }
            biGraphMath(); // 初始匹配
            out.printf("Heap %d\n", cases);
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if (match[i] != -1){
                    int cp = match[i];
                    match[cp] = -1;
                    deletedu = i;
                    deletedv = cp;
                    Arrays.fill(vis,false);
                    if (!find(i)){
                        out.printf("(%c,%d) ",i+'A', cp-N+1);
                        flag = true;
                        match[cp] = i;
                    }

                }
            }
            if (flag){
                out.printf("\n\n");
            } else {
                out.printf("none\n\n");
            }
        }
        out.flush();
    }

    private void caseInit() {
        Arrays.fill(match, -1);
        Arrays.fill(head, null);
        deletedu = -1;
        deletedv = -1;
    }

    private void biGraphMath() {
        for (int i = 0; i < N; i++) {
            if (match[i] == -1){
                Arrays.fill(vis, false);
                find(i);
            }
        }
    }

    private boolean find(int v) {
        vis[v] = true;
        for (Edge e = head[v]; e != null; e = e.next) {
            if (deletedu == v && deletedv == e.to) {
                // 删除边
                continue;
            }
            int cp = match[e.to];
            if (cp == -1 || !vis[cp] && find(cp)) {
                match[v] = e.to;
                match[e.to] = v;
                return true;
            }
        }
        return false;
    }

    private class Edge {
        int from, to;
        Edge next;
        boolean valid;

        public Edge(int from, int to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
            this.valid = true;
        }
    }

    private class Rec {
        int xmin, xmax, ymin, ymax;

        public Rec(int xmin, int xmax, int ymin, int ymax) {
            this.xmin = xmin;
            this.xmax = xmax;
            this.ymin = ymin;
            this.ymax = ymax;
        }

        private boolean contains(int x, int y){
            return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
        }
    }
}
