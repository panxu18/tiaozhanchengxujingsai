package tiaozhanchengxujingsai.poj;

import java.io.*;

public class MonthlyExpense3273 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int readInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new MonthlyExpense3273().solve();
    }

    int L,N,M, MAXN = 100005;
    int tot = 0;
    int[] arr = new int[MAXN];
    private void solve() throws IOException {
        N = readInt();
        M = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
            tot += arr[i];
        }
        int lb = 0, ub = tot;
        while (lb < ub){
            int mid = (lb + ub) >> 1;
            if (check(mid)){
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        out.println(ub);
        out.flush();
    }

    private boolean check(int d){
        int num = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > d) {
                return false;
            }
            if (sum + arr[i] > d) {
                num++;
                sum = arr[i];
            } else if (sum + arr[i] == d) {
                num++;
                sum = 0;
            } else {
                sum += arr[i];
            }
        }
        if (sum > 0){
            num++;
        }
        return num <= M;
    }
}
