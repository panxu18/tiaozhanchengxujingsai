package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MilkingTime3616 {
    public static void main(String[] args) throws IOException {
        new MilkingTime3616().solve();
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

    int N, M, R;
    long[] dp = new long[1005];
    Range[] ranges = new Range[1005];
    Range[] reRanges;
    void solve() throws IOException {
        N = readInt();
        M = readInt();
        R = readInt();
        for (int i = 1; i <= M; i++) {
            ranges[i] = new Range(i, readInt(), readInt(),readInt());
        }
        computeDP();
        out.println(dp[ranges[M].id]);
        out.flush();
    }



    long ans = 0L;
    // 前缀max
    private void computeDP() {
        reRanges = ranges.clone();
        Arrays.sort(ranges, 1, M + 1, new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
                return o1.s - o2.s;
            }
        });
        Arrays.sort(reRanges, 1, M + 1, new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
                return o1.e - o2.e;
            }
        });
        long max = 0L;
        int unCover = 1;
        long pre = 0L;
        for (int i = 1; i <= M; i++) {
            while (reRanges[unCover].e + R <= ranges[i].s) {
                max = Math.max(max, dp[reRanges[unCover++].id]);
            }
            dp[ranges[i].id] = ranges[i].w + max;
            ans = Math.max(ans, dp[ranges[i].id]);
        }
    }

    class Range{
        int id, s, e, w;

        Range(int id, int s, int e, int w) {
            this.id = id;
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}
