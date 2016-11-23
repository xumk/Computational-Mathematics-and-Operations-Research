/**
 * Created by alex on 11.12.15.
 */
public class CalculationsHelper {
    private static final int N = 11;
    double a;
    double b;

    private float[] vectorX;

    public CalculationsHelper(double a, double b) {
        vectorX = new float[N];
        this.a = a;
        this.b = b;
        createVectorX(a, b);
    }


    public float getValueFunction(double x) {
        return (float) (1 / (Math.pow(Math.E, x) - Math.pow(Math.E, -x)));
    }

    public float getValueDerivativeFunction(double x) {
        return (float) ((Math.pow(Math.E, x) + (6 * Math.pow(Math.E, 3 * x)) + (Math.pow(Math.E, 5 * x) + 1)) / Math.pow((Math.pow(Math.E, 2 * x) - 1), 3));
    }

    private void createVectorX(double a, double b) {
        for (int i = 0; i < N; i++) {
            vectorX[i] = (float) (a + ((b - a) / 10) * i);
        }
    }

    public float rectanglesMethod() {
        float result = 0;
        for (int i = 0; i < N - 1; i++) {
            result += getValueFunction((vectorX[i] + vectorX[i + 1]) / 2) * (vectorX[i + 1] - vectorX[i]);
        }
        return result;
    }

    public float rectanglesMethod2() {
        float h = (float) ((b - a) / 10);
        float result = (float) ((b - a) / 10);
        float summ = 0;
        for (int i = 1; i < N; i++) {
            summ += getValueFunction(vectorX[i] - (h / 2));
        }
        result *= summ;
        return result;
    }


    public float methodTrapezoids() {
        float result = 0;
        for (int i = 0; i < N - 1; i++) {
            result += ((getValueFunction(vectorX[i]) + getValueFunction(vectorX[i + 1])) / 2) * (vectorX[i + 1] - vectorX[i]);
        }
        return result;
    }

    public float methodTrapezoids1() {
        double h = (b - a) / 10;
        float result = (float) h;
        float summ = 0;
        int n = N - 1;
        for (int i = 0; i < N; i++) {
            if (i == 0 || i == n) {
                summ += getValueFunction(vectorX[i]) / 2;
            } else {
                summ += getValueFunction(vectorX[i]);
            }
        }
        result *= summ;
        return result;
    }

    public float methodSimpsona() {
        double h = (b - a) / 10;
        float result = (float) (h / 3);
        float summ = 0;
        for (int i = 1; i < N - 1; i += 2) {
            summ += (getValueFunction(vectorX[i - 1]) + (4 * getValueFunction(vectorX[i])) + getValueFunction(vectorX[i + 1]));
        }
        result *= summ;
        return result;
    }
}
