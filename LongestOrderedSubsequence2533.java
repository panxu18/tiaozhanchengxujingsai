package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestOrderedSubsequence2533 {

    public static void main(String[] args) throws IOException {
        new LongestOrderedSubsequence2533().solve();
    }

    /**
     * 最大上升子序列长度
     * @param arr 序列
     * @return
     */
    private int getLongestSeq(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];
        int[] ans = new int[arr.length];
        int ansLen = -1;
        for (int i = 0; i < arr.length; i++) {
            int j = Arrays.binarySearch(ans, 0, ansLen + 1, arr[i]);
            if (j < 0) {
                j = -j - 1;
                ans[j] = arr[i];
                ansLen = Math.max(ansLen, j);
            }
        }
        return ansLen + 1;
    }


    private void solve() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getLongestSeq(arr));
    }


}
