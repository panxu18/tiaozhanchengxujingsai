package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RectangularCovering2836 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new RectangularCovering2836().solve();
    }

    int MAXN = 15;
    int N, M;
    int[] dp = new int[1<<MAXN];
    int[] X = new int[MAXN];
    int[] Y = new int[MAXN];
    int INF = Integer.MAX_VALUE;
    ArrayList<Rec> recs = new ArrayList<Rec>();
    private void solve() throws IOException {
        while ((N = read()) != 0){
            for (int i = 0; i < N; i++) {
                X[i] = read();
                Y[i] = read();
            }
            recs.clear();
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    Rec rec = new Rec(X[i], Y[i], X[j], Y[j]);
                    for (int k = 0; k < N; k++) {
                        rec.add(X[k],Y[k], k);
                    }
                    recs.add(rec);
                }
            }
            Arrays.fill(dp, INF);
            dp[0] = 0;
            for (int s = 0; s < 1 << N; s++) {
                if (dp[s]!=INF){
                    for (Rec rec : recs) {
                        if((s|rec.cover) != s){
                            dp[s|rec.cover] = min(dp[s|rec.cover], dp[s] + rec.area);
                        }
                    }
                }
            }
            out.println(dp[(1<<N)-1]);
        }
        out.flush();
    }

    class Rec{
        int x1, y1, x2, y2, area;
        int cover = 0;

        public Rec(int x1, int y1, int x2, int y2) {
            this.x1 = min(x1, x2);
            this.y1 = min(y1, y2);
            this.x2 = max(x1, x2);
            this.y2 = max(y1, y2);
            this.area = max(1, (this.x2-this.x1)) * max(1, this.y2-this.y1);
        }


        public void add(int x, int y, int k) {
            if (x<=x2 && x >= x1
                    && y <= y2 && y >= y1){
                cover|=1<<k;
            }
        }
    }
}
