/**
 * Created by Алексей on 20.11.2015.
 */
public class Jacobi {
    private static final double EXP = 0.0001;

    double[][] alpha;
    private double[] beta;
    private int count;
    private int numberUnknowns;
    private double[] vectorRightParts;
    private double[][] coefficientMatrix;
    private double[][] d;

    public double[] methodJacobi(
            int numberUnknowns,
            double[][] coefficientMatrix,
            double[] vectorRightParts,
            double[] vectorInitialApproximations,
            double[][] d
    ) {
        this.vectorRightParts = vectorRightParts;
        this.d = d;
        this.coefficientMatrix = coefficientMatrix;
        this.numberUnknowns = numberUnknowns;
        alpha = getMatrixAlpha();
        beta = getVectorBeta();
        double[] vectorCurrentApproximations;
        double[] vectorLaterApproximations = vectorInitialApproximations;
        int countIterations = 0;

        do {
            vectorCurrentApproximations = vectorLaterApproximations;
            vectorLaterApproximations = calculationLaterVectorApproximations(vectorCurrentApproximations);
            ++countIterations;
        } while (error(vectorLaterApproximations, vectorCurrentApproximations) > EXP);

        count = countIterations;
        return vectorLaterApproximations;
    }

    private double error(double[] vectorLaterApproximations, double[] vectorCurrentApproximations) {
        int n = vectorCurrentApproximations.length;
        double[] vector = new double[n];
        double max = Math.abs(vectorLaterApproximations[0] - vectorCurrentApproximations[0]);
        for (int i = 0; i < n; i++) {
            vector[i] = Math.abs(vectorLaterApproximations[i] - vectorCurrentApproximations[i]);
            max = vector[i] > max ? vector[i] : max;
        }
        return max;
    }

    private double[] calculationLaterVectorApproximations(double[] currentVectorApproximations) {
        int n = currentVectorApproximations.length;
        double[] laterVector = new double[n];
        double[] vectorIntermediateCalculations = multiplyMatrixByVector(currentVectorApproximations);
        for (int i = 0; i < n; i++) {
            laterVector[i] = (beta[i] + vectorIntermediateCalculations[i]);
        }

        return laterVector;
    }

    //метод для умножения матрицы на вектор
    private double[] multiplyMatrixByVector(double[] vector) {
        int n = vector.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += (alpha[i][j] * vector[j]);
            }
        }

        return result;
    }

    //����� ��� ���������� ������� �����
    private double[][] getMatrixAlpha() {
        double[][] alpha = new double[numberUnknowns][numberUnknowns];
        double[][] matrExp = new double[numberUnknowns][numberUnknowns];
        for (int i = 0; i < numberUnknowns; i++) {
            for (int j = 0; j < numberUnknowns; j++) {
                matrExp[i][j] = Main.EXP;
            }
        }
        for (int i = 0; i < numberUnknowns; i++) {
            for (int j = 0; j < numberUnknowns; j++) {
                for (int k = 0; k < numberUnknowns; k++) {
                    alpha[i][j] += coefficientMatrix[i][k] * matrExp[k][j];
                }
                System.out.print(alpha[i][j] + " ");
            }
            System.out.println();
        }
        return alpha;
    }

    //����� ��� ���������� ������� �����
    private double[] getVectorBeta() {
        int n = vectorRightParts.length;
        double[] result = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += (d[i][j] * vectorRightParts[j]);
            }
        }

        return result;
    }

    public int getCount() {
        return count;
    }
}