package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;

public class GCDLCMInverse2429 {

    public static void main(String[] args) throws IOException {
        new GCDLCMInverse2429().solve();
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

    long G, L;
    long[] arr = new long[25];
    Random random = new Random();
    private void solve() throws IOException {
        while (canRead()) {
            G = readLong();
            L = readLong();
            long n = L / G;
            ArrayList<Long> primes = fastPrimeFactory(n, 10);
            int len = primes.size();
            int tot = 0;
            for (int i = 0; i < len; i++) {
                long p = primes.get(i);
                if (n % p == 0) {
                    arr[tot] = 1;
                    while (n % p == 0) {
                        arr[tot] *= p;
                        n /= p;
                    }
                    tot++;
                }
            }
            len = tot;
            long a = G;
            long b = G;
            long min = Long.MAX_VALUE;
            for (int i = 0; i < (1 << len); i++) {
                long a2 = G;
                long b2 = G;
                for (int j = 0; j < len; j++) {
                    if ((i >> j & 1) == 1){
                        a2 *= arr[j];
                    }else {
                        b2 *= arr[j];
                    }
                }
                if (a2 + b2 < min) {
                    a = a2;
                    b = b2;
                    min = a + b;
                }
            }

            if (a > b) {
                a^=b; b^=a; a^=b;
            }
            out.printf("%d %d\n", a, b);
        }

        out.flush();
    }

    ArrayList<Long> fastPrimeFactory(long n, int s) {
        ArrayList<Long> factors = new ArrayList<Long>();
        findFactor(n, s, factors);
        return factors;
    }

    void findFactor(long n, int s, ArrayList<Long> factors) {
        if (n == 1) {
            return; // 1 不能分解
        }
        if (fastPrimeCheck(n, s)) {
            factors.add(n);
            return;
        }
        long p = n;
        while (p >= n) {
            p = pollardRho(n);
        }
        findFactor(p, s, factors);
        findFactor(n/p, s, factors);
    }

    /**
     * 通过x^2+c % n计算小于n伪随机数，利用floyd判断环
     */
    long pollardRho(long n) {
        long c = randLong()%(n-1)+1;
        long x = randLong() % n;
        long y = x;
        long i = 1, k = 2;
        while (true) {
            i++;
            x = (mulMod(x, x, n) + c) % n;
            long d = gcd(Math.abs(y - x), n);
            if (1 < d && d < n) {
                return d;
            }
            if (x == y) {
                return n; // 死循环
            }
            if (i == k) {
                y = x;
                k <<= 1;
            }
        }
    }

    /**
     * 利用费马小定理和二次探测检查检查质数性质,
     * @param x
     * @param s
     * @return
     */
    boolean fastPrimeCheck(long x, int s) {
        if (x == 2) {
            return true;
        }
        if (x < 2 || x % 2 == 0) {
            return false;
        }
        // 二次探测分解, n-1 = d* 2^t
        long p = x - 1L;
        long t = 0L;
        while ((p&1) == 0) {
            p >>= 1;
            t++;
        }
        for (int i = 0; i < s; i++) {
            long r = randLong() % (x - 1) + 1; // 不能为0
            long a = powMod(r, p, x);
            for (int j = 0; j < t; j++) {
                long b = mulMod(a, a, x);
                if (b == 1 && a != 1 && a != x-1) {
                    return false; // 二次探测
                }
                a = b;
            }
            if (a != 1) {
                return false; // 费马定理
            }
        }
        return true;
    }

    // 快速幂
    long powMod(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b&1) == 1) {
                res = mulMod(res, a, mod);
            }
            a = mulMod(a, a, mod);
            b>>=1;
        }
        return  res;
    }

    // 快速乘法
    long mulMod(long a, long b, long mod) {

        a %= mod;
        b %= mod;
        long res = 0L;
        while (b > 0) {
            if ((b & 1) == 1) {
                res += a;
                res %= mod;
            }
            a <<= 1;
            a %= mod;
            b >>= 1;
        }
        return res;
    }

    long randLong() {
        return random.nextLong() & Long.MAX_VALUE;
    }

    private long gcd(long x, long y) {
        if (y == 0L) {
            return x;
        }
        return gcd(y, x % y);
    }
}
