public class CalculationsHelper {

    private float[][] inversion(float[][] a) {
        float[][] a1 = new float[3][3];
        float cur;
        int i, j;
        float d = det(a);
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                a1[i][j] = add(a, i, j) / d;
            }
        }

        for (i = 0; i < 3; i++) {
            for (j = i + 1; j < 3; j++) {
                cur = a1[i][j];
                a1[i][j] = a1[j][i];
                a1[j][i] = cur;
            }
        }
        return a1;
    }

    float det(float[][] a) {
        int i;
        float sum = 0;

        for (i = 0; i < 3; i++)
            sum += a[i][0] * add(a, i, 0);

        return sum;
    }

    float add(float[][] a, int row, int col) {
        float[][] b = new float[2][2];
        int i, j, bi, bj;

        for (i = 0, bi = 0; i < 3; i++) {
            if (i != row) {
                for (j = 0, bj = 0; j < 3; j++)
                    if (j != col) {
                        b[bi][bj] = a[i][j];
                        bj++;
                    }
                bi++;
            }
        }

        if ((row + col) % 2 == 0)
            return b[0][1] * b[1][0] - b[0][0] * b[1][1];
        else
            return b[0][0] * b[1][1] - b[0][1] * b[1][0];
    }

    private float[] multiplyMatrixByVector(int n, float[][] matrix, float[] vector) {
        float[] result = new float[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    private float[] calculationValuesFunctionsAtPoint(float[] vector) {
        float[] result = new float[vector.length];
        result[0] = vector[0] + ((float) Math.pow(vector[0], 2)) - (2 * vector[1] * vector[2]) - 0.1f;
        result[1] = vector[1] - ((float) Math.pow(vector[1], 2)) + (3 * vector[1] * vector[2]) + 0.2f;
        result[2] = vector[2] + ((float) Math.pow(vector[2], 2)) + (2 * vector[0] * vector[1]) - 0.3f;
        return result;
    }

    public float[][] formingJacobiMatrix(int n, float[] vectorApproximation) {
        float[][] result = new float[n][n];
        result[0][0] = 1 + (2 * vectorApproximation[0]);
        result[0][1] = -2 * vectorApproximation[2];
        result[0][2] = -2 * vectorApproximation[1];
        result[1][0] = 0;
        result[1][1] = -(2 * vectorApproximation[1]) + (3 * vectorApproximation[2]) + 1;
        result[1][2] = 3 * vectorApproximation[1];
        result[2][0] = 2 * vectorApproximation[1];
        result[2][1] = 2 * vectorApproximation[0];
        result[2][2] = 1 + (2 * vectorApproximation[2]);

        return result;
    }

    public float[] vectorH(int n, float[][] matrixJacobi, float[] vectorApproximation) {
        float[][] inverseJacobi = inversion(matrixJacobi);
        float[] value = calculationValuesFunctionsAtPoint(vectorApproximation);
        float[] result = multiplyMatrixByVector(n, inverseJacobi, value);
        for (int i = 0; i < n; i++) {
            result[i] = -result[i];
        }

        return result;
    }

    public float findMaximumElementVector(float[] vector) {
        int n = vector.length;
        float max = vector[0];
        for (int i = 1; i < n; i++) {
            max = vector[i] > max ? vector[i] : max;
        }
        return max;
    }

    public float[] layDownVector(float[] currentVector, float[] vectorH) {
        float[] result = new float[currentVector.length];
        for (int i = 0; i < currentVector.length; i++) {
            result[i] = currentVector[i] + vectorH[i];
        }
        return result;
    }
}