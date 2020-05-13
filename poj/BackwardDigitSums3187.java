package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BackwardDigitSums3187 {

    public static void main(String[] args) throws IOException {
        new BackwardDigitSums3187().solve();
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

    int N, SUM;
    int[] arr = new int[15];
    int[][] dp = new int[15][15];
    void solve() throws IOException {
        N = readInt();
        SUM = readInt();
        preSolve();
        for (int i = 0; i < N ; i++) {
            arr[i] = i + 1;
        }
        Permutation perm = new Permutation(arr, 0, N);
        do {
            if (compute(arr, N) == SUM){
                for (int i = 0; i < N; i++) {
                    out.printf("%d",arr[i]);
                    if (i + 1 < N) {
                        out.print(" ");
                    }
                }
                out.print("\n");
                break;
            }
        } while (perm.next());
        out.flush();
    }

    private long compute(int[] arr, int n) {
        int sum = 0;
        for (int i = 0; i <n ; i++) {
            sum += dp[n][i + 1] * arr[i];
        }
        return sum;
    }

    private void preSolve() {
        dp[1][1] = 1;
        for (int i = 1; i <14 ; i++) {
            for (int j = 1; j < 15; j++) {
                dp[i + 1][j] = dp[i][j] + dp[i][j - 1];
            }
        }
    }

    class Permutation{
        final int[] list;
        final int s;
        final int t;

        Permutation(int[] list, int s, int t) {
            this.list = list;
            this.s = s;
            this.t = t;
        }

        private void swap(int a, int b) {
            int v = list[a];
            list[a] = list[b];
            list[b] = v;
        }

        private void reverse(int a, int b) {
            while (a < b) {
                swap(a++, --b);
            }
        }

        boolean next() {
            int max = t - 1;
            int p = t - 1;
            while (p < s) {
                int q = p--;
                if (list[p] < list[q]) {
                    while (list[max] <= list[p]) max--;
                    swap(p, max);
                    reverse(q, t);
                    return true;
                }
            }
            return false;
        }
    }

}
