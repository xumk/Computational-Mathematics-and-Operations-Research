public class Main {
    public static void main(String[] args) {
        BisectionMethod bisectionMethod = new BisectionMethod();
        MethodGoldenSection goldenSection = new MethodGoldenSection();
        MethodNumbersFibonacci fibonacci = new MethodNumbersFibonacci();
        System.out.println("Метод бисекции");
        System.out.println(bisectionMethod.runMethod(8, 8.06f));
        System.out.println(bisectionMethod.runMethod(5, 5.017f));
        System.out.println(bisectionMethod.runMethod(1, 1.48f));
        System.out.println("Метод золотого сечения");
        System.out.println(goldenSection.runMethod(1, 2));
        System.out.println(goldenSection.runMethod(5, 5.033f));
        System.out.println(goldenSection.runMethod(8, 9));
        System.out.println("Метод чисел Фибоначчи");
        System.out.println(fibonacci.runMethod(1, 1.6f));
        System.out.println(fibonacci.runMethod(5, 5.02f));
        System.out.println(fibonacci.runMethod(8, 8.07f));
        BruteForce bruteForce = new BruteForce();
        /*System.out.println("Метод перебора");
        System.out.println(bruteForce.runMethod(1, 2f));
        System.out.println(bruteForce.runMethod(5, 6f));
        System.out.println(bruteForce.runMethod(8, 9f));*/
    }
}
