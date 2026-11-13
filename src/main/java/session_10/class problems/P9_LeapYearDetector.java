// Problem 9: Leap Year Detector
import java.util.Scanner;

public class P9_LeapYearDetector {

    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            int year = Integer.parseInt(line);
            System.out.println(isLeapYear(year) ? "Leap year" : "Not a leap year");
        }
    }
}
