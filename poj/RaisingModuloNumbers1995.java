package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class RaisingModuloNumbers1995 {

    public static void main(String[] args) throws IOException {
        new RaisingModuloNumbers1995().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    long readLong() throws IOException {
        if(canRead()) {
            return Long.parseLong(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    String readLine() throws IOException {
        String s;
        if (st != null &&st.hasMoreTokens()) {
            s = st.nextToken("");
        } else {
            s = in.readLine();
        }
        if (s == null) {
            throw new NoSuchElementException();
        }
        st = null;
        return s;

    }

    char readChar() throws IOException {
        if (canRead()) {
            return st.nextToken().charAt(0);
        }
        throw new NoSuchElementException();
    }

    boolean canRead() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = in.readLine();
            if (s != null) {
                st = new StringTokenizer(s, " ");
            } else {
                return false;
            }
        }
        return true;
    }

    int MAXN;
    int Z;
    private void solve() throws IOException {
        Z = readInt();
        for (int z = 0; z < Z; z++) {
            int m = readInt();
            int n = readInt();
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += powMod(readInt(), readInt(), m);
                ans %= m;
            }
            out.println(ans);
        }
        out.flush();
    }

    private int powMod(int a, int b, int m) {
        int res = 1;
        while (b > 0) {
            if ((b &1) == 1) {
                res = mulMod(res, a, m);
            }
            a = mulMod(a, a, m);
            b >>= 1;
        }
        return res;
    }

    private int mulMod(int a, int b, int m) {
        int res = 0;
        a %= m;
        b %= m;
        while (b > 0) {
            if ((b&1)==1) {
                res += a;
                res %= m;
            }
            a <<= 1;
            a %= m;
            b >>= 1;
        }
        return res;
    }
}
