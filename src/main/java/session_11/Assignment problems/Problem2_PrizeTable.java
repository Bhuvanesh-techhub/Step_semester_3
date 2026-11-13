import java.util.Arrays;

public class Problem2_PrizeTable {
    // Selection sort in descending order; count only swaps actually performed.
    public static int prizeTable(int[] marks) {
        int swaps = 0;
        for (int i = 0; i < marks.length - 1; i++) {
            int largest = i;
            for (int j = i + 1; j < marks.length; j++)
                if (marks[j] > marks[largest]) largest = j;
            if (largest != i) {
                int temp = marks[i]; marks[i] = marks[largest]; marks[largest] = temp;
                swaps++;
            }
        }
        return swaps;
    }
    public static void main(String[] args) {
        int[] first = {45, 90, 60, 75};
        System.out.println(Arrays.toString(first) + " swaps = " + prizeTable(first));
        int[] second = {80, 70, 60};
        System.out.println(Arrays.toString(second) + " swaps = " + prizeTable(second));
        int[] custom = {25, 90, 10, 65, 40};
        System.out.println("Custom: " + Arrays.toString(custom) + " swaps = " + prizeTable(custom));
    }
}
