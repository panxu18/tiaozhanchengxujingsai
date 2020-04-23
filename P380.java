package tiaozhanchengxujingsai;

import java.util.Arrays;



public class P380 {
    public static void main(String[] args) {
        new P380().solve("yeshowmuchiloveyoumydearmotherreallyicannotbelieveit$yeaphowmuchiloveyoumydearmother", "aab");
    }

    Integer[] sa; // 后组数组
    int len, k;
    int[] rank, temp; //
    private void solve(String haystack, String needle) {
        sa = new Integer[haystack.length() + 1];
        rank = new int[haystack.length() + 1];
        temp = new int[haystack.length() + 1];
        constructSa(haystack);
        for (int a :
                sa) {
            System.out.println(haystack.substring(a));
        }

        System.out.println(contain(haystack, sa, needle));

    }

    private void reverse(int[] arr) {
    }

    /**
     * 计算后缀数组，根据长度为k的后缀排序结果，计算后缀长度为2k的后缀排序，
     *
     * @param s
     */
    void constructSa(String s) {
        len = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i <= len; i++) {
            sa[i] = i;
            rank[i] = i < len ? arr[i] : -1;
        }
        // 利用对长度为k的排序结果对长度为2k的排序
        for (k = 1; k <= len ; k <<= 1) {
            Arrays.sort(sa, (i, j)->compareSa(i, j));

            // 重新计算rank
            temp[sa[0]] = 0;
            for (int i = 1; i <= len ; i++) {
                temp[sa[i]] = temp[sa[i - 1]] - (compareSa(sa[i - 1], sa[i]));
            }
            for (int i = 0; i <= len; i++) {
                rank[i] = temp[i];
            }
        }

    }

    /**
     * 比较后缀的大小，后缀分为两部分依次比较,先比较前k位，再比较后k位
     * @param i
     * @param j
     * @return
     */
    int compareSa(int i, int j) {
        if (rank[i] != rank[j]) return compareInt(rank[i], rank[j]);
        int ri = i + k <= len ? rank[i + k] : -1;
        int rj = j + k <= len ? rank[j + k] : -1;
        return compareInt(ri, rj);
    }

    int compareInt(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    boolean contain(String s, Integer[] sa, String t) {
        int a = 0;
        int b = s.length();
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        while (b - 1> a) {
            int mid = (b + a) >> 1;
            int ans = Arrays.compare(arr1, sa[mid], Math.min(sa[mid] + t.length(), s.length()), arr2, 0, t.length());
            if (ans < 0) a = mid;
            else b = mid;
        }
        return Arrays.compare(arr1, sa[b], Math.min(sa[b] + t.length(), s.length()), arr2, 0, t.length()) == 0;
    }

    int[] lcp;
    /**
     * 计算高度数组
     * @param s
     */
    void constructLCP(String s) {
        // 初始化
        int h = 0;
        lcp = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            rank[sa[i]] = i; // 查找子串i在后缀数组中的索引
        }
        char[] arr = s.toCharArray();
        // 空串在最后一个,空串的高度为0
        for (int i = 0; i < len ; i++) {
            // 前一个后缀
            int j = sa[rank[i] - 1];
            if (h > 0) h--; // 前缀长度至少为h-1
            for (; j + h < len && i + h < len ; h++) {
                if (arr[j + h] != arr[i + h]) break;
            }
            lcp[rank[i] - 1] = h;
        }
    }


}
