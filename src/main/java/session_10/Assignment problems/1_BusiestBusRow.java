// Problem 10 (listed first here): Busiest Bus Row
//
// Complexity: O(r * c) time — every seat in the grid is visited exactly
// once (r rows, c columns). O(1) extra space — only a running total, the
// best total, and the best row index are kept, no matter how large the grid.
public class P1_BusiestBusRow {

    // Returns {rowIndex, total} for the row with the largest seat total.
    // Ties keep the smaller row index because we only update on a strictly
    // larger total.
    static int[] busiestRow(int[][] grid) {
        int bestRow = 0;
        int bestTotal = Integer.MIN_VALUE;

        for (int i = 0; i < grid.length; i++) {
            int total = 0;
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];
            }
            if (total > bestTotal) {
                bestTotal = total;
                bestRow = i;
            }
        }

        return new int[] { bestRow, bestTotal };
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 2, 0, 1 },
                { 3, 3, 1 },
                { 1, 1, 1 }
        };

        int[] result = busiestRow(grid);
        System.out.println("Row " + result[0] + ", Total " + result[1]);
    }
}
