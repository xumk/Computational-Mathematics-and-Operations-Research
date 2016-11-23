public class MethodNumbersFibonacci {
    private CalculationsHelper helper;

    public MethodNumbersFibonacci() {
        helper = new CalculationsHelper();
    }

    public float runMethod(float a, float b) {
        int n = (int) ((b - a) / CalculationsHelper.EXP);
        int i = 2;
        while (n > CalculationsHelper.fibonacci(i)) {
            ++i;
        }
        int s = i, k = 1;
        float l = (b - a) / (float) CalculationsHelper.fibonacci(s);
        float x1 = a + l / (float) CalculationsHelper.fibonacci(s - 2);
        float x2 = b - l / (float) CalculationsHelper.fibonacci(s - 2);
        float y1 = helper.getValueFunction(x1);
        float y2 = helper.getValueFunction(x2);

        while (k != s - 1) {
            if (y1 < y2) {
                b = x2;
                ++k;
                x2 = x1;
                y2 = y1;
                x1 = a + l * CalculationsHelper.fibonacci(s - 1 - k);
                y1 = helper.getValueFunction(x1);
            } else {
                a = x1;
                ++k;
                x1 = x2;
                y1 = y2;
                x2 = b - l*CalculationsHelper.fibonacci(s - 1 - k);
                y2 = helper.getValueFunction(x2);
            }
        }
        if (y1 < y2) {
            b = x1;
        } else {
            a = x1;
        }

        return (a + b) / 2;
    }
}
