package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        int result = getFibonacciNumber(8);
        System.out.println(result);
    }

    public static int getFibonacciNumber(int n) {
        if (n <= 1) {
            return n;
        }
        return getFibonacciNumber(n - 1) + getFibonacciNumber(n - 2);
    }
}
