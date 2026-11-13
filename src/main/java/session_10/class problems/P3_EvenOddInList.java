// Problem 3: Even and Odd Numbers in a List
import java.util.Scanner;

public class P3_EvenOddInList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();

        // Strip the surrounding [ ] if present, then split on commas.
        line = line.replace("[", "").replace("]", "");
        String[] parts = line.split(",");

        int even = 0;
        int odd = 0;

        for (String part : parts) {
            part = part.trim();
            if (part.isEmpty()) {
                continue;
            }
            int num = Integer.parseInt(part);
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        System.out.println("Even: " + even);
        System.out.println("Odd: " + odd);
    }
}
