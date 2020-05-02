package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Allowance3040 {

    public static void main(String[] args) throws IOException {
        new Allowance3040().solve();
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

    int N, C;
    Coin[] arr = new Coin[25];
    void solve() throws IOException {
        N = readInt();
        C = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = new Coin(readInt(), readInt());
        }
        Arrays.sort(arr, 0, N);
        ArrayList<Coin> que = new ArrayList<Coin>();
        for (int i = 0; i < N; i++) {
            que.add(arr[i]);
        }
        int ans = 0;
        out: while (!que.isEmpty()) {
            int sum = 0;
            //
            for (Iterator<Coin> it = que.iterator(); it.hasNext();){
                Coin c = it.next();
                if (c.b == 0) {
                    it.remove();
                    continue;
                } else if (sum + c.v <= C) {
                    long num = Math.min(c.b, (C - sum)/c.v);
                    sum += c.v * num;
                    c.b -= num;
                    if (c.b == 0) {
                        it.remove();
                    }
                    if (sum == C) {
                        ans++;
                        continue out;
                    }
                }
            }
            if (!que.isEmpty()) {
                que.get(que.size() - 1).b--;
                ans++;
            }
        }
        out.println(ans);
        out.flush();

    }

    class Coin implements  Comparable<Coin>{
        long v;
        int b;

        Coin(long v, int b) {
            this.v = v;
            this.b = b;
        }

        @Override
        public int compareTo(Coin o) {
            return v < o.v ? 1 : (v == o.v ? 0 : -1);
        }
    }
}
