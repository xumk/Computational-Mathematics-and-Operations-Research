import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CalculationsHelper helper = new CalculationsHelper(1, 2);
        System.out.println("Значение функции = " + helper.getValueFunction(1.5f));
        System.out.println("Первая формула Ньютона = " + helper.firstInterpolationFormulaNewton(1.5f));
        System.out.println("Вторая формула Ньютона = " + helper.secondInterpolationFormulaNewton(1.5f));
        System.out.println("формула Лагранжа = " + helper.lagrange(1.5f));
    }
}
