package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Stripies1862 {
    public static void main(String[] args) throws IOException {
        new Stripies1862().solve();
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

    int N;
    void solve() throws IOException {
        N = readInt();
        PriorityQueue<Double> maxQue = new PriorityQueue<Double>(N, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (o2 < o1) ? -1 : (o1 == o2 ? 0 : 1);
            }
        });
        for (int i = 0; i < N; i++) {
            double w = readInt();
            maxQue.add(w);
        }
        for (int i = 1; i < N; i++) {
            double a = maxQue.poll();
            double b = maxQue.poll();
            double c = 2.0 * Math.sqrt(a * b);
            maxQue.add(c);
        }
        out.printf("%.3f\n", maxQue.poll());
        out.flush();
    }
}
