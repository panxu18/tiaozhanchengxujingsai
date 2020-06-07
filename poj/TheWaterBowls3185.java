package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

public class TheWaterBowls3185 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    long read() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new TheWaterBowls3185().solve();
    }

    int MAXN = 10000005;
    int INF = 1000000007;
    int ans = INF;
    int N = 20;
    boolean[] bowls  = new boolean[20];
    boolean[] flips  = new boolean[20];
    private void solve() throws IOException {
        for (int i = 0; i < 20; i++) {
            bowls[i] = read() == 0;
        }
        for (int s = 0; s < 1 << 1; s++) {
            int cnt = 0;
            Arrays.fill(flips, false);
            boolean f = false;
            if ((s&1)==1){
                f= flips[0] = true;
                cnt++;
            }
            for (int i = 1; i < 20; i++) {
                if (bowls[i-1] == f) {
                    flips[i] = true;
                    cnt++;
                }
                f^=flips[i];
                if (i >= 2){
                    f^=flips[i-2];
                }
            }
            if (bowls[N-1] != f){
                ans = min(ans, cnt);
            }
        }
        out.println(ans);
        out.flush();
    }
}
