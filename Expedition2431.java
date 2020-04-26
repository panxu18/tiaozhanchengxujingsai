package tiaozhanchengxujingsai;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Expedition2431 {
    public static void main(String[] args) {
        new Expedition2431().solve();
    }

    int N, L, P;
    Stop[] stops = new Stop[10005];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        N = in.nextInt();
        for (int i = 0; i < N; i++) {
            stops[i] = new Stop(in.nextInt(), in.nextInt());
        }
        L = in.nextInt();
        P = in.nextInt();
        out.println(getAns());
        out.flush();
    }

    private int getAns() {
        Arrays.sort(stops, 0, N);
        int cur = 0;
        int pos = L;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(N, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int ans = 0;
        while (cur < N && P >= pos - stops[cur].dis || !maxHeap.isEmpty()) {
            if (P >= pos) {
                // 到达目的地
                break;
            } else if (cur < N && P >= pos - stops[cur].dis) {
                // 到达下一个加油站
                P = P - (pos - stops[cur].dis);
                pos = stops[cur].dis;
                maxHeap.offer(stops[cur++].fuel);
            } else {
                // 加油
                ans++;
                int add = maxHeap.poll();
                P += add;
            }
        }
        return P >= pos ? ans : -1;
    }

    class Stop implements Comparable<Stop>{
        int dis;
        int fuel;

        public Stop(int dis, int fuel) {
            this.dis = dis;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Stop o) {
            return o.dis - dis;
        }
    }
}
