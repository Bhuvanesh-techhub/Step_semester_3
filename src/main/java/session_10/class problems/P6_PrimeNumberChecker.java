// Problem 6: Prime Number Checker
import java.util.Scanner;

public class P6_PrimeNumberChecker {

    static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; (long) i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            int number = Integer.parseInt(line);
            System.out.println(number + (isPrime(number) ? " is prime" : " is not prime"));
        }
    }
}
