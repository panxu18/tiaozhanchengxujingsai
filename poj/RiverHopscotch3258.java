package tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

public class RiverHopscotch3258 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int readInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new RiverHopscotch3258().solve();
    }


    int L,N,M, MAXN = 50005;
    int[] arr = new int[MAXN];
    private void solve() throws IOException {
        L = readInt();
        N = readInt();
        M = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }
        Arrays.sort(arr, 0, N);
        arr[N] = L;
        int lb = 0, ub = L;
        while (lb < ub){
            int mid = (lb + ub + 1) >> 1;
            if (check(mid)){
                lb = mid;
            } else {
                ub = mid - 1;
            }
        }
        out.println(ub);
        out.flush();
    }

    private boolean check(int d){
        int num = 0, cur = 0;
        for (int i = 0; i <= N; i++) {
            if (arr[i] - cur < d) {
                num++;
            } else {
                cur = arr[i];
            }
        }
        return  num <= M;
    }
}
