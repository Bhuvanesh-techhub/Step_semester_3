package session_2.assignment_problems;

import java.util.*;

class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        int leftProduct = 1;

        // Store product of all elements to the left
        for (int i = 0; i < n; i++) {
            answer[i] = leftProduct;
            leftProduct = leftProduct * nums[i];
        }

        int rightProduct = 1;

        // Multiply by product of all elements to the right
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
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

        ProductExceptSelf obj = new ProductExceptSelf();

        int[] result = obj.productExceptSelf(nums);

        System.out.println(Arrays.toString(result));
    }
}