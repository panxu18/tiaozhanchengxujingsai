package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class Crane2991 {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        new Crane2991().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    long readLong() throws IOException {
        if(canRead()) {
            return Long.parseLong(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    boolean canRead() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = in.readLine();
            if (s != null) {
                st = new StringTokenizer(s, " ");
            } else {
                return false;
            }
        }
        return true;
    }

    int MAXN = 10005;
    long INF = 1000000007;
    int N, C;
    int[] line = new int[MAXN];
    double[] pre = new double[MAXN];
    private void solve() throws IOException {
        while (canRead()) {
            N = readInt();
            C = readInt();
            for (int i = 0; i < N; i++) {
                line[i] = readInt();
            }
            Arrays.fill(pre, PI);
            build(1,0, N);
            for (int i = 0; i < C; i++) {
                int s = readInt();
                double ang = readInt() / 360.0 * 2.0 * PI;
                change(s, ang-pre[s], 1, 0, N);
                pre[s] = ang;
                out.printf("%.2f %.2f\n", tree[1].x, tree[1].y);
            }
        }
        out.flush();
    }
    Node[] tree = new Node[MAXN<<2];
    void build(int v, int s, int t){
        if (s + 1 == t) {
            tree[v] = new Node(0, line[s], 0);
            return;
        }

        build(v<<1, s, (s + t)/2);
        build(v << 1|1 , (s + t)/2, t);
        tree[v] = new Node(0,0, 0);
        up(v);
    }

    // 旋转坐标系
    private void up(int v) {
        double ang = tree[v].ang;
        tree[v].x = tree[v<<1].x + tree[v<<1|1].x * cos(ang) - tree[v<<1|1].y * sin(ang);
        tree[v].y = tree[v<<1].y + tree[v<<1|1].x * sin(ang) + tree[v<<1|1].y * cos(ang);
    }


    // 旋转点s
    void change(int s, double ang, int v, int l, int r) {
        if (r > s && l < s) {
            int m = (l + r)/ 2;
            change(s, ang, v<<1, l, m);
            change(s, ang, v<<1|1, m, r);
            if (s <= m) {
                tree[v].ang += ang;
            }
            up(v);
        }
    }

    class Node{
        double x, y, ang;
        Node(double x, double y, double ang) {
            this.x = x;
            this.y = y;
            this.ang = ang;
        }
    }
}
