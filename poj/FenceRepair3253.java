package tiaozhanchengxujingsai.poj;

import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FenceRepair3253 {

    public static void main(String[] args) {
        new FenceRepair3253().solve();
    }

    int n;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        PriorityQueue<Long> minHeap = new PriorityQueue<Long>();
        for (int i = 0; i < n; i++) {
            long l = in.nextInt();
            minHeap.offer(l);
        }
        long ans = 0;
        for (int i = 1; i < n; i++) {
            // 取出最短的两块木板拼接
            long c = minHeap.poll() + minHeap.poll();
            minHeap.offer(c);
            ans += c;
        }
        out.println(ans);
        out.flush();
    }
}