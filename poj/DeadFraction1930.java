package tiaozhanchengxujingsai.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;

public class DeadFraction1930 {

    public static void main(String[] args) throws IOException {
        new DeadFraction1930().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    long readLong() throws IOException {
        if(canRead()) {
            return Long.parseLong(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    String readLine() throws IOException {
        String s;
        if (st != null &&st.hasMoreTokens()) {
            s = st.nextToken("");
        } else {
            s = in.readLine();
        }
        if (s == null) {
            throw new NoSuchElementException();
        }
        st = null;
        return s;

    }

    char readChar() throws IOException {
        if (canRead()) {
            return st.nextToken().charAt(0);
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

    char[] arr = new char[1000];
    Random random = new Random();
    private void solve() throws IOException {
        while (canRead()) {
            String s = readLine();
            String[] strings = s.split("\\.");
            if (strings.length == 1) {
                break;
            }
            int len = strings[1].length();
            strings[1].getChars(0, len, arr, 0);
            long a = Long.MAX_VALUE;
            long b = Long.MAX_VALUE;
            long all = Long.parseLong(strings[1]);// 小数全部位的值
            long num = 0; // 不循环部分的值
            long k = 1; // 不循环部分的长度
            long c = pow(10, len); // 总长度
            for (int i = 0; i < len ; i++) {
                long aa = all - num;
                long bb = c - k;
                long g = gcd(aa, bb);
                if (bb / g < b) {
                    b = bb/g;
                    a = aa/g;
                }
                num = num * 10 + (arr[i] - '0');
                k *= 10;
            }
            out.printf("%d/%d\n", a, b);
        }

        out.flush();
    }

    private long gcd(long a, long b) {
        if (b == 0){
            return a;
        }
        return gcd(b, a %b);
    }

    long pow(long a, long b) {
        long res = 1L;
        while (b > 0) {
            if ((b&1)==1) {
                res *= a;
            }
            a *= a;
            b>>=1;
        }
        return res;
    }
}
