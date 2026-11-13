class WarehouseBinGridScan {
    // Returns {total item count, max row, max column}; coordinates are zero-based.
    public static long[] warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return new long[]{0, -1, -1};
        long total = 0;
        int max = Integer.MIN_VALUE, maxRow = 0, maxCol = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int value = grid[row][col];
                total += value;
                // Strictly greater preserves the first coordinate when values tie.
                if (value > max) { max = value; maxRow = row; maxCol = col; }
            }
        }
        return new long[]{total, maxRow, maxCol};
    }

    public static void main(String[] args) {
        int[][] grid = {{4, 9, 2}, {7, 1, 6}, {3, 12, 5}};
        long[] answer = warehouseSummary(grid);
        System.out.println("Total = " + answer[0]);
        System.out.println("Max coordinate = (" + answer[1] + ", " + answer[2] + ")");
    }
}
