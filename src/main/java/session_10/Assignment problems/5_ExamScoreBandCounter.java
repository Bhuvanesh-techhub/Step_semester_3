// Problem 4 (listed fifth here): Exam Score Band Counter
//
// Complexity: a linear scan is O(n) time. This binary-search solution is
// O(log n) time and O(1) extra space, which matters because the array is
// queried very often and can hold up to 10^6 scores. Duplicates at the band
// edges are exactly why a plain binary search (which just stops at any
// matching element) is not enough: we need the FIRST position >= low and
// the FIRST position > high, so every duplicate at the low edge is included
// and every duplicate at the high edge is included too, without over- or
// under-counting.
public class P5_ExamScoreBandCounter {

    // First index where scores[i] >= target.
    static int lowerBound(int[] scores, int target) {
        int lo = 0;
        int hi = scores.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (scores[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // First index where scores[i] > target.
    static int upperBound(int[] scores, int target) {
        int lo = 0;
        int hi = scores.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (scores[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    static int countInBand(int[] scores, int low, int high) {
        int from = lowerBound(scores, low);
        int to = upperBound(scores, high);
        return to - from;
    }

    public static void main(String[] args) {
        int[] scores = { 35, 42, 42, 50, 58, 58, 58, 63, 71, 88 };

        System.out.println(countInBand(scores, 42, 58));
        System.out.println(countInBand(scores, 90, 100));
    }
}
