package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class KBest3111 {

    public static void main(String[] args) throws IOException {
        new KBest3111().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    double readDouble() throws IOException {
        if(canRead()) {
            return Double.parseDouble(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
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

    int MAXN = 100005;
    Item[] arr = new Item[MAXN];
    Item[] ans = new Item[MAXN];
    int N, K;
    private void solve() throws IOException {
        N = readInt();
        K = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = new Item(readInt(), readInt(), i+1);
        }
        double lb = 0.0;
        double ub = 1000000.0;
        for (int i = 0; i < 60; i++) {
            double mid = (lb + ub) / 2;
            if (!check(mid)) {
                ub = mid;
            } else {
                lb = mid;
            }
        }
        for (int i = 0; i < K; i++) {
            if (i < K - 1) {
                out.printf("%d ", arr[i].id);
            } else {
                out.printf("%d\n", arr[i].id);
            }
        }
        out.flush();
    }
    private boolean check(double mid) {
        E = mid;
        Arrays.sort(arr, 0, N, comparator);
        double w = 0;
        double v = 0;
        for (int i = 0; i < K; i++) {
            w += arr[i].w;
            v += arr[i].v;
        }
        return v/w >= mid;
    }
    double E;
    Comparator<Item> comparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            double r = (o2.v - E * o2.w)  - (o1.v - E * o1.w);
            return r < 0 ? -1 : (r == 0 ? 0 : 1);
        }
    };

    class Item{
        double v,w;
        int id;
        Item(int v, int w, int id) {
            this.v = v;
            this.w = w;
            this.id = id;
        }
    }
}
