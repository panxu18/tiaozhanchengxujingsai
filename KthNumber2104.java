package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class KthNumber2104 {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        new KthNumber2104().solve();
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
        if (canRead()) {
            return Long.parseLong(st.nextToken());
        }
        throw new NoSuchElementException();
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

    int MAXN = 100005;
    long INF = 1000000007;
    int B = 1000;
    int N, Q;
    long[] arr = new long[MAXN];
    long[] sortArr = new long[MAXN];
    long[][] buckets = new long[101][B];
    private void solve() throws IOException {
        N = readInt();
        Q = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = readLong();
        }

        for (int i = 0; i < N/B; i++) {
            System.arraycopy(arr, i * B, buckets[i], 0, B);
        }
        System.arraycopy(arr, 0, sortArr, 0, N);
        Arrays.sort(sortArr, 0, N);

        for (int i = 0; i < N/B; i++) {
            Arrays.sort(buckets[i], 0, B);
        }
        for (int i = 0; i < Q; i++) {
            int l = readInt() - 1;
            int r = readInt() - 1;
            int k = readInt();

            int lb = 0;
            int ub = N - 1;
            while (lb < ub) {
                int mid = (lb + ub) >> 1;
                if (check(l,r,sortArr[mid]) >= k){
                    ub = mid;
                } else {
                    lb = mid +1;
                }
            }
            out.println(sortArr[ub]);
        }
        out.flush();
    }

    // 小于等于a的数目
    private int check(int l, int r, long a) {
        int res = 0;
        while (l <= r && l % B != 0) {
            if (arr[l++] <= a){
                res++;
            }
        }

        while (l <= r && r % B != B-1) {
            if (arr[r--] <= a){
                res++;
            }
        }
        while (l <= r){
            int i = l / B;
            res += upperBound(buckets[i], 0, B, a);
            l += B;
        }
        return res;
    }

    int upperBound(long[] a, int l, int r, long key){
        int lb = l;
        int ub = r - 1;
        while (lb < ub) {
            int mid = (lb + ub) >> 1;
            if (a[mid] > key) {
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        if (a[ub] > key) {
            return ub;
        }else {
            return ub + 1;
        }
    }

}
