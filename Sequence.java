package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sequence {

    public static void main(String[] args) {
        new Sequence().solve();
    }

    private void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        in.close();
       // 计算第一段
        reverse(arr);
        constrctSa(arr);
        int len1 = 0;
        for (int i = 1; i <= len ; i++) {
            if (sa[i] > 1) {
                len1 = sa[i];
                for (int j = sa[i]; j < n ; j++) {
                    out.println(arr[j]);
                }
                break;
            }
        }
        // 计算剩余两段
        long[] arr2 = new long[2 * len1];
        for (int i = 0; i < len1 ; i++) {
            arr2[i] = arr2[i + len1] = arr[i];
        }
        constrctSa(arr2);
        for (int i = 1; i <= len; i++) {
            // 两段都不为空
            if(sa[i] > 0 && sa[i] < len1) {
                for (int j = sa[i]; j < sa[i] + len1 ; j++) {
                    out.println(arr2[j]);
                }
                break;
            }
        }
        out.flush();
    }

    long[] temp, rank;
    int len, k;
    int[] aux;
    Integer[] sa;

    /**
     * 计算arr的后缀数组
     * @param arr 输入序列
     */
    private void constrctSa(long[] arr) {
        // 初始化
        len = arr.length;
        sa = new Integer[len + 1];
//        sa = new int[len + 1];
        aux = new int[len + 1];
        temp = new long[len + 1];
        rank = new long[len + 1];

        for (int i = 0; i <= len ; i++) {
            sa[i] = i;
            rank[i] = i < len ? arr[i] : Long.MIN_VALUE;
        }

        for (k = 1; k <= len ; k <<= 1) {
            // 通过长度为k的rank将长度为2 * k的子串排序
//            System.arraycopy(sa, 0, aux, 0, len + 1);
//            mergeSort(aux, sa, 0, len + 1, 0);
            Arrays.sort(sa, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return compareSa(o1, o2);
                }
            });

            // 计算2*k的rank
            temp[sa[0]] = 0;
            for (int i = 1; i <= len ; i++) {
                temp[sa[i]] = temp[sa[i - 1]] - compareSa(sa[i - 1], sa[i]);
            }
            if (len >= 0) System.arraycopy(temp, 0, rank, 0, len + 1);
        }
    }

    private int compareSa(int i, int j) {
        if (rank[i] != rank[j]) return compareLong(rank[i], rank[j]);
        long ri = i + k <= len ? rank[i + k] : Long.MIN_VALUE;
        long rj = j + k <= len ? rank[j + k] : Long.MIN_VALUE;
        return compareLong(ri, rj);
    }

    private void reverse(long[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            long c = arr[i];
            arr[i++] = arr[j];
            arr[j--] = c;
        }
    }

    void mergeSort(int[] src,
                   int[] dest,
                   int low,
                   int high,
                   int off) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < 7) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low &&
                        compareSa(dest[j-1], dest[j])>0; j--)
                    swap(dest, j, j-1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        if (compareSa(src[mid - 1], src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && compareSa(src[p], src[q])<=0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    int compareLong(long x, long y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }
}
