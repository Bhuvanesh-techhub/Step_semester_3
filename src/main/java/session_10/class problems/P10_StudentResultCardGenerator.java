// Problem 10: Student Result Card Generator
import java.util.Locale;
import java.util.Scanner;

class Student {
    private String name;
    private int[] marks;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public double calculateAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return sum / (double) marks.length;
    }

    public char calculateGrade() {
        double avg = calculateAverage();
        if (avg >= 90) {
            return 'A';
        } else if (avg >= 75) {
            return 'B';
        } else if (avg >= 60) {
            return 'C';
        } else if (avg >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public String getName() {
        return name;
    }
}

public class P10_StudentResultCardGenerator {

    // Parses a line like "Asha [80, 90, 70]" into a Student object.
    static Student parseLine(String line) {
        int bracketStart = line.indexOf('[');
        int bracketEnd = line.indexOf(']');

        String name = line.substring(0, bracketStart).trim();
        String marksPart = line.substring(bracketStart + 1, bracketEnd);

        String[] tokens = marksPart.split(",");
        int[] marks = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            marks[i] = Integer.parseInt(tokens[i].trim());
        }

        return new Student(name, marks);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            Student student = parseLine(line);
            System.out.printf(Locale.US, "%s: Average %.1f, Grade %c%n",
                    student.getName().toUpperCase(),
                    student.calculateAverage(),
                    student.calculateGrade());
        }
    }
}
