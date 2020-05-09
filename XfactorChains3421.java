package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class XfactorChains3421 {

    public static void main(String[] args) throws IOException {
        new XfactorChains3421().solve();
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

    int X;
    int[] facArr = new int[1000];
    int fcnt = 0;
    int[] divArr = new int[2500];
    int dcnt = 0;
    int[][] dp = new int[2][1100000];
    private void solve() throws IOException {
//        predeal();
        computePrime();
        while (canRead()) {
            X = readInt();
            out.printf("%d %d\n", dp[0][X],dp[1][X]);
        }
        out.flush();
    }

    private void primeFactory(int n) {
        for (int i = 2; i * i <= n ; i++) {
            if (n % i == 0) {
                facArr[fcnt++] = i;
                while (n % i == 0) {
                    n /= i;
                }
            }
        }
        if (n != 1) {
            facArr[fcnt++] = n;
        }
    }

    private void divisor(int n){
        divArr[dcnt++] = n;
        for (int i = 2; i * i <= n ; i++) {
            if (n % i == 0) {
                divArr[dcnt++] = i;
                if (i * i != n){
                    divArr[dcnt++] = n / i;
                }
            }
        }
    }

    // 普通打表
    private void predeal() {
        Arrays.fill(dp[0],1);
        Arrays.fill(dp[1],1);
        for (int i = 2; i <= N >> 1; i++) {
            for (int j = i * 2; j <= N; j += i) {
                if (dp[0][i] + 1 > dp[0][j]) {
                    dp[0][j] = dp[0][i] + 1;
                    dp[1][j] = dp[1][i];
                } else if (dp[0][i] + 1 == dp[0][j]) {
                    dp[1][j] += dp[1][i];
                }
            }
        }
    }

    // 素数筛选打表
    int N = 1 << 20;
    boolean[] isPrime = new boolean[N + 1];
    int[] primes = new int[N + 1];
    int pcnt = 0;
    private void computePrime() {
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i <= N ; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes[pcnt++] = i;
            }
        }
        Arrays.fill(dp[0],1);
        Arrays.fill(dp[1],1);
        for (int i = 2; i <= N>>1 ; i++) {
            for (int j = 0; j < pcnt; j++) {
                int k = i * primes[j];
                if (k <= 0 || k > N) {
                    break;
                }
                if (dp[0][i] + 1 > dp[0][k]) {
                    dp[0][k] = dp[0][i] + 1;
                    dp[1][k] = dp[1][i];
                } else if (dp[0][i] + 1 == dp[0][k]) {
                    dp[1][k] += dp[1][i];
                }

            }
        }
    }

}
