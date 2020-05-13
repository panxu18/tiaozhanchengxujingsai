package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FrogDating1061 {

    public static void main(String[] args) throws IOException {
        new FrogDating1061().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    long readLong() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Long.parseLong(st.nextToken());
    }

    long x, y, m, n, L;
    void solve() throws IOException {
        x = readLong();
        y = readLong();
        m = readLong();
        n = readLong();
        L = readLong();
        long a = m - n;
        long b = L;
        long c = y - x;
        if (a < 0) {
            a = -a;
            c = -c;
        }
        long[] ansX = new long[1];
        long[] ansY = new long[1];
        long g = exgcd(a, b, ansX, ansY);
        if (c % g == 0) {
            long ans = ansX[0] * c / g;
            b = b / g;
            ans = (ans % b + b) % b;
            out.println(ans);
        } else {
            out.println("Impossible");
        }
        out.flush();
    }

    private long exgcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            long ans = exgcd(b, a%b, y, x);
            y[0] = (y[0] - (a / b) * x[0]);
            return ans;
        }
    }
}
