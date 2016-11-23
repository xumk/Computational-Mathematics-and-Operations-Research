/**
 * Created by ������� on 20.11.2015.
 */
public class MethodGayssa {
    public double[][] castTriangular(int numberRows, int numberColumns, double[][] matr) {
        double[][] result = matr;
        for (int i = 0; i <= numberRows - 1; i++) {
            double t = result[i][i];
            if (t != 0) {
                for (int k = 0; k < numberColumns; k++) {
                    result[i][k] = result[i][k] / t;
                }
                for (int n = i + 1; n < numberRows; n++) {
                    t = result[n][i];
                    for (int j = 0; j < numberColumns; j++) {
                        result[n][j] = result[n][j] - t * result[i][j];
                    }
                }
            }
        }
        return result;
    }

    public double[] methodGaysa(int numberRows, int numberColumns, double[][] matr) {
        double[][] triangular = castTriangular(numberRows, numberColumns, matr);
        for (int i = 0; i < numberRows; i++) {
            for (int j = 0; j < numberColumns; j++) {
                System.out.print(triangular[i][j] + " ");
            }
            System.out.println();
        }
        double[] result = new double[4];
        result[numberRows - 1] = triangular[3][4];
        result[numberRows - 2] = triangular[2][4] - triangular[2][3] * result[3];
        result[numberRows - 3] = triangular[1][4] - triangular[1][3] * result[3] - triangular[1][2] * result[2];
        result[numberRows - 4] = triangular[0][4] - triangular[0][3] * result[3] - triangular[0][2] * result[2]
                - triangular[0][1] * result[1];

        return result;
    }
}
