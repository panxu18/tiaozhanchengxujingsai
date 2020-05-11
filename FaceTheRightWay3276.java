package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class FaceTheRightWay3276 {

    public static void main(String[] args) throws IOException {
        new FaceTheRightWay3276().solve();
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

    int INF = 1000000007;
    int MAXN = 50005;
    int T, N, S;
    boolean[] arr = new boolean[MAXN];
    int ans;
    Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
    private void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            char c = readChar();
            arr[i] = c == 'F';
        }
        ans = INF;
        int k = INF;
        for (int i = 1; i <= N; i++) {
            int m = check(i);
            if (m < ans) {
                ans = m;
                k = i;
            }
        }
        out.printf("%d %d\n", k, ans);
        out.flush();
    }

    boolean[] flip = new boolean[MAXN];
    private int check(int k){
        Arrays.fill(flip, false);
        int head = 0, tail = 0;
        boolean cnt = false;
        for (int i = 0; i < N - k + 1; i++) {
            flip[tail] = cnt == arr[i];
            cnt ^= flip[tail++];
            if (tail - head == k) {
                cnt ^= flip[head++];
            }
        }
        for (int i = N - k + 1; i < N; i++) {
            if (cnt == arr[i]) {
                return INF;
            }
            if (++tail - head == k) {
                cnt ^= flip[head++];
            }
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (flip[i]) {
                sum++;
            }
        }
        return sum;
    }
}
