import java.util.Arrays;

public class Problem5 {

    static class Candidate implements Comparable<Candidate> {

        private String name;
        private double cgpa;
        private int codingScore;

        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        public double getCompositeScore() {
            return (cgpa * 10) + (codingScore * 0.5);
        }

        public String getName() {
            return name;
        }

        public double getCgpa() {
            return cgpa;
        }

        public int getCodingScore() {
            return codingScore;
        }

        // CGPA-only eligibility
        static boolean isEligible(double cgpa) {
            return cgpa >= 7.0;
        }

        // Combined CGPA + coding score eligibility
        static boolean isEligible(double cgpa, int codingScore) {
            return cgpa >= 6.5 && codingScore >= 60;
        }

        // Sort by composite score in descending order
        @Override
        public int compareTo(Candidate other) {
            return Double.compare(
                other.getCompositeScore(),
                this.getCompositeScore()
            );
        }
    }

    static String shortlistAndRank(Candidate[] candidates) {

        Candidate[] shortlisted = new Candidate[candidates.length];

        int count = 0;

        for (Candidate candidate : candidates) {

            if (Candidate.isEligible(candidate.getCgpa()) ||
                Candidate.isEligible(
                    candidate.getCgpa(),
                    candidate.getCodingScore()
                )) {

                shortlisted[count] = candidate;
                count++;
            }
        }

        // Create array containing only shortlisted candidates
        Candidate[] finalList = Arrays.copyOf(shortlisted, count);

        // Arrays.sort() uses compareTo()
        Arrays.sort(finalList);

        String result = "";

        for (int i = 0; i < finalList.length; i++) {

            result += (i + 1) + ". "
                    + finalList[i].getName()
                    + " ("
                    + String.format("%.1f",
                        finalList[i].getCompositeScore())
                    + ")";

            if (i < finalList.length - 1) {
                result += " | ";
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Candidate[] candidates = {

            new Candidate("Aisha", 8.2, 40),

            new Candidate("Rohit", 6.8, 65),

            new Candidate("Meena", 6.0, 90),

            new Candidate("Karan", 7.5, 20)
        };

        System.out.println(
            shortlistAndRank(candidates)
        );
    }
}