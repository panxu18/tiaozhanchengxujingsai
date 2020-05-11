package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AggressiveCows2456 {
    public static void main(String[] args) throws IOException {
        new AggressiveCows2456().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    double readDouble() throws IOException {
        if(canRead()) {
            return Double.parseDouble(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
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
    int[] arr = new int[MAXN];
    int N, C;
    private void solve() throws IOException {
        N = readInt();
        C = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }
        Arrays.sort(arr, 0, N);
        int lb = 0;
        int ub = 1000000000;
        while (lb < ub) {
            int mid = (lb + ub + 1) / 2;
            if (!check(mid)) {
                ub = mid - 1;
            } else {
                lb = mid;
            }
        }
        out.println(lb);
        out.flush();
    }

    private boolean check(int mid) {
        int num = 0;
        int last = -mid;
        for (int i = 0; i < N; i++) {
            if (arr[i] - last >= mid) {
                num++;
                last = arr[i];
            }
        }
        return num >= C;
    }

}
