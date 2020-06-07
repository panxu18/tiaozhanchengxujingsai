package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class Matrix2155 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    String readStr() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {
        new Matrix2155().solve();
    }

    int MAXN = 1005;
    int N, T, M;
    int[][] bit = new int[MAXN][MAXN];
    private void solve() throws IOException {
        T = read();
        for (int t = 0; t < T; t++) {
            N = read();
            M = read();
            init();
            for (int i = 0; i < M; i++) {
                String q = readStr();
                if ("C".equals(q)){
                    int x1 = read();
                    int y1 = read();
                    int x2 = read();
                    int y2 = read();
                    change(x1, y1, x2, y2);
                } else {
                    int x = read();
                    int y = read();
                    out.println(sum(x,y)&1);
                }
            }
            out.println();
        }
        out.flush();
    }

    private void change(int x1, int y1, int x2, int y2) {

        add(x1,y1, 1);
        add(x1,y2+1, 1);
        add(x2+1,y1, 1);
        add(x2+1,y2+1, 1);
    }

    // 二维树状数组
    private long sum(int x, int y){
        long res = 0;
        while (x > 0){
            int t = y;
            while (t > 0){
                res += bit[x][t];
                t -= t &-t;
            }
            x -= x&-x;
        }
        return res;
    }

    private void add(int x, int y, long a){
        while (x <= N){
            int t = y;
            while (t <= N){
                bit[x][t] += a;
                t += t & -t;
            }
            x += x & -x;
        }

    }

    private void init(){
        for (int i = 0; i < MAXN; i++) {
            Arrays.fill(bit[i],0, MAXN, 0);
        }

    }
}
