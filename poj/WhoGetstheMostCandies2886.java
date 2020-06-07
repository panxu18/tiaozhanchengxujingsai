package tiaozhanchengxujingsai.poj;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class WhoGetstheMostCandies2886 {

    PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        new WhoGetstheMostCandies2886().solve();
    }

    int MAXN = 500005;
    int N, K;
    int ans;
    String[] name = new String[MAXN];
    int[] val = new int[MAXN];
    int maxDiv, antiprime;
    // 反素数
    int[] antiprimes = {1,2,4,6,12,24,36,48,60,120,180,240,360,720,840,1260,1680,2520,5040,7560,10080,15120,20160,25200,27720,45360,50400,55440,83160,110880,166320,221760,277200,332640,498960,500001};
    //因子数打表
    int[] div = {1,2,3,4,6,
            8,9,10,12,16,
            18,20,24,30,32,
            36,40,48,60,64,
            72,80,84,90,96,
            100,108,120,128,144,
            160,168,180,192,200,
            1314521};

    private void solve() throws IOException {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            N = in.nextInt();
            K = in.nextInt();
            for (int i = 1; i <= N; i++) {
                name[i] = in.next();
                val[i] = in.nextInt();
            }
            int idx = upperBound(antiprimes, 0 , div.length, N);
            maxDiv = div[idx-1];
            antiprime = antiprimes[idx-1];
            ans = doSolve();
            out.printf("%s %d\n", name[ans], maxDiv);
        }

        out.flush();
    }

    private int upperBound(int[] arr, int s, int t, int key) {
        int lb = s, ub = t - 1;
        while (lb < ub){
            int mid = (lb + ub) >> 1;
            if (arr[mid] > key){
                ub = mid;
            } else {
                lb = mid+1;
            }
        }
        if (arr[ub] > key){
            return ub;
        } else {
            return ub +1;
        }
    }

    private int doSolve() {
        init();
        int out = K;
        int n = N;
        for (int i = 1; i < antiprime; i++) {
            n--;
            int kp = findK(out);
            add(kp, -1);
            if (val[kp] > 0) {
                out = ((out + val[kp] - 2) % n + n) % n + 1;
            } else {
                out = ((out + val[kp] - 1) % n + n)%n  +1;
            }
        }
        return findK(out);
    }

    private void init(){
        Arrays.fill(bit, 0);
        for (int i = 1; i <= N; i++) {
            add(i, 1);
        }
    }

    private int findK(int k) {
        int lb = 1, ub = N;
        while (lb < ub){
            int mid = (lb + ub)>>1;
            int t = sum(mid);
            if (t >= k){
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        return ub;
    }

    int[] bit = new int[MAXN];
    private int sum(int x) {
        int res = 0;
        while (x > 0){
            res += bit[x];
            x -= x & -x;
        }
        return res;
    }

    private void add(int x, int a){
        while (x <= N){
            bit[x] += a;
            x += x & -x;
        }
    }
}
