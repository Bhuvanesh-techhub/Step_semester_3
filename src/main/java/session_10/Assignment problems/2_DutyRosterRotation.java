// Problem 8 (listed second here): Duty Roster Rotation
//
// Complexity: O(n) time and O(n) extra space, where n is the number of
// names — each name is placed once into a new array. Rotating one place at
// a time, k times, would cost O(k * n): with k up to 10^9 and n only up to
// 100, that is hundreds of millions of wasted shifts for work that k % n
// (at most 99 real rotations) and a single pass already finish.
public class P2_DutyRosterRotation {

    static String[] rotateRoster(String[] names, long k) {
        int n = names.length;
        int shift = (int) (k % n); // strip full turns that land back where they started

        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[(i + shift) % n] = names[i];
        }
        return result;
    }

    public static void main(String[] args) {
        String[] names = { "A", "B", "C", "D", "E" };

        System.out.println(java.util.Arrays.toString(rotateRoster(names, 2)));
        System.out.println(java.util.Arrays.toString(rotateRoster(names, 7)) + "  // 7 places is the same as 2");
    }
}
