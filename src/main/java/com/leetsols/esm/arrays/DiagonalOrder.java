package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Matrix, Simulation
 * Number: 498 Diagonal Traversal
 */
public class DiagonalOrder {
    /*
     * GOING UP
     * - At the TAIL end of DOWNWARDS diagonal
     * - HEAD would be the node BELOW the TAIL of the previous diagonal
     * - Unless the TAIL lies in the LAST ROW, HEAD will be RIGHT next to the tail
     *
     * GOING DOWN
     * - At the TAIL end of UPWARDS diagonal
     * - HEAD would be the node RIGHT of the TAIL of the previous diagonal
     * - Unless the tail lies in the LAST COLUMN, HEAD will be BELOW the tail
     */
    public int[] findDiagonalOrder(int[][] mat) {
        // Check for the EMPTY matrix
        if (mat == null || mat.length == 0) {
            return new int[0];
        }

        // Variables to track the SIZE of the matrix
        int N = mat.length;
        int M = mat[0].length;

        // Indices for tracking progress
        int col = 0;
        int row = 0;

        // diagonal checking variable
        int direction = 1;

        int[] result = new int[N * M];
        int r = 0;

        // Iterate over all elements in the array
        while (row < N && col < M) {

            // Add current element to the result matrix
            result[r++] = mat[row][col];

            /*
             * Move along the current direction based on the direction
             * [i, j] -> [i - 1, j + 1] -> going up
             * [i, j] -> [i + 1, j - 1] -> going down
             */
            int newRow = row + (direction == 1 ? -1 : 1);
            int newCol = col + (direction == 1 ? 1 : -1);

            // Check if the next element in the diagonal within the bounds or not
            if (newRow < 0 || newRow == N || newCol < 0 || newCol == M) {
                // GOING UPWARDS
                if (direction == 1) {
                    row += (col == M - 1 ? 1 : 0);
                    col += (col < M - 1 ? 1 : 0);
                } else {
                    col += (row == N - 1 ? 1 : 0);
                    row += (row < N - 1 ? 1 : 0);
                }
                direction = 1 - direction;
            } else {
                row = newRow;
                col = newCol;
            }
        }
        return result;
    }
}
