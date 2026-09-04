import java.util.*;

class FindMinimumRotated {

    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            // Array is already sorted
            if (nums[left] < nums[right]) {
                return nums[left];
            }

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {

                // Minimum is on the right
                left = mid + 1;

            } else {

                // Minimum is on the left including mid
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        FindMinimumRotated obj = new FindMinimumRotated();

        System.out.println(obj.findMin(nums));
    }
}