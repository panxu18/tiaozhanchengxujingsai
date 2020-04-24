package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class YinBiWenTi {
    public static void main(String[] args) {
        new YinBiWenTi().solve();
    }

    int n, c;
    int[] v = new int[25];
    int[] b = new int[25];
    Integer[] id = new Integer[25];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        c = in.nextInt();
        for (int i = 0; i < n; i++) {
            id[i] = i;
            v[i] = in.nextInt();
            b[i] = in.nextInt();
        }

        Arrays.sort(id, 0, n, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return compareCoins(o1, o2);
            }
        });
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (c >= v[id[i]]) {
                int t = Math.min(c / v[id[i]], b[id[i]]);
                count += t;
                c = c - t * v[id[i]];
            }
        }
        out.println(count);
        out.flush();
    }

    private int compareCoins(int i, int j) {
        return v[j] < v[i] ? -1 : (v[j] == v[i] ? 0 : 1);
    }
}
