package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SmallestDifference2718 {

    public static void main(String[] args) throws IOException {
        new SmallestDifference2718().solve();
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

    int T;
    int[] arr = new int[10];
    void solve() throws IOException {
        setPreZreo();
        T = readInt();
        for (int i = 0; i < T; i++) {
            Arrays.fill(arr, 0);
            String[] strs = readLine().split(" ");
            int len = strs.length;
            for (int j = 0; j < len; j++) {
                arr[j] = Integer.parseInt(strs[j]);
            }
            if (len == 10) {
                out.println(247);
            } else if (len % 2 == 1){
                out.println(fastAns(len));
            } else {

                out.println(getAns(len));
            }
        }

        out.flush();
    }

    int[] preZreo = new int[10];
    private void setPreZreo() {
        int m = 10;
        for (int i = 2; i < 10; i++) {
            preZreo[i] = m - 1;
            m *= 10;
        }
    }

    private long fastAns(int len) {
        int lenLeft = (len + 1) / 2;
        Arrays.sort(arr, 0, len);
        long left, right;
        if ((len & 1) == 0) {
            swap(arr, lenLeft - 1, lenLeft);
            reverse(arr, 0, lenLeft);
            reverse(arr, 1, lenLeft);
            reverse(arr, lenLeft + 1, len);
            left = arr2Long(arr, 0, lenLeft);
            right = arr2Long(arr, lenLeft, len);
        } else {
            if (arr[0] == 0) {
                swap(arr, 0, 1);
            }
            left = arr2Long(arr, 0, lenLeft);
            reverse(arr, lenLeft, len);
            right = arr2Long(arr, lenLeft, len);
        }
        return left - right;
    }

    long ans1, ans2;
    private long getAns(int len) {

        long ans = Long.MAX_VALUE;
        Arrays.sort(arr, 0, len);

        if (len == 2 && arr[0] == 0) {
            return arr[1];
        }
        long a = arr2Long(arr, 0, len);
        Permutation permutation = new Permutation(arr, 0, len);
        for (; a != -1; a = permutation.next()) {
//            out.println(a);
            int m = 10;
            for (int i = 1; i < len; i++) {
                long left = a / m;
                long right = a % m;
                m *= 10;
                if (right > preZreo[i] && left > preZreo[len - i]) {
                    if (Math.abs(left - right) < ans) {

                        ans = Math.abs(left - right);
                        ans1 = left;
                        ans2 = right;
                    }
                }
            }
        }
        return ans;
    }

    class Permutation{
        final int[] list;

        Permutation (int[] list) {
            this.list = list.clone();
        }

        Permutation(int[] list, int s, int t) {
            this.list = new int[t - s];
            System.arraycopy(list, s, this.list, 0, t - s);
        }


        long next() {

            int p = list.length - 1;
            int end = list.length - 1;
            int max = 0;
            while (p > 0) {
                int q = p;
                p--;
                if (list[p] < list[q]) {
                    max = findMax(list, p, list.length);
                    swap(list, p, max);
                    reverse(list, q, list.length);
                    return arr2Long(list, 0, list.length);
                }
            }
            return -1;
        }

    }


    // 开区间
    long arr2Long(int[] list, int s, int t){
        long res = 0L;
        while (s < t) {
            res = res * 10 + list[s++];
        }
        return res;
    }

    // 开区间
    int findMax(int[] list, int s, int t) {
        t--;
        while (t > s && list[t] <= list[s]) {
            t--;
        }
        return t;
    }

    /**
     * 翻转
     * @param list
     * @param s 开始索引
     * @param t 结束索引，不包括
     */
    void reverse(int[] list, int s, int t) {
        t--;
        while (s < t) {
            swap(list, s, t);
            s++;
            t--;
        }
    }

    private void swap(int[] list, int a, int b) {
        int t = list[a];
        list[a] = list[b];
        list[b] = t;
    }
}
