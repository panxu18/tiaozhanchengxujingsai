package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class LongLongMessage {
    public static void main(String[] args) {
        new LongLongMessage().solve();
    }

    String tempStr;
    private void solve() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in),200010);
        String s, t;
        try {
            s = bf.readLine();
            t  = bf.readLine();
        } catch (IOException e) {
            return;
        }
//        tempStr = s + '$' + t;
        char[] arr = new char[s.length() + t.length() + 1];
        char[] temp = s.toCharArray();
        System.arraycopy(temp, 0, arr, 0, s.length());
        temp = t.toCharArray();
        System.arraycopy(temp, 0, arr, s.length() + 1, t.length());
        // 计算高度数组
        constructSa(arr);
//        System.out.println(tempStr);
//        for (int a :
//                sa) {
//            System.out.println(tempStr.substring(a));
//        }
        constructLcp(arr);

        // 查找最大的前缀
        int ans = 0;
        for (int i = 0; i < len ; i++) {
            if ((sa[i] < s.length()) != (sa[i + 1] < s.length())) {
                ans = Math.max(ans, lcp[i]);
            }
        }
        System.out.println(ans);
    }

    Integer[] sa;
    int[] rank, temp;
    int[] lcp;
    int len, k;
    private void constructSa(char[] arr) {
        // init
        len = arr.length;
        sa = new Integer[len + 1];
        rank = new int[len + 1];
        temp = new int[len + 1];

        for (int i = 0; i <= len ; i++) {
            sa[i] = i;
            rank[i] = i < len ? arr[i] : -1; // 长度为1的子串顺序
        }

        for (k = 1; k <= len ; k <<= 1) {
            // 对长度为2*k的子串排序
            Arrays.sort(sa, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return compareSa(o1, o2);
                }
            });
            // 计算长度为2 * k的子串的rank
            temp[sa[0]] = 0;
            for (int i = 1; i <= len ; i++) {
                // compareSa(sa[i - 1], sa[i]) 一定为0或-1
                temp[sa[i]] = temp[sa[i - 1]] - compareSa(sa[i - 1], sa[i]);
            }
            System.arraycopy(temp, 0, rank, 0, len + 1);
        }
    }

    void constructLcp(char[] arr) {
        // init
        len = arr.length;
        lcp = new int[len + 1];
        for (int i = 0; i <= len ; i++) {
            rank[sa[i]] = i;
        }
        // 空串的高度默认为0
        int h = 0;
        for (int i = 0; i < len ; i++) {
            int j = sa[rank[i] - 1]; // 前一个后缀子串
            if (h > 0) h--;
            for (; j + h < len && i + h < len ; h++) {
                if (arr[j + h] != arr[i + h]) break;
            }
            lcp[rank[i] - 1] = h;
        }
    }

    int compareSa(int i, int j) {
        if (rank[i] != rank[j]) return compareInt(rank[i], rank[j]);
        int ri = i + k <= len ? rank[i + k] : -1;
        int rj = j + k <= len ? rank[j + k] : -1;
        return compareInt(ri, rj);
    }

    int compareInt(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }
}
