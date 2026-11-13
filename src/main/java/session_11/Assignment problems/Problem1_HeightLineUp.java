import java.util.Arrays;
import java.util.Scanner;

public class Problem1_HeightLineUp {
    public static void lineUp(int[] heights) {
        if (heights.length < 2) { System.out.println("Pass 1: " + Arrays.toString(heights)); return; }
        int pass = 1;
        while (true) {
            boolean swapped = false;
            for (int j = 0; j < heights.length - 1; j++) {
                if (heights[j] > heights[j + 1]) {
                    int temp = heights[j]; heights[j] = heights[j + 1]; heights[j + 1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Pass " + pass++ + ": " + Arrays.toString(heights));
            if (!swapped) break;
        }
    }
    public static void main(String[] args) {
        lineUp(new int[]{4, 2, 5, 1});
        System.out.println("Custom test:");
        lineUp(new int[]{9, 3, 7, 1, 5});
    }
}
