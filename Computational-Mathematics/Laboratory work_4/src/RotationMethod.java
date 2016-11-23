import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10.12.15.
 */
public class RotationMethod {
    private CalculationsHelper helper;
    private float[][] matrixA;
    private float[][] matrixS;
    private float[] a;
    private float[] b;
    private float[] c;
    private List<float[][]> matrixList;
    private int n;
    private double lymbda;
    private double[] vectorY;

    public RotationMethod(float[][] matrix) {
        helper = new CalculationsHelper();
        matrixA = helper.matrixCopy(matrix);
        n = matrix.length;
        matrixList = new ArrayList<>();
        a = new float[n];
        b = new float[n];
        c = new float[n];
    }

    private void writeMatrix(float[][] matrix) {
        for (float[] aMatrix : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(aMatrix[j] + " ");
            }
            System.out.println();
        }
    }

    public void run() {
        float[][] matrixS = helper.matrixCopy(matrixA);
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                float[][] matrixT = helper.matrixCopy(helper.getSingleMatrix(n));
                matrixT[i][i] = (float) getC(matrixS, i, j);
                matrixT[i][j] = -(float) getS(matrixS, i, j);
                matrixT[j][i] = (float) getS(matrixS, i, j);
                matrixT[j][j] = (float) getC(matrixS, i, j);
               // writeMatrix(matrixT);
                //System.out.println();
                matrixList.add(helper.matrixCopy(matrixT));
                float[][] transposeMatrixT = helper.matrixCopy(getTransposeMatrixT(matrixT));
                float[][] interim = helper.multiplyMatrixOnMatrix(matrixS, matrixT);
                matrixS = helper.multiplyMatrixOnMatrix(transposeMatrixT, interim);
            }
        }
        this.matrixS = matrixS;
        for (int i = 0; i < n; i++) {
            a[i] = matrixS[i][i];
            if (i < 4) {
                b[i] = matrixS[i + 1][i];
            }
            if (i < 4) {
                c[i] = matrixS[i][i + 1];
            }
        }
    }

    private float[][] getTransposeMatrixT(float[][] matrixT) {
        float[][] copyMatrixT = helper.matrixCopy(matrixT);
        float[][] result = new float[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = copyMatrixT[j][i];
            }
        }
        return result;
    }

    public double getLymbda(double startInterval, double endInterval) {
        lymbda = helper.getRootRotation(this.matrixS, startInterval, endInterval);
        vectorY = getVectorY(lymbda, matrixS.length);
        return lymbda;
    }

    public double[] getVectorX(double[] vectorY) {
        double[] vectorX = helper.vectorCopy(vectorY);
        double[] intermediateVector;
        int n = matrixList.size();
        for (int i = n - 1; i >= 0; --i) {
            intermediateVector = multiplyMatrixByVector(vectorX.length, matrixList.get(i), vectorX);
            vectorX = helper.vectorCopy(intermediateVector);
        }
        return vectorX;
    }

    private double[] getVectorY(double alpha, int n) {
        double[] result = new double[n];
        result[0] = 1;
        result[1] = -(a[0] - alpha) * (result[0] / b[0]);
        for (int i = 2; i < n; ++i) {
            result[i] = (-c[i - 2] * result[i - 2] - (a[i - 1] - lymbda) * result[i - 1]) / b[i - 1];
        }
        return result;
    }

    private double[] multiplyMatrixByVector(int n, float[][] matrix, double[] vector) {
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    private double getS(float[][] matrixS, int i, int j) {
        return matrixS[i - 1][j] / Math.sqrt(Math.pow(matrixS[i - 1][i], 2) + Math.pow(matrixS[i - 1][j], 2));
    }

    private double getC(float[][] matrixS, int i, int j) {
        return matrixS[i - 1][i] / Math.sqrt(Math.pow(matrixS[i - 1][i], 2) + Math.pow(matrixS[i - 1][j], 2));
    }

    public float[][] getMatrixS() {
        return matrixS;
    }

    public double[] getVectorY() {
        return vectorY;
    }
}
