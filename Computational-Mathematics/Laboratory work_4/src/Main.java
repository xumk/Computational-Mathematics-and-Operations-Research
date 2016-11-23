import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        float[][] matrix = {
                {2, 5, 4, 1, 20},
                {1, 3, 2, 1, 11},
                {2, 10, 9, 7, 40},
                {3, 8, 9, 2, 37},
                {7, 9, 4, -1, 5}
        };
        MethodDanilevsky danilevsky = new MethodDanilevsky(matrix);
        danilevsky.run();
        float[][] result = danilevsky.getMatrixP();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Корень = " + danilevsky.getLymbda(30, 31));
        System.out.println("Вектор: " + Arrays.toString(danilevsky.getVectorX(danilevsky.getVectorY())));
        System.out.println("Корень = " + danilevsky.getLymbda(-10, -9));
        System.out.println("Вектор: " + Arrays.toString(danilevsky.getVectorX(danilevsky.getVectorY())));
        System.out.println("Корень = " + danilevsky.getLymbda(2, 3));
        System.out.println("Вектор: " + Arrays.toString(danilevsky.getVectorX(danilevsky.getVectorY())));
        System.out.println("Корень = " + danilevsky.getLymbda(-4, -3));
        System.out.println("Вектор: " + Arrays.toString(danilevsky.getVectorX(danilevsky.getVectorY())));
        System.out.println("Корень = " + danilevsky.getLymbda(0, 1));
        System.out.println("Вектор: " + Arrays.toString(danilevsky.getVectorX(danilevsky.getVectorY())));

        System.out.println();
        System.out.println("Метод вращений");
        matrix = new float[][]{
                {2, 5, 4, 1, 20},
                {5, 3, 2, 1, 11},
                {4, 2, 9, 7, 40},
                {1, 1, 7, 2, 37},
                {20, 11, 40, 37, 5}
        };
        RotationMethod rotationMethod = new RotationMethod(matrix);
        rotationMethod.run();
        result = rotationMethod.getMatrixS();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Корень = " + rotationMethod.getLymbda(-3, -2));
        System.out.println("Вектор: " + Arrays.toString(rotationMethod.getVectorX(rotationMethod.getVectorY())));
        System.out.println("Корень = " + rotationMethod.getLymbda(-2, -1));
        System.out.println("Вектор: " + Arrays.toString(rotationMethod.getVectorX(rotationMethod.getVectorY())));
        System.out.println("Корень = " + rotationMethod.getLymbda(5, 6));
        System.out.println("Вектор: " + Arrays.toString(rotationMethod.getVectorX(rotationMethod.getVectorY())));
        System.out.println("Корень = " + rotationMethod.getLymbda(-50, -49));
        System.out.println("Вектор: " + Arrays.toString(rotationMethod.getVectorX(rotationMethod.getVectorY())));
        System.out.println("Корень = " + rotationMethod.getLymbda(69, 70));
        System.out.println("Вектор: " + Arrays.toString(rotationMethod.getVectorX(rotationMethod.getVectorY())));
    }
}
