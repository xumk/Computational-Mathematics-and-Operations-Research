public class Calculations {
    private static final double EXP = 0.0001; // �����������
    private static final double EXP_CRUSHING = 0.05; //

    private int count;
    private double[] valueUnknown;
    private double valueFunction;

    public Calculations() {
        count = 0;
    }

    /**
     * ����������� ����� � ���������� ����
     *
     * @param initialPoint ��������� �����
     * @param initialStep  ��������� ���
     */
    public void methodCrushingStep(double[] initialPoint, double initialStep) {
        double[] currentValue, nextValue = initialPoint;
        double step = initialStep;
        count = 0;
        do {
            currentValue = nextValue;
            // ���� ������������� ������� �� ��������� ���� ������ ��� �� 2
            while (continueToCrushStep(currentValue, step)) {
                step /= 2;
            }
            ++count;
            // ��������� ��������� ������
            nextValue = calculateFollowingValues(currentValue, step);
        } while (continueExecutionMethodCrush(currentValue, nextValue)); // ���� �������� ������� ������ EXP
        valueUnknown = nextValue;
        valueFunction = function(nextValue);
    }

    public void methodFastStep(double[] initialPoint) {
        double[] currentValue, nextValue = initialPoint;
        double step;
        count = 0;
        do {
            currentValue = nextValue;
            ++count;
            step = -getStep(currentValue);
            // ��������� ��������� ������
            nextValue = calculateFollowingValues(currentValue, step);
        } while (continueExecutionMethodFast(currentValue, nextValue)); // ���� �������� ������� ������ EXP
        valueUnknown = nextValue;
        valueFunction = function(nextValue);
    }

    private double getStep(double[] valueUnknown) {
        double x = partialDerivativeToX(valueUnknown);
        double y = partialDerivativeToY(valueUnknown);
        double z = partialDerivativeToZ(valueUnknown);
        double result =
                ((-2 * x * valueUnknown[0])
                        - (2 * y * valueUnknown[1])
                        - (2 * z * valueUnknown[2])
                        + (2 * x) - (2 * y) + (4 * z))
                        / (2 * (Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)));
        return result;
    }

    /**
     * ����� ��� �������� ���������� ������� ��������
     *
     * @param valueVariables ������� ������ ��������
     * @param step           ���
     * @return ��������� ������
     */
    private double[] calculateFollowingValues(double[] valueVariables, double step) {
        double[] derivative = assumeDerivatives(valueVariables); // ������� �������� �����������
        // �������� ����������� ���������� �� ���
        double x = step * derivative[0];
        double y = step * derivative[1];
        double z = step * derivative[2];
        double[] result = new double[valueVariables.length];
        // �� �������� �������� ����������� ���� ��������
        result[0] = valueVariables[0] - x;
        result[1] = valueVariables[1] - y;
        result[2] = valueVariables[2] - z;
        return result;
    }

    /**
     * ����� ��� ������������ �����������
     *
     * @param valueVariables ������ ��������
     * @return ������ ���������� �����������
     */
    private double[] assumeDerivatives(double[] valueVariables) {
        double[] result = new double[valueVariables.length];
        result[0] = partialDerivativeToX(valueVariables);
        result[1] = partialDerivativeToY(valueVariables);
        result[2] = partialDerivativeToZ(valueVariables);
        return result;
    }

    /**
     * ����� ��� �������� �������� ������� � ������� ������
     *
     * @param valueVariables ������ �������� �����
     * @return �������� �������
     */
    private double function(double[] valueVariables) {
        return Math.pow(valueVariables[0], 2) + Math.pow(valueVariables[1], 2)
                + Math.pow(valueVariables[2], 2) - (2 * valueVariables[0])
                + (2 * valueVariables[1]) - (4 * valueVariables[2]);
    }

    /**
     * ����� �������� �������� ������� ����������� �� �
     *
     * @param valueVariables �������� �����
     * @return �������� �����������
     */
    private double partialDerivativeToX(double[] valueVariables) {
        return (2 * valueVariables[0]) - 2;
    }

    /**
     * ����� �������� �������� ������� ����������� �� y
     *
     * @param valueVariables �������� �����
     * @return �������� �����������
     */
    private double partialDerivativeToY(double[] valueVariables) {
        return (2 * valueVariables[1]) + 2;
    }

    /**
     * ����� �������� �������� ������� ����������� �� z
     *
     * @param valueVariables �������� �����
     * @return �������� �����������
     */
    private double partialDerivativeToZ(double[] valueVariables) {
        return (2 * valueVariables[2]) - 4;
    }

    /**
     * ����� �� ���������� ��������� ����
     *
     * @param currentValue ������ ��������
     * @param step         ���
     * @return
     */
    private boolean continueToCrushStep(double[] currentValue, double step) {
        double error = function(calculateFollowingValues(currentValue, step)) - function(currentValue);
        double[] derivative = assumeDerivatives(currentValue);
        boolean result = false;
        for (int i = 0; i < currentValue.length; i++) {
            if (error > EXP_CRUSHING * step * derivative[i]) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * ����� �� ���������� �����
     *
     * @param currentValue ������� ������ ��������
     * @param nextValue    ��������� ������ ��������
     * @return
     */
    private boolean continueExecutionMethodCrush(double[] currentValue, double[] nextValue) {
        int n = currentValue.length;
        boolean result = false;

        for (int i = 0; i < n; i++) {
            if (Math.abs(nextValue[i] - currentValue[i]) > EXP) {
                result = true;
                break;
            }
        }

        return result;
    }

    private boolean continueExecutionMethodFast(double[] currentValue, double[] nextValue) {
        int n = currentValue.length;
        boolean result = false;

        for (int i = 0; i < n; i++) {
            if (Math.abs(nextValue[i] - currentValue[i]) <= EXP) {
                result = true;
                break;
            }
        }

        return result;
    }

    public int getCount() {
        return count;
    }

    public double[] getValueUnknown() {
        return valueUnknown;
    }

    public double getValueFunction() {
        return valueFunction;
    }
}
