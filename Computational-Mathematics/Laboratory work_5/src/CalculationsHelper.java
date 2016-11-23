import java.util.Arrays;

public class CalculationsHelper {
    private static final int N = 11;
    private float[] vectorX;
    private float[] vectorXg;
    private float[] vectorQ;
    private float[] vectorQ2;
    private float[] vectorFact;
    private float[][] matrixFiniteDifference;
    float h;

    public CalculationsHelper(double a, double b) {
        vectorX = new float[N];
        vectorXg = new float[N];
        matrixFiniteDifference = new float[N][N];
        vectorQ = new float[N];
        vectorQ2 = new float[N];
        vectorFact = new float[N];
        createVectorX(a, b);
        createVectorXg();
        createMatrixFinite();
        createVectorFact();
    }

    public void createVectorX(double a, double b) {
        h = (float) (b - a) / 10;
        for (int i = 0; i < N; i++) {
            vectorX[i] = (float) (a + ((b - a) / 10) * i);
        }
    }

    public void createVectorXg() {
        for (int i = 0; i < N; i++) {
            vectorXg[i] = (float) (vectorX[i] + 0.03);
        }
    }

    public void createVectorFact() {
        vectorFact[0] = 1;
        for (int i = 1; i < N; i++) {
            vectorFact[i] = vectorFact[i - 1] * i;
        }
    }

    public float secondInterpolationFormulaNewton(float valueX) {
        float p1, pp;
        float hp = 1;
        int n = N - 1;
        for (int i = 1; i < N; i++) {
            hp = hp * h;
            vectorQ2[i] = matrixFiniteDifference[n - i][i] / (vectorFact[i] * hp);
        }
        p1 = getValueFunction(vectorX[n]);
        pp = valueX - vectorX[n];
        for (int i = 1; i < N; i++) {
            p1 = p1 + vectorQ2[i] * pp;
            pp = pp * (valueX - vectorX[n - i]);
        }
        return p1;
    }

    public float firstInterpolationFormulaNewton(float valueX) {
        float p1, pp;
        float hp = 1;
        for (int i = 1; i < N; i++) {
            hp = hp * h;
            vectorQ[i] = matrixFiniteDifference[0][i] / (vectorFact[i] * hp);
        }
        p1 = getValueFunction(vectorX[0]);
        pp = valueX - vectorX[0];
        for (int i = 1; i < N; i++) {
            p1 = p1 + vectorQ[i] * pp;
            pp *= (valueX - vectorX[i]);
        }
        return p1;
    }

    public float lagrange(float valueX) {
        float helpX1 = 1, helpX2 = 1, l = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != i) {
                    helpX2 = helpX2 * (valueX - vectorX[j]);
                }
            }
            for (int j = 0; j < N; j++) {
                if (j != i) {
                    helpX1 = helpX1 * (vectorX[i] - vectorX[j]);
                }
            }
            l = l + getValueFunction(vectorX[i]) * (helpX2 / helpX1);
            helpX1 = 1;
            helpX2 = 1;
        }
        return l;
    }

    public float getValueFunction(double x) {
        return (float) (1 / (Math.pow(Math.E, x) - Math.pow(Math.E, -x)));
    }

    public void createMatrixFinite() {
        for (int i = 0; i < N; i++) {
            matrixFiniteDifference[i][0] = getValueFunction(vectorX[i]);
        }
        for (int i = 1; i < N; i++) {
            int j = 1;
            int k = i - 1;
            while (j < 11 && matrixFiniteDifference[j][k] != 0) {
                matrixFiniteDifference[j - 1][i] = matrixFiniteDifference[j][k] - matrixFiniteDifference[j - 1][i - 1];
                ++j;
            }
            ++k;
        }
    }

    public float[] getVectorX() {
        return vectorX;
    }

    public float[] getVectorXg() {
        return vectorXg;
    }
}
