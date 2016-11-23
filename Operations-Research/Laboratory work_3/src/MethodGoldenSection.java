public class MethodGoldenSection {
    private CalculationsHelper helper;

    public MethodGoldenSection() {
        helper = new CalculationsHelper();
    }

    public float runMethod(float a, float b) {
        float x1, x2;
        do {
            x1 = b - ((b - a) / CalculationsHelper.PROPORTION_GOLDEN_SECTION);
            x2 = a + ((b - a) / CalculationsHelper.PROPORTION_GOLDEN_SECTION);
            float y1 = helper.getValueFunction(x1);
            float y2 = helper.getValueFunction(x2);
            if (y1 >= y2) {
                a = x1;
            } else {
                b = x2;
            }
        } while (Math.abs(b - a) >= CalculationsHelper.EXP);
        return (a + b) / 2;
    }
}
