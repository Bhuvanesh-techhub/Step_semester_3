// Problem 1: Vowel and Consonant Counter
import java.util.Scanner;

public class P1_VowelConsonantCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine().trim();

        int vowels = 0;
        int consonants = 0;

        for (char c : word.toCharArray()) {
            char lower = Character.toLowerCase(c);
            if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                vowels++;
            } else if (Character.isLetter(lower)) {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }
}
