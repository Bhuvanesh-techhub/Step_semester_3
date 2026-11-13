// Problem 7 (listed third here): Second-Best Score
//
// Complexity: O(n) time, O(1) extra space — a single pass tracks the
// highest and second-highest distinct values seen so far. Sorting first
// would cost O(n log n) time (and O(n) or O(log n) extra space depending on
// the sort), which is strictly more work than this one-pass approach needs.
public class P3_SecondBestScore {

    static int secondHighest(int[] scores) {
        int highest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int score : scores) {
            if (score > highest) {
                second = highest;
                highest = score;
            } else if (score < highest && score > second) {
                second = score;
            }
            // score == highest is skipped: it is a repeat of the current
            // best, not a new distinct second-best.
        }

        return second == Integer.MIN_VALUE ? -1 : second;
    }

    public static void main(String[] args) {
        System.out.println(secondHighest(new int[] { 45, 78, 92, 78, 60 }));
        System.out.println(secondHighest(new int[] { 50, 50, 50 }));
    }
}
