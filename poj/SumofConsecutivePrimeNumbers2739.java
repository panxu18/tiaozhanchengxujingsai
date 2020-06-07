package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class SumofConsecutivePrimeNumbers2739 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new SumofConsecutivePrimeNumbers2739().solve();
    }

    int  N, K;
    int MAXN = 10005;
    long INF = Long.MAX_VALUE;
    long ans;
    int[] primes = new int[MAXN];
    int pcnt;
    boolean[] isPrimes = new boolean[MAXN];
    private void solve() throws IOException {
        primes();
        while (true){
            N = read();
            if (N == 0){
                break;
            }
            ans = doSolve(N);
            out.println(ans);
        }

        out.flush();
    }

    private long doSolve(int n) {
        int sum = 0, cnt = 0;
        int head = 0, tail = 0;
        while (tail < pcnt) {
            if (sum > n){
                sum -= primes[head++];
            } else if (sum < n){
                sum += primes[tail++];
            } else {
                cnt++;
                sum -= primes[head++];
            }
            if (head > tail && tail < pcnt){
                sum += primes[tail++];
            }
        }
        return cnt;
    }

    private void primes(){
        Arrays.fill(isPrimes, true);
        for (int i = 2; i * i< MAXN; i++) {
            if (isPrimes[i]){

                for (int j = i<<1; j < MAXN; j+=i) {
                    isPrimes[j] = false;
                }
            }
        }
        for (int i = 2; i < MAXN; i++) {
            if (isPrimes[i]) {
                primes[pcnt++] = i;
            }
        }
    }
}
