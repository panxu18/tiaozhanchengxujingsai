package tiaozhanchengxujingsai.poj;

import java.io.*;

public class Garland1759 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    double read() throws IOException {
        in.nextToken();
        return in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Garland1759().solve();
    }

    int  N;
    double A, P;
    int MAXN = 1005;
    int INF = 1000000007;
    double ans = 0;
    private void solve() throws IOException {
        N = (int) read();
        A = read();
        double lb = 0, ub = A;
        for (int i = 0; i < 100; i++) {
            double mid = (lb+ub)/2;
            if (check(mid)){
                ub = mid;
            } else {
                lb = mid;
            }
        }
        out.printf("%.2f\n", ans);
        out.flush();
    }

    // 判断是否全部高度都不小于0
    private boolean check(double h) {
        double a = A, b = h;
        for (int i = 3; i <= N; i++) {
            double temp = b;
            // 变形后的递推公式
            b = 2 * b - a + 2;
            a = temp;
            if (b < 0) {
                return false;
            }
        }
        ans = b;
        return true;
    }
}
