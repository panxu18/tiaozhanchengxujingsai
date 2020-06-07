package tiaozhanchengxujingsai.poj;

import java.io.*;

public class Matrix3685 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    long readInt() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Matrix3685().solve();
    }

    long T, N, K, M;
    long MIN = -5000000000L;
    long MAX = 12500000000L;

    private void solve() throws IOException {
        T = readInt();
        for (int t = 0; t < T; t++) {
            N = readInt();
            M = readInt();
            long lb = MIN, ub = MAX;
            while (lb < ub){
                long mid = (lb + ub) >> 1;
                if (check(mid)){
                    ub = mid;
                } else{
                    lb = mid + 1;
                }
            }
            out.println(ub);
        }
        out.flush();
    }

    // 判断小于等于m的数的个数是否大于等于M，
    private boolean check(long m) {
        long sum = 0;
        // 每列单调递增
        for (long i = 1; i <= N; i++) {
            long a = 1;
            long b = 100000 + i;
            long c = i * i - 100000 * i - m;
            long delta = b * b - 4 * a * c;
            double x = (-b + Math.sqrt(delta))/2;
            long  _x = Math.max(1,Math.min(Math.round(x), N));
            if (_x * _x + b * _x + c <= 0){
                sum += _x;
            }else {
                sum += _x - 1;
            }
        }
        return sum >= M;
    }
}
