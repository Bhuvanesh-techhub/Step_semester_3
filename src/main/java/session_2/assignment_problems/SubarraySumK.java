import java.util.*;

class SubarraySumK {

    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Empty prefix
        map.put(0, 1);

        int currentSum = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            currentSum = currentSum + nums[i];

            int requiredSum = currentSum - k;

            if (map.containsKey(requiredSum)) {
                count = count + map.get(requiredSum);
            }

            if (map.containsKey(currentSum)) {
                map.put(currentSum, map.get(currentSum) + 1);
            } else {
                map.put(currentSum, 1);
            }
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        SubarraySumK obj = new SubarraySumK();

        System.out.println(obj.subarraySum(nums, k));
    }
}