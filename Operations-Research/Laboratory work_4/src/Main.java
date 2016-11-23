import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Calculations calculations = new Calculations();
        calculations.methodCrushingStep(new double[]{0, -2, 1}, 0.05);
        System.out.println("Ответ: " + Arrays.toString(calculations.getValueUnknown()));
        System.out.println("Значение функции = " + calculations.getValueFunction());
        System.out.println("Количество итераций = " + calculations.getCount());

        calculations.methodFastStep(new double[]{0, -2, 1});
        System.out.println("Ответ: " + Arrays.toString(calculations.getValueUnknown()));
        System.out.println("Значение функции = " + calculations.getValueFunction());
        System.out.println("Количество итераций = " + calculations.getCount());
    }
}
