package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FoodChain1182 {

    public static void main(String[] args) {
        new FoodChain1182().solve();
    }

    int N, K, D, X, Y;
    int[] parent = new int[150005];
    int[] rank = new int[150005];
    void solve() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        try {
            String[] arr = in.readLine().split(" ");
            N = Integer.parseInt(arr[0]);
            K = Integer.parseInt(arr[1]);
            for (int i = 0; i <= 3 * N; i++) {
                parent[i] = i;
            }
            int ans = 0;
            for (int i = 0; i < K; i++) {
                arr = in.readLine().split(" ");
                D = Integer.parseInt(arr[0]);
                X = Integer.parseInt(arr[1]);
                Y = Integer.parseInt(arr[2]);
                if (check()) continue;
                ans++;
            }
            out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
    }


    private boolean check() {
        if (X > N || Y > N) return false;
        if (D == 1) {
            if (same(X, Y + N) || same(X, Y + 2 * N)) return false;
            union(X, Y);
            union(X + N, Y + N);
            union(X + 2 * N, Y + 2 * N);
        } else {
            if (same(X, Y) || same(Y, X + N)) return false;
            union(X, Y + N);
            union(X + N, Y + 2 * N);
            union(X + 2 * N, Y);
        }
        return true;
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else {
            parent[py] = px;
            if (rank[py] == rank[px]) rank[px]++;
        }
    }

    private int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    private boolean same(int x, int y) {
        return find(x) == find(y);
    }
}
