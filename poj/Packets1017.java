package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Packets1017 {

    public static void main(String[] args) throws IOException {
        new Packets1017().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Integer.parseInt(st.nextToken());
    }

    String readLine() throws IOException {
        return in.readLine();
    }

    int N, S;
    int[] arr = new int[7];
    int[] u = {0, 5, 3, 1};
    void solve() throws IOException {
        while (true) {
            boolean quite = true;
            for (int i = 0; i < 6; i++) {
                arr[i] = readInt();
                if (arr[i] != 0) {
                    quite = false;
                }
            }
            if (quite) {
                break;
            }

            int ans = arr[5] + arr[4] + arr[3] + (arr[2] + 3) / 4;
            int y = 5 * arr[3] + u[arr[2] % 4]; // 可以放2*2的区域
            if (arr[1] > y) {
                ans += (arr[1] - y + 8)/9;
            }
            int x = 36 * ans - 36 * arr[5] - 25 * arr[4] - 16 * arr[3] - 9 * arr[2] - 4 * arr[1];
            if (arr[0] > x) {
                ans += (arr[0] - x + 35) / 36;
            }
            out.println(ans);
        }

        out.flush();

    }
}
