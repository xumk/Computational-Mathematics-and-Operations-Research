public class CalculationsHelper {
    public float[][] inversion(float[][] m) {
        float[][] A = matrixCopy(m);
        int n = A.length;
        int row[] = new int[n];
        int col[] = new int[n];
        float temp[] = new float[n];
        int hold, I_pivot, J_pivot;
        float pivot, abs_pivot;

        if (A[0].length != n) {
            System.out.println("Error in Matrix.invert, inconsistent array sizes.");
        }
        // установиим row и column как вектор изменений.
        for (int k = 0; k < n; k++) {
            row[k] = k;
            col[k] = k;
        }
        // начало главного цикла
        for (int k = 0; k < n; k++) {
            // найдем наибольший элемент для основы
            pivot = A[row[k]][col[k]];
            I_pivot = k;
            J_pivot = k;
            for (int i = k; i < n; i++) {
                for (int j = k; j < n; j++) {
                    abs_pivot = Math.abs(pivot);
                    if (Math.abs(A[row[i]][col[j]]) > abs_pivot) {
                        I_pivot = i;
                        J_pivot = j;
                        pivot = A[row[i]][col[j]];
                    }
                }
            }
            //перестановка к-ой строки и к-ого столбца с стобцом и строкой, содержащий основной элемент(pivot основу)
            hold = row[k];
            row[k] = row[I_pivot];
            row[I_pivot] = hold;
            hold = col[k];
            col[k] = col[J_pivot];
            col[J_pivot] = hold;
            // k-ую строку с учетом перестановок делим на основной элемент
            A[row[k]][col[k]] = (float) (1.0 / pivot);
            for (int j = 0; j < n; j++) {
                if (j != k) {
                    A[row[k]][col[j]] = A[row[k]][col[j]] * A[row[k]][col[k]];
                }
            }
            // внутренний цикл
            for (int i = 0; i < n; i++) {
                if (k != i) {
                    for (int j = 0; j < n; j++) {
                        if (k != j) {
                            A[row[i]][col[j]] = A[row[i]][col[j]] - A[row[i]][col[k]] *
                                    A[row[k]][col[j]];
                        }
                    }
                    A[row[i]][col[k]] = -A[row[i]][col[k]] * A[row[k]][col[k]];
                }
            }
        }
        // конец главного цикла

        // переставляем назад rows
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                temp[col[i]] = A[row[i]][j];
            }
            for (int i = 0; i < n; i++) {
                A[i][j] = temp[i];
            }
        }
        // переставляем назад columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[row[j]] = A[i][col[j]];
            }
            for (int j = 0; j < n; j++) {
                A[i][j] = temp[j];
            }
        }
        return A;
    }

    public float[][] matrixCopy(float[][] matrix) {
        int n = matrix.length;
        float[][] result = new float[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, n);
        }
        return result;
    }

    public double[] vectorCopy(double[] vector) {
        double[] result = new double[vector.length];
        System.arraycopy(vector, 0, result, 0, vector.length);
        return result;
    }

    public float[][] getMatrixM(float[][] matrix, int currentRow) {
        int n = matrix.length;
        int row = currentRow - 1;
        float[][] result = getSingleMatrix(n);
        for (int i = 0; i < n; i++) {
            if (i == currentRow) {
                result[row][row] = (float) 1 / matrix[currentRow][row];
            }
            for (int j = 0; j < n; j++) {
                if (i == currentRow && j != row) {
                    result[row][j] = -(matrix[currentRow][j] / (matrix[currentRow][row]));
                }
            }
        }
        return result;
    }

    public float[][] getSingleMatrix(int n) {
        float[][] result = new float[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = i == j ? 1 : 0;
            }
        }
        return result;
    }

    public float[][] multiplyMatrixOnMatrix(float[][] matrixLeft, float[][] matrixRight) {
        int n = matrixLeft.length;
        float[][] result = new float[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrixLeft[i][k] * matrixRight[k][j];
                }
            }
        }
        return result;
    }

    public double getFunctionAlpha(float[][] matrix, double alpha) {
        int n = matrix.length;
        double result = Math.pow(alpha, n);
        for (int i = 1; i < n; i++) {
            result -= matrix[0][i - 1] * Math.pow(alpha, n - i);
        }
        result -= matrix[0][4];
        return result;
    }

    public double getFunctionAlphaRotation(float[][] matrix, double alpha) {
        int n = matrix.length;
        double result3;
        double result1 = 1;
        double result2 = matrix[0][0] - alpha;
        for (int i = 1; i < n; i++) {
            result3 = result2;
            result2 = (matrix[i][i] - alpha) * result2 - (matrix[i - 1][i] * matrix[i][i - 1]) * result1;
            result1 = result3;
        }
        return result2;
    }

    public double getRoot(float[][] matrix, double startInterval, double endInterval) {
        double exp = 0.0001;
        double ksi = (startInterval + endInterval) / 2;
        do {
            if ((getFunctionAlpha(matrix, ksi) * getFunctionAlpha(matrix, endInterval)) < 0) {
                startInterval = ksi;
            } else {
                endInterval = ksi;
            }
            ksi = (startInterval + endInterval) / 2;
        } while ((endInterval - startInterval) >= exp);
        return ksi;
    }

    public double getRootRotation(float[][] matrix, double startInterval, double endInterval) {
        double exp = 0.0001;
        double ksi = (startInterval + endInterval) / 2;
        do {
            if ((getFunctionAlphaRotation(matrix, ksi) * getFunctionAlphaRotation(matrix, endInterval)) < 0) {
                startInterval = ksi;
            } else {
                endInterval = ksi;
            }
            ksi = (startInterval + endInterval) / 2;
        } while ((endInterval - startInterval) > exp);
        return ksi;
    }
}
