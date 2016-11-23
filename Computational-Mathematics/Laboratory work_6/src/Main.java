/**
 * Created by alex on 11.12.15.
 */
public class Main {
    public static void main(String[] args) {
        CalculationsHelper helper = new CalculationsHelper(1, 2);
        System.out.println("Метод средних прямоугольник Википедия = " + helper.rectanglesMethod());
        System.out.println("Метод средних прямоугольник Лекция = " + helper.rectanglesMethod2());
        System.out.println("Метод трапеций Википедия = " + helper.methodTrapezoids());
        System.out.println("Метод трапеций Лекция = " + helper.methodTrapezoids1());
        System.out.println("Фрмула Симпсона Лекция = " + helper.methodSimpsona());
    }
}
