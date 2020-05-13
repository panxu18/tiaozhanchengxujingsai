package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Subset3977 {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        new Subset3977().solve();
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

    int MAXN = 40;
    long INF = 1000000007;
    int N;
    long[] arr = new long[MAXN];
    //    long ans, cnt;
    Pair ans = new Pair(1000, Long.MAX_VALUE);
    Pair keyPair = new Pair(0, Long.MIN_VALUE);
    private void solve() throws IOException {
        while (true){
            N = readInt();
            if (N == 0) {
                break;
            }
            for (int i = 0; i < N; i++) {
                arr[i] = readLong();
            }
            ans = new Pair(1,arr[0]);
            doSolve();
            out.printf("%d %d\n",ans.s, ans.num);
        }
        out.flush();
    }

    Pair[] arrHalf = new Pair[1 << 20];
    private void doSolve() {
        int half = N / 2;
        for (int i = 1; i < (1 << half); i++) {
            long sum = 0;
            int num = 0;
            for (int j = 0; j < half; j++) {
                if ((i >> j & 1) == 1) {
                    sum += arr[half - j - 1];
                    num++;
                }
            }
            arrHalf[i] = new Pair(num, sum);
            updateAns(sum, num);
        }
        Arrays.sort(arrHalf, 1, 1<<half);
        for (int i = 1; i < 1 << (N - half); i++) {
            long sum = 0;
            int num = 0;
            for (int j = 0; j < (N - half); j++) {
                if ((i >> j & 1)== 1) {
                    num++;
                    sum += arr[N - 1 - j];
                }
            }
            updateAns(sum, num);
            keyPair.s = -sum;
            int idx = Arrays.binarySearch(arrHalf, 1, 1 << half, keyPair);
            idx = -idx - 1;
            if (idx < 1<<half) {
                updateAns(sum + arrHalf[idx].s, num + arrHalf[idx].num);
            }
            if (idx > 1) {
                updateAns(sum + arrHalf[idx-1].s, num + arrHalf[idx-1].num);
            }
        }
    }

    void updateAns(long s, int num) {
        s = abs(s);
        if (s < ans.s || (s == ans.s && num < ans.num)) {
            ans.s = s;
            ans.num = num;
        }
    }

    class Pair implements Comparable<Pair> {
        long s;
        int num;

        Pair(int num, long s) {
            this.num = num;
            this.s = s;
        }

        @Override
        public int compareTo(Pair o) {
            if (s == o.s) {
                return num - o.num;
            }
            return s < o.s ? -1 :  1;
        }
    }
}
