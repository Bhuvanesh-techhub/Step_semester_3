// Problem 2: Word Reverser and Palindrome Checker
import java.util.Scanner;

public class P2_WordReverserPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            StringBuilder sb = new StringBuilder(line);
            String reversed = sb.reverse().toString();
            boolean isPalindrome = line.equals(reversed);

            System.out.println(reversed + " - " + (isPalindrome ? "palindrome" : "not a palindrome"));
        }
    }
}
