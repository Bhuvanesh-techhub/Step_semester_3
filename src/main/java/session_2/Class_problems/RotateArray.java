import java.util.*;

class RotateArray {
    public int[] rotateArray(int[] nums, int k) {
        k = k % nums.length;

        int[] newArray = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            newArray[(i + k) % nums.length] = nums[i];
        }

        return newArray;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        RotateArray obj = new RotateArray();

        int[] result = obj.rotateArray(nums, k);

        System.out.println(Arrays.toString(result));
    }
}