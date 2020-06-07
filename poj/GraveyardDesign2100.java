package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.ArrayList;

public class GraveyardDesign2100 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new GraveyardDesign2100().solve();
    }

    long  N, K;
    int MAXN = 10000005;
    long INF = Long.MAX_VALUE;
    int ans;
    ArrayList<Pair> ansList = new ArrayList<Pair>();
    private void solve() throws IOException {
        N = read();
        doSolve(N);
        out.println(ansList.size());
        for (Pair pair :
                ansList) {
            long len = pair.t - pair.h + 1;
            out.printf("%d ", len);
            for (int j = 0; j < len; j++) {
                if (j + 1 < len) {
                    out.printf("%d ", j + pair.h);
                } else {
                    out.printf("%d\n", j + pair.h);
                }
            }
        }
        out.flush();
    }

    private void doSolve(long n) {
        long sum = 1;
        ans = 0;
        long head = 1, tail = 1;
        while (tail * tail <= n) {
            if (sum > n){
                sum -= head * head;
                head++;
            } else if (sum < n){
                tail++;
                sum += tail * tail;
            } else {
                ansList.add(new Pair(head, tail));
                sum -= head * head;
                head++;
            }
            if (head > tail && tail * tail <= n){
                sum += tail * tail;
                tail++;
            }
        }
    }

    class Pair {
        long h, t;

        public Pair(long h, long t) {
            this.h = h;
            this.t = t;
        }
    }

}
