/**
 * Created by ������� on 22.12.2015.
 */
public class Main {
    public static void main(String[] args) {
        Transport t = new Transport(4, 6, new float[][]{
                {0, 10, 12, 18, 25, 5},
                {20, 8, 4, 3, 2, 7},
                {17, 6, 2, 5, 0, 3},
                {33, 0, 2, 1, 3, 3}
        });
        t.solve();
    }
}
