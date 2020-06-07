package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class Sumsets2549 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Sumsets2549().solve();
    }

    int MAXN = 1005;
    int INF = 1000000007;
    long ans = Long.MIN_VALUE;
    int N ;
    long[] arr = new long[MAXN];
    Pair[] left = new Pair[MAXN*MAXN];
    int lcnt;
    private void solve() throws IOException {
        while (true){
            N = read();
            if (N == 0){
                break;
            }
            for (int i = 0; i < N; i++) {
                arr[i] = read();
            }
            ans = Long.MIN_VALUE;
            ans = doSolve();
            out.println(ans == Long.MIN_VALUE ? "no solution" : ans);
        }
        out.flush();
    }

    private long doSolve() {
        Arrays.sort(arr, 0, N);
        lcnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                left[lcnt++] = new Pair(i,j,arr[i] + arr[j]);
            }
        }
        Arrays.sort(left, 0, lcnt);
        for (int i = N-1; i >= 0 ; i--) {
            for (int j = N-1; j >=0 ; j--) {
                if (i == j) {
                    continue;
                }
                long dif = arr[i]-arr[j];
                int ind = lowerBound(left, dif);
                while (ind < lcnt && left[ind].sum == dif){
                    if (left[ind].i != i && left[ind].i != j
                            && left[ind].j != i && left[ind].j!= j){
                        return arr[i];
                    }
                    ind++;
                }
            }

        }
        return Long.MIN_VALUE;

    }

    private int lowerBound(Pair[] list, long key) {
        int lb = 0, ub = lcnt-1;
        while (lb < ub){
            int mid = (lb + ub)>>1;
            if (list[mid].sum >= key){
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        if (list[ub].sum>=key) {
            return ub;
        } else {
            return ub+1;
        }
    }

    class Pair implements Comparable<Pair>{
        int i,j;
        long sum;

        public Pair(int i, int j, long sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }

        @Override
        public int compareTo(Pair o) {
            return sum < o.sum ? -1 : (sum == o.sum ? 0 : 1);
        }
    }
}
