// Problem 4: In-Place Array Reversal
import java.util.Scanner;

public class P4_InPlaceArrayReversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().trim();

        line = line.replace("[", "").replace("]", "");
        String[] parts = line.split(",");

        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i].trim());
        }

        // In-place reversal with two pointers, no second array used.
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i < nums.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        System.out.println(sb.toString());
    }
}
