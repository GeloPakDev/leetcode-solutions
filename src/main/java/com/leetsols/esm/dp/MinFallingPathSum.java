package com.leetsols.esm.dp;

public class MinFallingPathSum {
    /*
     * - falling path - starts at any element in the first row and going to the next element in the next row that is either
     *  - below
     *  - diagonally left/right
     *  - next element from position (row, column)
     *
     *   - (row + 1, col - 1) - diagonally left
     *   - (row + 1, col)     - below
     *   - (row + 1, col + 1) - diagonally right
     *
     *   [2 1 3]
     *   [6 5 4]
     *   [7 8 9]
     *
     * Naive approach or Brute force
     *
     *  - Base case:
     *   - row or col values are not within the boundaries
     *   - last row reached
     *
     *  - Recursively explore all paths
     *   - minPath = Math.min(findMin(row + 1, col + 1),
     *                        findMin(row + 1, col),
     *                        findMin(row + 1, col - 1))
     */
    public int minFallingPathSum(int[][] matrix) {
        int minFallingSum = Integer.MAX_VALUE;
        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingSum = Math.min(minFallingSum, findMinFallingPathSum(matrix, 0, startCol));
        }

        return minFallingSum;
    }

    public int findMinFallingPathSum(int[][] matrix, int row, int column) {
        /*
         * Check if we are out of right or left boundary of the matrix
         */
        if (column < 0 || column == matrix.length) {
            return Integer.MAX_VALUE;
        }

        /*
         * Check if we have reached the last row
         */
        if (row == matrix.length - 1) {
            return matrix[row][column];
        }

        int left = findMinFallingPathSum(matrix, row + 1, column);
        int middle = findMinFallingPathSum(matrix, row + 1, column + 1);
        int right = findMinFallingPathSum(matrix, row + 1, column - 1);

        return Math.min(left, Math.min(middle, right)) + matrix[row][column];
    }

    public int minFallingPathSumTwo(int[][] matrix) {
        int minFallingPathSum = Integer.MAX_VALUE;
        Integer[][] memo = new Integer[matrix.length][matrix[0].length];

        for (int startCol = 0; startCol < matrix.length; startCol++) {
            minFallingPathSum = Math.min(minFallingPathSum, findFallingPathSum(matrix, 0, startCol, memo));
        }
        return minFallingPathSum;
    }

    private int findFallingPathSum(int[][] matrix, int row, int col, Integer[][] memo) {
        if (col < 0 || col == memo.length) {
            return Integer.MAX_VALUE;
        }

        if (row == matrix.length - 1) {
            return matrix[row][col];
        }

        if (memo[row][col] != null) {
            return memo[row][col];
        }

        int left = findFallingPathSum(matrix, row + 1, col, memo);
        int middle = findFallingPathSum(matrix, row + 1, col + 1, memo);
        int right = findFallingPathSum(matrix, row + 1, col - 1, memo);
        memo[row][col] = Math.min(left, Math.min(middle, right)) + matrix[row][col];
        return memo[row][col];
    }
}
