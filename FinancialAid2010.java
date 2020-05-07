package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FinancialAid2010 {
    public static void main(String[] args) throws IOException {
        new FinancialAid2010().solve();
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
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), "");
        }

        return st.nextToken();
    }

    char readChar() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken().charAt(0);
    }

    int N, C, F;
    int ans = 0;
    Cow[] cows = new Cow[100005];
    int[] farr = new int[100005];
    int[] leftF = new int[100005];
    int[] rightF = new int[100005];
    private void solve() throws IOException {
        N = readInt();
        C = readInt();
        F = readInt();
        for (int i = 0; i < C; i++) {
            cows[i] = new Cow(readInt(), readInt());
        }

        Arrays.sort(cows, 0, C);
        for (int i = 0; i < C; i++) {
            farr[i] = -cows[i].f;
        }
        sumOfTopN(farr, leftF, N>>1, C);
        reverse(farr, 0, C);
        sumOfTopN(farr, rightF, N>>1, C);
        reverse(rightF, 0, C);
        int len = N >> 1;
        ans = -1;
        for (int i = C - len - 1; i >= len ; i--) {
            if (leftF[i] + rightF[i] + cows[i].f <= F) {
                ans = cows[i].c;
                break;
            }
        }
        out.println(ans);
        out.flush();
    }

    private void reverse(int[] arr, int s, int t) {
        t--;
        while (s < t){
            int temp = arr[s];
            arr[s++] = arr[t];
            arr[t--] = temp;
        }
    }

    void sumOfTopN(int[] list, int[] des, int n, int len) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            des[i] = -sum; // 不包含当前项的前n项
            minHeap.add(list[i]);
            sum += list[i];
            if (i >= n) {
                sum -= minHeap.poll();
            }
        }
    }

    class Cow implements Comparable<Cow>{
        int c,f;

        Cow(int c, int f){
            this.c = c;
            this.f = f;
        }

        @Override
        public int compareTo(Cow o) {
            return c - o.c;
        }
    }
}
