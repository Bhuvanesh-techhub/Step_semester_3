// Problem 4: Examination Question Grader
import java.util.*;

abstract class Question {
    protected String questionText;
    protected String correctAnswer;
    protected String studentAnswer;
    protected int points;

    public Question(String questionText, String correctAnswer, String studentAnswer, int points) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    public abstract double calculateScore();

    public abstract String getType();
}

class McqQuestion extends Question {
    public McqQuestion(String questionText, String correctAnswer, String studentAnswer, int points) {
        super(questionText, correctAnswer, studentAnswer, points);
    }

    public double calculateScore() {
        return correctAnswer.equals(studentAnswer) ? points : 0;
    }

    public String getType() {
        return "MCQ";
    }
}

class TfQuestion extends Question {
    public TfQuestion(String questionText, String correctAnswer, String studentAnswer, int points) {
        super(questionText, correctAnswer, studentAnswer, points);
    }

    public double calculateScore() {
        return correctAnswer.equals(studentAnswer) ? points : 0;
    }

    public String getType() {
        return "TF";
    }
}

class EssayQuestion extends Question {
    public EssayQuestion(String questionText, String correctAnswer, String studentAnswer, int points) {
        super(questionText, correctAnswer, studentAnswer, points);
    }

    public double calculateScore() {
        String[] keywords = correctAnswer.split(",");
        String lowerStudentAnswer = studentAnswer.toLowerCase();

        int matched = 0;
        for (String keyword : keywords) {
            if (lowerStudentAnswer.contains(keyword.trim().toLowerCase())) {
                matched++;
            }
        }

        if (matched >= 2) {
            return points * 0.75;
        } else if (matched == 1) {
            return points * 0.50;
        } else {
            return 0;
        }
    }

    public String getType() {
        return "ESSAY";
    }
}

public class P4_ExamQuestionGrader {

    static Question createQuestion(String type, String text, String correct, String student, int points) {
        switch (type) {
            case "MCQ":
                return new McqQuestion(text, correct, student, points);
            case "TF":
                return new TfQuestion(text, correct, student, points);
            case "ESSAY":
                return new EssayQuestion(text, correct, student, points);
            default:
                throw new IllegalArgumentException("Unknown question type: " + type);
        }
    }

    // Tokenizes a line where some tokens are "quoted and may contain spaces"
    // and the final token (Points) is a plain, unquoted integer.
    static List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        int n = line.length();
        while (i < n) {
            while (i < n && line.charAt(i) == ' ') {
                i++;
            }
            if (i >= n) {
                break;
            }
            if (line.charAt(i) == '"') {
                int j = line.indexOf('"', i + 1);
                tokens.add(line.substring(i + 1, j));
                i = j + 1;
            } else {
                int j = i;
                while (j < n && line.charAt(j) != ' ') {
                    j++;
                }
                tokens.add(line.substring(i, j));
                i = j;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> tokens = tokenize(sc.nextLine());
            String type = tokens.get(0);
            String text = tokens.get(1);
            String correct = tokens.get(2);
            String student = tokens.get(3);
            int points = Integer.parseInt(tokens.get(4));
            questions.add(createQuestion(type, text, correct, student, points));
        }

        double total = 0;
        for (Question q : questions) {
            double score = q.calculateScore();
            total += score;
            System.out.printf(Locale.US, "%s: %.2f%n", q.getType(), score);
        }

        System.out.printf(Locale.US, "Total Score: %.2f%n", total);
    }
}
