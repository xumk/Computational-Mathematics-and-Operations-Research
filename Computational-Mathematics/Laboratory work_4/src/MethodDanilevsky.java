import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodDanilevsky {
    private float[][] matrixA;
    private float[][] matrixP;
    private double[] vectorY;
    private double lymbda;
    private List<float[][]> matrixList;
    private List<float[][]> reversMatrixList;
    private CalculationsHelper helper;

    public MethodDanilevsky(float[][] matrix) {
        matrixA = matrix;
        matrixList = new ArrayList<>();
        reversMatrixList = new ArrayList<>();
        helper = new CalculationsHelper();
    }

    public void run() {
        float[][] matrixP = matrixA;
        int k = 0;
        for (int i = matrixP.length - 1; i > 0; --i) {
            if (matrixP[i][i - 1] != 0) {
                float[][] matrixM = helper.getMatrixM(matrixP, i);
                //writeMatrix(matrixM);
                matrixList.add(matrixM);
                float[][] reversMatrixM = helper.inversion(matrixM);
                reversMatrixList.add(reversMatrixM);
                float[][] interim = helper.multiplyMatrixOnMatrix(matrixP, matrixM);
                matrixP = helper.multiplyMatrixOnMatrix(reversMatrixM, interim);
                // writeMatrix(matrixP);
                ++k;
            }
        }
        this.matrixP = matrixP;
    }

    public double getLymbda(double startInterval, double endInterval) {
        lymbda = helper.getRoot(this.matrixP, startInterval, endInterval);
        vectorY = getVectorY(lymbda, matrixP.length);
        return lymbda;
    }

    public double[] getVectorX(double[] vectorY) {
        double[] vectorX = vectorY;
        double[] intermediateVector;
        int n = vectorY.length - 2;
        for (int i = n; i >= 0; --i) {
            intermediateVector = multiplyMatrixByVector(vectorX.length, matrixList.get(i), vectorX);
            vectorX = helper.vectorCopy(intermediateVector);
        }
        return vectorX;
    }

    private double[] getVectorY(double alpha, int n) {
        double[] result = new double[n];
        result[n - 1] = 1;
        for (int i = n - 2; i >= 0; --i) {
            result[i] = alpha * result[i + 1];
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

    private void writeMatrix(float[][] matrix) {
        for (float[] aMatrix : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(aMatrix[j] + " ");
            }
            System.out.println();
        }
    }

    public float[][] getMatrixP() {
        return matrixP;
    }

    public List<float[][]> getMatrixList() {
        return matrixList;
    }

    public List<float[][]> getReversMatrixList() {
        return reversMatrixList;
    }

    public double[] getVectorY() {
        return vectorY;
    }
}
