package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RadarInstallation1328 {
    public static void main(String[] args) throws IOException {
        new RadarInstallation1328().solve();
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

    int N, D;
    Range[] islands = new Range[1005];
    void solve() throws IOException {
        int round = 0;
        while (true) {
            round++;
            N = readInt();
            D = readInt();
            if (N == 0 && D == 0) {
                break;
            }
            boolean flag = true;
            if (D < 0) {
                flag = false;
            }
            for (int i = 0; i < N; i++) {
                int x = readInt();
                int y = readInt();
                if (flag) {
                    islands[i] = getRange(x, y, D);
                    if (islands[i] == null) flag = false;
                }
            }
            if (flag) {
                Arrays.sort(islands, 0 , N);
                double t = Double.NEGATIVE_INFINITY;
                int ans = 0;
                for (int i = 0; i < N; i++) {
                    if (islands[i].x > t) {
                        t = islands[i].y;
                        ans++;
                    }
                }
                out.printf("Case %d: %d\n",round, ans);
            } else {
                out.printf("Case %d: %d\n",round, -1);
            }
        }
        out.flush();

    }

    Range getRange(long x, long y, long d) {
        long r2 = d * d - y * y;
        if (r2 < 0) return null;
        double r = Math.sqrt(d * d - y * y);
        return new Range(x - r, x + r);
    }


    class Range implements Comparable<Range>{
        double x;
        double y;

        Range(double x, double y){
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Range o) {
            if (y == o.y) {
                return x < o.x ? -1 : (x == o.x ? 0 : 1);
            }
            return y < o.y ? -1 : 1;
        }
    }

}
