public class BisectionMethod {
    private CalculationsHelper helper;

    public BisectionMethod() {
        helper = new CalculationsHelper();
    }

    public float runMethod(float x0, float x1) {
        float dx = (x1 - x0);
        float nextX = x1;
        while (Math.abs(x1 - x0) > CalculationsHelper.EXP) {
            dx /= 2;
            nextX = x0 + dx;
            if (Math.signum(helper.getValueFunction(x0)) != Math.signum(helper.getValueFunction(nextX))) {
                x1 = nextX;
            } else {
                x0 = nextX;
            }
            dx = (x1 - x0);
        }
        return nextX;
    }
}
