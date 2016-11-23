import java.util.ArrayList;
import java.util.List;

/**
 * Created by ??????? on 21.12.2015.
 */
public class Transport {
    private float[] v;
    private float[] u;
    private int n;
    private int m;
    private float[] a;
    private float[] b;
    private float[][] c;
    private float[][] x;
    private List<Point> baziz;
    private int iter;
    private List<Point> cycle;

    private class Point {
        private int x;
        private int y;

        public Point() {
            x = 0;
            y = 0;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.valueOf(this.y) + ':' + String.valueOf(this.x);
        }
    }

    public Transport(int n, int m, float[][] matr) {
        v = new float[m - 1];
        u = new float[n - 1];
        this.n = n - 1;
        this.m = m - 1;
        this.a = new float[this.n];
        for (int i = 1; i < n; i++) { //1..n-1
            a[i - 1] = matr[i][0];
        }

        b = new float[this.m];
        for (int i = 1; i < m; i++) {
            b[i - 1] = matr[0][i];
        }

        c = new float[this.n][this.m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                c[i - 1][j - 1] = matr[i][j];
            }
        }

        x = new float[n - 1][m - 1];
        baziz = new ArrayList<>();
        for (int i = 0; i < n + m - 3; i++) {
            baziz.add(new Point());
        }

        cycle = new ArrayList<>();
    }

    public void solve() {
        build_plan();
        while (true) {
            Point p = get_best_point();
            out_state();
            if (getr(p.x, p.y) >= 0) {
                break;
            }
            ++iter;
            cycle = get_cycle(p);
            execute(cycle);
        }
        System.out.println();
    }

    // ????????? ????, ?????????? ?????? Point, ????????? p ? ?????
    public List<Point> get_cycle(Point p) {
        // ?????????? ????? ? ??????
        List<Point> q_row = new ArrayList<>();
        List<Point> q_col = new ArrayList<>();
        //?????? ?????? ? ??????? ??????
        Point[][] ancestor = new Point[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ancestor[i][j] = new Point(-1, -1);
            }
        }
        q_row.add(p);
        baziz.add(p);
        //??? ???????, ????? ???? ?? ????????? ? ?????
        while (q_row.size() > 0 || q_col.size() > 0) {
            while (q_row.size() > 0) {
                Point cur = q_row.get(0);
                q_row.remove(0);
                for (int i = 0; i < baziz.size(); i++) {
                    int x = baziz.get(i).x;
                    int y = baziz.get(i).y;
                    if (cur.y == y && cur.x != x && ((Point)ancestor[y][x]).x == -1) {
                        ((Point)ancestor[y][x]).x = cur.x;
                        ((Point)ancestor[y][x]).y = cur.y;
                        q_col.add(new Point(x, y));
                    }
                }
            }
            while (q_col.size() > 0) {
                Point cur = q_col.get(0);
                q_col.remove(0);
                for (int i = 0; i < baziz.size(); i++) {
                    int x = baziz.get(i).x;
                    int y = baziz.get(i).y;
                    if (cur.x == x && cur.y != y && ((Point)ancestor[y][x]).x == -1) {
                        ((Point) ancestor[y][x]).x = cur.x;
                        ((Point) ancestor[y][x]).y = cur.y;
                        q_row.add(new Point(x, y));
                    }
                }
            }
        }
        // ??????????????? ????
        List<Point> res = new ArrayList<>();
        res.add(new Point(p.x, p.y));
        Point s = (Point)ancestor[p.y][p.x];
        while (s.x != p.x || s.y != p.y) {
            if (s.x == -1) {
                System.out.println("?????????? ????????? ????!!!");
                break;
            }
            res.add(new Point(s.x, s.y));
            int oldX = s.x;
            s.x = ((Point)ancestor[s.y][s.x]).x;
            s.y = ((Point)ancestor[s.y][oldX]).y;
        }
        return res;
    }

    // ???? ????????
    private void execute(List<Point> cycle) {
        // ???? dx
        int i = 1;
        float dx = 1123456f;
        while (i < cycle.size()) {
            int x = cycle.get(i).x;
            int y = cycle.get(i).y;
            if (this.x[y][x] < dx) {
                dx = this.x[y][x];
            }
            i += 2;
        }
        //?????? ???????? ? ??????? ?????
        for (int j = 0; j < cycle.size(); j++) {
            int x = cycle.get(j).x;
            int y = cycle.get(j).y;
            this.x[y][x] += dx;
            dx *= -1;
        }
        //???? ?????? ??? ????????? ?? ??????
        Point p = baziz.get(0);
        int pi = 0;
        for (int j = 0; j < baziz.size(); j++) {
            int x = baziz.get(j).x;
            int y = baziz.get(j).y;
            if ((this.x[y][x] == 0) && (this.x[p.y][p.x] != 0 || this.c[y][x] > this.c[p.y][p.x])) {
                p = new Point(x, y);
                pi = j;
            }
        }
        baziz.remove(pi);
    }

    private void out_state(){
        System.out.println("x[i][j] = ");
        for (int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
        /*System.out.println("v = " + Arrays.toString(v));
        System.out.println("u = " + Arrays.toString(u));
        System.out.println("c[i][j] - u[i] - v[j] = ");
        for (int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(getr(j,i) + " ");
            }
            System.out.println();
        }
        System.out.println("basis = ");
        for (Point point : baziz) {
            System.out.println(point.toString());
        }
        System.out.println("cycle = ");
        for (Point point : cycle) {
            System.out.println(point.toString());
        }
*/
    }
    private Point get_best_point() {
        // ????????????? ?????????? ??????? ? ??????
        List<Integer> q_row = new ArrayList<>();
        List<Integer> q_col = new ArrayList<>();
        // ?????????? ?????? ? ???????
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[m];
        q_row.add(baziz.get(0).y);
        // ???????? ?????? ?????????
        u[baziz.get(0).y] = 0;
        while (q_row.size() > 0 || q_col.size() > 0) {
            while (q_row.size() > 0) {
                int cur = q_row.get(0);
                q_row.remove(0);
                row[cur] = true;
                for (Point aBaziz : baziz) {
                    int x = aBaziz.x;
                    int y = aBaziz.y;
                    if (!col[x] && y == cur) {
                        v[x] = c[y][x] - u[y];
                        q_col.add(x);
                    }
                }
            }
            while (q_col.size() > 0) {
                int cur = q_col.get(0);
                q_col.remove(0);
                col[cur] = true;
                for (Point aBaziz : baziz) {
                    int x = aBaziz.x;
                    int y = aBaziz.y;
                    if (!row[y] && x == cur) {
                        u[y] = c[y][x] - v[x];
                        q_row.add(y);
                    }
                }
            }
        }
        // ???? ??????????? ??????
        Point p = new Point(0, 0);
        float mn = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                float tek = getr(j, i);
                if (tek < mn) {
                    mn = tek;
                    p.x = j;
                    p.y = i;
                }
            }
        }
        return p;
    }

    // ?????????? ?????? ??? ?????? x,y
    private float getr(int x, int y) {
        return c[y][x] - u[y] - v[x];
    }

    private void build_plan() {
        int x = 0;
        int y = 0;
        //????? ?? ??????? ???????? ??????
        float tek_a = a[0];
        float tek_b = b[0];
        for (int i = 0; i < n + m - 1; i++) {
            baziz.get(i).x = x;
            baziz.get(i).y = y;
            float m = Math.min(tek_a, tek_b);
            this.x[y][x] = m;
            tek_a -= m;
            tek_b -= m;

            if (tek_a == 0 && y < this.n - 1) {
                y += 1;
                tek_a = this.a[y];
            } else {
                if (x < this.m - 1) {
                    x += 1;
                    tek_b = this.b[x];
                }
            }
        }
    }
}
