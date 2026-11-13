import java.util.Arrays;

public class Problem5_DiceThrowTally {
    // Counts each die face, then rebuilds the array in ascending order.
    public static int[] diceSort(int[] rolls) {
        int[] count = new int[7];
        for (int roll : rolls) {
            if (roll < 1 || roll > 6) throw new IllegalArgumentException("Each roll must be from 1 to 6");
            count[roll]++;
        }
        System.out.print("Counts: ");
        for (int face = 1; face <= 6; face++)
            System.out.print(face + ":" + count[face] + (face == 6 ? "" : "  "));
        System.out.println();
        int[] sorted = new int[rolls.length];
        int index = 0;
        for (int face = 1; face <= 6; face++)
            for (int occurrences = 0; occurrences < count[face]; occurrences++)
                sorted[index++] = face;
        return sorted;
    }
    public static void main(String[] args) {
        System.out.println("Sorted: " + Arrays.toString(diceSort(new int[]{4, 1, 6, 4, 2, 1, 4})));
        System.out.println("Custom: " + Arrays.toString(diceSort(new int[]{6, 6, 3, 1, 5, 3})));
    }
}
