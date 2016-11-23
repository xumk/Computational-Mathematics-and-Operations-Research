public class Main {
    public static void main(String[] args) {
        NonlinearSystemEquation systemEquation = new NonlinearSystemEquation();
        float[] vector = {0.2f, 1.3f, 0.02f};
        float[] result = systemEquation.methodNewton(3, vector);
        for (int i = 0; i < 3; i++) {
            System.out.println("X_" + i + " = " + result[i]);
        }
        System.out.println("Количество итераций = " + systemEquation.getIterations());

        vector = new float[]{0.05f, -0.2f, 0.3f};
        result =systemEquation.methodNewton(3, vector);
        for (int i = 0; i < 3; i++) {
            System.out.println("X_" + i + " = " + result[i]);
        }
        System.out.println("Количество итераций = " + systemEquation.getIterations());

        vector = new float[]{-0.09f, 0.08f, -1.3f};
        result =systemEquation.methodNewton(3, vector);
        for (int i = 0; i < 3; i++) {
            System.out.println("X_" + i + " = " + result[i]);
        }
        System.out.println("Количество итераций = " + systemEquation.getIterations());
    }
}