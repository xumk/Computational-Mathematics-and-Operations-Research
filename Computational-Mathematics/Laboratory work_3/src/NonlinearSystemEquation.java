public class NonlinearSystemEquation {
    private static final float EXP = 0.0001f;

    public int getIterations() {
        return iterations;
    }

    private int iterations;
    private CalculationsHelper helper;

    public NonlinearSystemEquation() {
        helper = new CalculationsHelper();
        iterations = 0;
    }

    public float[] methodNewton(int numberEquations, float[] vectorInitialApproximation) {
        float max;
        float[][] matrixJacobi;
        float[] vectorH, nextVector, currentVector = vectorInitialApproximation;
        do {
            matrixJacobi = helper.formingJacobiMatrix(numberEquations, currentVector);
            vectorH = helper.vectorH(numberEquations, matrixJacobi, currentVector);
            nextVector = helper.layDownVector(currentVector, vectorH);
            max = helper.findMaximumElementVector(vectorH);
            currentVector = nextVector;
            ++iterations;
        } while (max >= EXP);
        return nextVector;
    }
}