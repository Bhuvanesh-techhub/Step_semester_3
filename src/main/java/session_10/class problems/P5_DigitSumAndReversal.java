// Problem 5: Digit Sum and Number Reversal
import java.util.Scanner;

public class P5_DigitSumAndReversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = Integer.parseInt(sc.nextLine().trim());

        int n = number;
        int sum = 0;
        int reversed = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            reversed = reversed * 10 + digit;
            n /= 10;
        }

        System.out.println("Sum of digits: " + sum);
        System.out.println("Reverse: " + reversed);
    }
}
