// Problem 7: Factorial Calculation
import java.util.Scanner;

public class P7_FactorialCalculation {

    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            int n = Integer.parseInt(line);
            System.out.println(factorial(n));
        }
    }
}
