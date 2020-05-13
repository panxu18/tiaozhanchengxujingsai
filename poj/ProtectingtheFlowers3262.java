package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProtectingtheFlowers3262 {

    public static void main(String[] args) throws IOException {
        new ProtectingtheFlowers3262().solve();
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

    int N;
    Cow[] cows = new Cow[100005];
    void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            cows[i] = new Cow(readInt(), readInt());
        }
        Arrays.sort(cows, 0, N);
        long t = 0;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += t * cows[i].d;
            t += 2 * cows[i].t;
        }
        out.println(ans);
        out.flush();
    }

    class Cow implements Comparable<Cow> {
        int t;
        int d;

        Cow(int t, int d){
            this.t = t;
            this.d = d;
        }

        @Override
        public int compareTo(Cow o) {
            double a = (double) t / (double)d;
            double b = (double) o.t / (double)o.d;
            return a < b ? -1 : (a == b ? 0 : 1);
        }
    }
}
