package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Ball_A0033 {

    public static void main(String[] args) throws IOException {
        new Ball_A0033().solve();
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

    String next() throws IOException {
        while (st == null  || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken();
    }

    long N;
    char[][] arr = new char[105][2105];
    boolean[][] vis = new boolean[105][2105];
    void solve() throws IOException {
        N = readInt();
        out: for (int i = 0; i < N; i++) {
            String[] strs = in.readLine().split(" ");
            int[] arr = new int[strs.length];
            for (int j = 0; j < strs.length; j++) {
                arr[j] = Integer.parseInt(strs[j]);
            }
            int l = 0, r = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > l) {
                    l = arr[j];
                } else if (arr[j] > r) {
                    r = arr[j];
                } else {
                    out.println("NO");
                    continue out;
                }
            }
            out.println("YES");
        }
        out.flush();
    }
}
