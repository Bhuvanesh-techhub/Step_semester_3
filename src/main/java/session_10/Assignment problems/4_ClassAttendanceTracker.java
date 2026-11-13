// Problem 6 (listed fourth here): Class Attendance Tracker
//
// Complexity: O(n) time, O(1) extra space. Both answers come from the same
// single pass: a running streak counter grows on every 1 and resets on
// every 0, while a separate total just counts every 1 seen; the streak's
// largest value along the way is the longest run.
public class P4_ClassAttendanceTracker {

    static int[] attendanceSummary(int[] days) {
        int present = 0;
        int longestStreak = 0;
        int currentStreak = 0;

        for (int day : days) {
            if (day == 1) {
                present++;
                currentStreak++;
                longestStreak = Math.max(longestStreak, currentStreak);
            } else {
                currentStreak = 0;
            }
        }

        return new int[] { present, longestStreak };
    }

    public static void main(String[] args) {
        int[] r1 = attendanceSummary(new int[] { 1, 1, 0, 1, 1, 1, 0, 1 });
        System.out.println("Present: " + r1[0] + ", Longest streak: " + r1[1]);

        int[] r2 = attendanceSummary(new int[] { 0, 0, 0 });
        System.out.println("Present: " + r2[0] + ", Longest streak: " + r2[1]);
    }
}
