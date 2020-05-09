package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class CableMaster1064 {
    public static void main(String[] args) throws IOException {
        new CableMaster1064().solve();
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

    int MAXN = 10005;
    double[] arr = new double[MAXN];
    int N, K;
    private void solve() throws IOException {
        N = readInt();
        K = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = -readDouble();
        }
        Arrays.sort(arr, 0, N);

        double l = 0.0;
        double r = 100000.0;
        for (int i = 0; i < 100; i++) {
            double m = (l + r) / 2;
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j] + m > 0) {
                    break;
                }
                sum += (int)(-arr[j] / m);
            }
            if (sum >= K) {
                l = m;
            } else {
                r = m;
            }
        }
        out.printf("%.2f\n", Math.floor(r*100)/100);
        out.flush();
    }
}
