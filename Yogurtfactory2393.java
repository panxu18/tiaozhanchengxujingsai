package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Yogurtfactory2393 {

    public static void main(String[] args) throws IOException {
        new Yogurtfactory2393().solve();
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

    int N, S;
    void solve() throws IOException {
        N = readInt();
        S = readInt();
        long min = Long.MAX_VALUE - 500;
        long cost = 0;
        for (int i = 0; i < N; i++) {
            int c = readInt();
            int y = readInt();
            min = Math.min(c, min + S);
            cost += min * (long) y;
        }
        out.println(cost);
        out.flush();

    }
}
