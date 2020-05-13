package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;

public class PseudoprimeNumbers3641 {
    public static void main(String[] args) throws IOException {
        new PseudoprimeNumbers3641().solve();
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

    private void solve() throws IOException {
        while (true){
            int p = readInt();
            int a = readInt();
            if (a == 0 && p == 0) {
                break;
            }
            out.println(checkPseudoprime(a, p) ? "yes" : "no");

        }
        out.flush();
    }

    private boolean checkPseudoprime(int a, int p) {
        if(fastCheckPrime(p)) {
            return false;
        }
        int b = powMod(a, p, p);
        return a == b;
    }

    Random random = new Random();
    int randInt() {
        return random.nextInt() & Integer.MAX_VALUE;
    }

    boolean fastCheckPrime(int n) {
        if (n == 2) {
            return true;
        }
        if (n < 2 || n % 2 == 0) {
            return false;
        }

        int p = n - 1;
        int t = 0;
        // 分解完全平方
        while ((p&1)== 0) {
            p >>= 1;
            t++;
        }
        int s = 10;
        for (int i = 0; i < s; i++) {
            int c = randInt() % (n - 1) + 1;
            int a = powMod(c,p,n); // 费马小定理
            for (int j = 0; j < t; j++) {
                int b = mulMod(a, a, n); // 平方探测
                if (b == 1 && a != 1 && a != n - 1) {
                    return false;
                }
                a = b;
            }
            if (a != 1) {
                return false;
            }
        }
        return true;
    }

    private int powMod(int a, int b, int mod) {
        int res = 1;
        while (b > 0) {
            if ((b&1)==1) {
                res = mulMod(res, a, mod);
            }
            a = mulMod(a, a, mod);
            b >>= 1;
        }
        return res;
    }

    private int mulMod(int a, int b, int mod) {
        int res = 0;
        a %= mod;
        b %= mod;

        while (b > 0) {
            if ((b&1)==1) {
                res += a;
                res %= mod;
            }
            a <<= 1;
            a %= mod;
            b >>= 1;
        }
        return res;
    }
}
