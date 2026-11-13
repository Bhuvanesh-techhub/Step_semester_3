import java.util.Arrays;

class PairSumInSortedArray {
    // Returns the matching values, or null if no pair exists.
    public static int[] pairSumSorted(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            long sum = (long) nums[left] + nums[right];
            if (sum == target) return new int[]{nums[left], nums[right]};
            if (sum < target) left++;
            else right--;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] a = {-4, -1, 0, 3, 5, 9};
        int[] result = pairSumSorted(a, 4);
        System.out.println(result == null ? "Not Found" : Arrays.toString(result));
        System.out.println(pairSumSorted(new int[]{1, 2, 3}, 100) == null ? "Not Found" : "Found");
    }
}
