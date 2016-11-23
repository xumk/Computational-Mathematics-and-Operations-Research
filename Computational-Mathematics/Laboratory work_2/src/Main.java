import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Алексей on 20.11.2015.
 */
public class Main {
    public static final double EXP = 0.0001;

    public static void main(String[] args) throws IOException {
        MethodGayssa gayssa = new MethodGayssa();
        /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество строк = ");
        int n1 = Integer.valueOf(in.readLine());
        System.out.println("ВВедите количество столбцов (вместе с дополнительным) = ");
        int n2 = Integer.valueOf(in.readLine());
        double[][] matr = new double[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                System.out.println("Введите x_" + i + "_" + j + " = ");
                matr[i][j] = Double.valueOf(in.readLine());
            }
        }*/

        System.out.println("Метод Гаусса");
        int n1 = 4, n2 = 5;
        double[][] matr = {
                {2, 1, 4, 8, -1},
                {1, 3, -6, 2, 3},
                {2, -1, 2, 0, 4},
                {3, -2, 2, -2, 8}
        };
        double[] triangular = gayssa.methodGaysa(n1, n2, matr);
        for (int i = 0; i < n1; i++) {
            int k = i + 1;
            System.out.print("X_" + k + " = " + triangular[i]);
            System.out.println();
        }
        System.out.println("Метод Якоби");
        int n = 4;
        double[][] A = {
                {2, 1, 4, 8},
                {1, 3, -6, 2},
                {3, -2, 2, -2},
                {2, -1, 2, 0}
        };
        double[] B = {-1, 3, 8, 4};
        double[] X = {-0.5, 1, 8 / 2, -1};
        /*Jacobi jacobi = new Jacobi();
        double[] result = jacobi.methodJacobi(n, A, B, X);

        for (int i = 0; i < n; i++) {
            int k = i + 1;
            System.out.print("X_" + k + " = " + result[i] + " ");
            System.out.println();
        }

         -1/6  	  1/6  	  -0.5  	  4/3
  -4/3  	  1/3  	  -5  	  26/3
  -0.5  	  0  	  -2  	  3.5
  7/12  	  -1/12  	  1.75  	  -19/6

        System.out.println("Количество итераций = " + jacobi.getCount());*/
        double[][] d = {
                {(double) -1 / 6 - EXP, (double) 1 / 6 - EXP, (double) -0.5 - EXP, (double) 4 / 3 - EXP},
                {(double) -4 / 3 - EXP, (double) 1 / 3 - EXP, (double) -5 - EXP, (double) 26 / 3 - EXP},
                {(double) -0.5 - EXP, (double) 0 - EXP, (double) -2 - EXP, (double) 3.5 - EXP},
                {(double) 7 / 12 - EXP, (double) -1 / 12 - EXP, (double) 1.75 - EXP, (double) -19 / 6 - EXP}
        };
        Jacobi2 jacobi2 = new Jacobi2(n, A, B, d);
        double[] result;// = jacobi2.methodJacobi(X);

       /* for (int i = 0; i < n; i++) {
            int k = i + 1;
            System.out.print("X_" + k + " = " + result[i] + " ");
            System.out.println();
        }

        System.out.println("Количество итераций = " + jacobi2.getCount());*/

        Jacobi jacobi = new Jacobi();
        result = jacobi.methodJacobi(n, A, B, X, d);

        for (int i = 0; i < n; i++) {
            int k = i + 1;
            System.out.print("X_" + k + " = " + result[i] + " ");
            System.out.println();
        }

        System.out.println("Количество итераций = " + jacobi.getCount());
    }
}
