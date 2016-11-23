public class CalculationsHelper {
    public static final float EXP = 0.0001f;
    public static final float PROPORTION_GOLDEN_SECTION = 1.618034f;

    public float getValueFunction(float x) {
        return (float) (Math.pow(x, 4) - (19.4 * Math.pow(x, 3)) + (119.43 * Math.pow(x, 2)) - (238.606 * x) + 43.097);
    }

    static int fibonacci(int n) {
        return (n <= 2 ? 1 : fibonacci(n - 1) + fibonacci(n - 2));
    }
}
