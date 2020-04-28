package tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Raid3714 {

    public static void main(String[] args) throws IOException {
        new Raid3714().solve();
    }

    int T, N;
    Point[] points = new Point[200005];
    Point[] terms = new Point[200005];

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        T = Integer.parseInt(in.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(in.readLine());
            // 多组数据初始化
            Arrays.fill(points, 0, N, null);
            String[] strings = null;
            for (int j = 0; j < N; j++) {
                strings = in.readLine().split(" ");
                points[j] = new Point(Long.parseLong(strings[0]), Long.parseLong(strings[1]),  PointType.STATION);
            }
            for (int j = 0; j < N; j++) {
                strings = in.readLine().split(" ");
                points[j + N] = new Point(Long.parseLong(strings[0]), Long.parseLong(strings[1]),  PointType.ANGENT);
            }
//            Arrays.sort(points, 0, 2 * N);
//            out.printf("%.3f\n", Math.sqrt(merge(0, 2 * N -1)));
            out.printf("%.3f\n", Math.sqrt(getMinDis()));
        }

        out.flush();
    }

    /**
     * 分治精确算法
     * @param left
     * @param right
     * @return
     */
    private double merge(int left, int right) {
        if (left == right) {
            return Long.MAX_VALUE;
        } else if (left + 1 == right) {
            if (points[left].type != points[right].type) {
                return dis(points[left], points[right]);
            } else {
                return Long.MAX_VALUE;
            }
        } else {
            int mid = (left + right) >> 1;
            double d = Math.min(merge(left, mid), merge(mid + 1, right));
            int k = 0;
            for (int i = left; i <= right; i++) {
                if (Math.abs(points[i].x - points[mid].x) <= d) {
                    terms[k++] = points[i];
                }
            }
            Arrays.sort(terms, 0, k, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return (o1.y < o2.y) ? -1 : ((o1.y == o2.y ? 0 : 1));
                }
            });
            for (int i = 0; i < k; i++) {
                for (int j = i + 1; j < k; j++) {
                    if (Math.abs(terms[j].y - terms[i].y) > d) {
                        break;
                    } else {
                        if (terms[i].type != terms[j].type) {
                            d = Math.min(d, dis(terms[i], terms[j]));
                        }
                    }
                }
            }
            return d;
        }
    }

    /**
     * 旋转坐标系近似算法
     * @return
     */
    private double getMinDis() {
        double ans = Double.MAX_VALUE;
        Random rand = new Random();
        Arrays.sort(points, 0, 2 * N);
        ans = dfs(ans);
        int ds = rand.nextInt(360);
        around(ds);
        Arrays.sort(points, 0, 2 * N);
        ans = dfs(ans);
        ds = rand.nextInt(360);
        Arrays.sort(points, 0, 2 * N);
        ans = dfs(ans);
        return ans;

    }

    private double dfs(double ans) {
        for (int i = 0; i < 2 * N; i++) {
            for (int j = i + 1; j <= Math.min(i + 5, 2 * N - 1); j++) {
                if (points[j].x - points[i].x >= ans) {
                    break;
                } else {
                    if (points[j].type != points[i].type) {
                        ans = Math.min(ans, dis(points[j], points[i]));
                    }
                }
            }
        }
        return ans;
    }

    private void around(int ds) {
        for (int i = 0; i < 2 * N; i++) {
            double x = points[i].x;
            double y = points[i].y;
            points[i].x = x * Math.cos(ds) - y * Math.sin(ds);
            points[i].y = x * Math.sin(ds) + y * Math.cos(ds);
        }

    }


    double dis(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y)* (a.y - b.y);
    }


    enum PointType{
        ANGENT, STATION;
    }
    class Point implements  Comparable<Point>{
        double x;
        double y;
        PointType type;

        Point(double x, double y, PointType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public int compareTo(Point p) {
            if (x != p.x) {
                return y < p.y ? -1 : (y == p.y ? 0 : 1);
            }else {
                return x < p.x ? -1 : 1;
            }

        }
    }


}


