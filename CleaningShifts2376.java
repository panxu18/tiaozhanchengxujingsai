package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CleaningShifts2376 {

    public static void main(String[] args) throws IOException {
        new CleaningShifts2376().solve();
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

    int N, T;
    Range[] arr = new Range[25005];
    int ans = 0;
    void solve() throws IOException {
        N = readInt();
        T = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = new Range(readInt(), readInt());
        }

        Arrays.sort(arr, 0, N);
        int s = 1;
        int t = 0;
        int i = 0;
        while (s <= T) {
            if (i < N && arr[i].l <= s) {
                t = Math.max(t, arr[i++].r);
            } else if (t < s){
                break;
            } else {
                s = t + 1;
                ans++;
            }
        }
        out.println(s > T ? ans : -1);
        out.flush();

    }

    class Range implements Comparable<Range>{
        int l, r;

        Range(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Range o) {
            if (l == o.l) {
                return o.r - r;
            }
            return l - o.l;
        }
    }
}
