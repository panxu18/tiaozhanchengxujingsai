package tiaozhanchengxujingsai.poj;

import java.io.*;

public class ArrangeTheBulls2441 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new ArrangeTheBulls2441().solve();
    }

    int MAXN = 20;
    int N, M;
    int[][] arr = new int[MAXN][MAXN+1];
    int[] dp = new int[1<<MAXN];
    int ans = 0;
    private void solve() throws IOException {
        N = read();
        M = read();
        for (int i = 0; i < N; i++) {
            arr[i][0] = read();
            for (int j = 1; j <= arr[i][0]; j++) {
                arr[i][j] = read()-1;
            }
        }
        doSolve();
        out.println(ans);
        out.flush();
    }

    private void doSolve() {
        for (int i = 1; i <= arr[0][0]; i++) {
            dp[1<<arr[0][i]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= arr[i][0]; j++) {
                for (int comb = (1<<i)-1, x, y; comb < 1<<M ; x = comb &-comb, y=comb+x, comb=(comb&~y)/x>>1|y) {
                    if ((comb>>arr[i][j]&1) == 0){
                        dp[1<<arr[i][j]|comb] += dp[comb];
                    }
                }
            }
        }
        for (int comb = (1<<N)-1, x, y; comb < 1<<M ; x = comb &-comb, y=comb+x, comb=(comb&~y)/x>>1|y) {
            ans+=dp[comb];
        }
    }
}
