package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Scanner;

public class BestCowLine3617 {

    public static void main(String[] args) {
        new BestCowLine3617().solve();
    }

    int n;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        in.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(in.nextLine());
        }
        char[] arr = new char[n];
        char[] reArr = new char[n];
        sb.getChars(0, n, arr, 0);
        sb.reverse();
        sb.getChars(0, n, reArr, 0);

        char[] ans = new char[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {

            int mismatch;
            for (mismatch = 0; mismatch < n - i ; mismatch++) {
                if (arr[l + mismatch] != reArr[r + mismatch]) break;
            }
            if (mismatch < n - i && arr[l + mismatch] < reArr[r + mismatch]) {
                ans[i] = arr[l++];
            } else {
                ans[i] = reArr[r++];
            }
        }

        for (int i = 1; i <= n; i++) {
            out.print(ans[i - 1]);
            if (i % 80 == 0) out.println();
        }
        out.flush();
    }
}
