import java.util.*;

class ThreeSum {

    public int[][] threeSum(int[] nums) {

        Arrays.sort(nums);

        ArrayList<int[]> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(new int[]{
                        nums[i],
                        nums[left],
                        nums[right]
                    });

                    left++;
                    right--;

                    // Skip duplicate left values
                    while (left < right &&
                           nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // Skip duplicate right values
                    while (left < right &&
                           nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {

                    left++;

                } else {

                    right--;
                }
            }
        }

        int[][] answer = new int[result.size()][];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        ThreeSum obj = new ThreeSum();

        int[][] result = obj.threeSum(nums);

        System.out.print("[");

        for (int i = 0; i < result.length; i++) {

            System.out.print(Arrays.toString(result[i]));

            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}