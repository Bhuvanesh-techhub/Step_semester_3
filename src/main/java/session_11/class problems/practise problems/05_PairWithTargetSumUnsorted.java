import java.util.HashSet;
import java.util.Set;

class PairWithTargetSumUnsorted {
    // Expected O(n) time and O(n) extra space.
    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int value : nums) {
            int complement = target - value;
            if (seen.contains(complement)) return true;
            seen.add(value);
        }
        return false;
    }

    // Alternative: brute force takes O(n^2) time and O(1) extra space.
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if ((long) nums[i] + nums[j] == target) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasPairWithSum(new int[]{2, 7, 11, 15}, 9));
        System.out.println(hasPairWithSum(new int[]{3, 4, 6}, 20));
    }
}
