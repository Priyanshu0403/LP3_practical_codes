public class FibonacciCounter {

    static int recursiveSteps = 0;
    static int iterativeSteps = 0;

    // Recursive Fibonacci with step counting
    public static int fibonacciRecursive(int n) {
        recursiveSteps++;
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative Fibonacci with step counting
    public static int fibonacciIterative(int n) {
        iterativeSteps = 0;
        if (n <= 1) {
            iterativeSteps++;
            return n;
        }

        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            iterativeSteps++;
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static void main(String[] args) {
        int n = 10;  // Change this value to test other Fibonacci numbers

        // Recursive
        recursiveSteps = 0;
        int fibRec = fibonacciRecursive(n);
        System.out.println("Recursive Fibonacci of " + n + " = " + fibRec);
        System.out.println("Recursive steps taken: " + recursiveSteps);

        // Iterative
        int fibIter = fibonacciIterative(n);
        System.out.println("Iterative Fibonacci of " + n + " = " + fibIter);
        System.out.println("Iterative steps taken: " + iterativeSteps);
    }
}