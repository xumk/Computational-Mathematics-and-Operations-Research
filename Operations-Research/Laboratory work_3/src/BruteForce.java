/**
 * Created by alex on 14.12.15.
 */
public class BruteForce {
    private static final int N = 9;
    private static final float H = 0.000676085f;
    private CalculationsHelper helper;
    private float[] vectroX;
    private float[] vectorValueFunction;

    public BruteForce() {
        helper = new CalculationsHelper();
        vectroX = new float[N];
        vectorValueFunction = new float[N];
    }

    public float runMethod(float x0, float x1) {
        for (int i = 0; i < N; i++) {
            vectroX[i] = x0 + (i * H);
            vectorValueFunction[i] = helper.getValueFunction(vectroX[i]);
        }
        float min = vectorValueFunction[0];
        for (int i = 1; i < N; i++) {
            min = min > vectorValueFunction[i]? vectorValueFunction[i] : min;
        }
        int i = 2;
        float xi = x0 + (H / 2);
        while (helper.getValueFunction(xi) - min > CalculationsHelper.EXP ) {
            xi = Math.min(x0 + (i - 1) * H, x1);
            ++i;
        }
        return xi;
    }
}
