/**
 * Created by ������� on 22.11.2015.
 */
public class Jacobi2 {
    public static final double EXP = 0.0001;

    private int numberUnknowns;
    private double[][] coefficientMatrix;
    private double[] vectorRightParts;
    private double[][] d;
    private double[][] alpha;
    private double[] beta;
    private int count;

    public Jacobi2(
            int numberUnknowns,
            double[][] coefficientMatrix,
            double[] vectorRightParts,
            double[][] d
    ) {
        this.numberUnknowns = numberUnknowns;
        this.coefficientMatrix = coefficientMatrix;
        this.vectorRightParts = vectorRightParts;
        this.d = d;
    }

    public double[] methodJacobi(double[] vectorInitialApproximations) {
        alpha = getMatrixAlpha();
        beta = getVectorBeta();
        double[] vectorCurrentApproximations = new double[numberUnknowns];
        double[] vectorLaterApproximations = new double[numberUnknowns];
        double[] vectorEarlyApproximations = vectorInitialApproximations;
        int countIterations = 0;

        for (int i = 0; i < numberUnknowns; i++)   //цикл для расчета иксов
        {
            vectorCurrentApproximations[i] = beta[i];  //присваиваем иксу для начала значение соответсвующее из вектора бета
            for (int j = 0; j < numberUnknowns; j++) {
                vectorCurrentApproximations[i] = vectorCurrentApproximations[i] + alpha[i][j] * vectorEarlyApproximations[j]; //расчитываем по формуле используя предудущее приближение
            }
        }
        do {
            for (int i = 0; i < numberUnknowns; i++)   //цикл для расчета иксов
            {
                vectorLaterApproximations[i] = beta[i];  //присваиваем иксу для начала значение соответсвующее из вектора бета
                for (int j = 0; j < numberUnknowns; j++) {
                    vectorLaterApproximations[i] = vectorLaterApproximations[i] + alpha[i][j] * vectorEarlyApproximations[j]; //расчитываем по формуле используя предудущее приближение
                }
            }
            vectorEarlyApproximations = vectorCurrentApproximations;
            vectorCurrentApproximations = vectorLaterApproximations;
            ++countIterations;
        } while (error(vectorCurrentApproximations, vectorEarlyApproximations) > EXP);

        count = countIterations;
        return vectorLaterApproximations;
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
                alpha[i][j] += coefficientMatrix[i][j] * matrExp[j][i];
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

    //����� ��� ������������ ����������� �� ������ ����
    private double error(double[] vectorLaterApproximations, double[] vectorCurrentApproximations) {
        int n = vectorCurrentApproximations.length;
        double[] vector = new double[n];
        double max = 0;
        for (int i = 0; i < n; i++) {
            vector[i] = Math.abs(vectorLaterApproximations[i] - vectorCurrentApproximations[i]);
            max = vector[i] > max ? vector[i] : max;
        }
        return max;
    }

    public int getCount() {
        return count;
    }
}
