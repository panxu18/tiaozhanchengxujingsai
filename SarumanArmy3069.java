package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SarumanArmy3069 {

    public static void main(String[] args) {
        new SarumanArmy3069().solve();
    }

    int r, n;
    int[] arr = new int[1010];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (true){
            r = in.nextInt();
            n = in.nextInt();
            if (r == -1 && n == -1) break;
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            Arrays.sort(arr, 0, n);
            int ans = 0;
            int start = 0;
            while (start < n) {
                ans++;
                int i = start;
                while (i < n && arr[i] - arr[start] <= r) i++;
                start = i - 1;
                i = start;
                while (i < n && arr[i] - arr[start] <= r) i++;
                start = i;
            }
            out.println(ans);

        }

        out.flush();
    }
}
