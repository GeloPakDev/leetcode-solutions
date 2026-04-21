package com.leetsols.esm.arrays;

public class RotateImage {
    /*
     *                   int temp = arr[row][col]
     * 15 -> 5  arr[row][col]     = arr[n - 1][col]
     * 16 -> 15 arr[n - 1][col]   = arr[n - 1][n - 1]
     * 11 -> 16 arr[n - 1][n - 1] = arr[row][n - 1]
     * 5  -> 11 arr[row][n - 1]   = temp
     *
     *                       int temp = arr[row + 1][col + 1]
     * 3  -> 4  arr[row + 1][col + 1] = arr[n - 2][col + 1]
     * 6  -> 3  arr[n - 2][col + 1]   = arr[n - 2][n - 2]
     * 8  -> 6  arr[n - 2][n - 2]     = arr[row + 1][n - 2]
     * 4  -> 8  arr[row + 1][n - 2]   = temp
     *
     * topLeft     = (row, col)
     * topRight    = (row, n - 1 - col)
     * bottomRight = (n - 1 - row, n - 1 - col)
     * bottomLeft  = (n - 1 - col, row)
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int row = 0; row < n; row++) {
            for (int col = row; col < n - 1 - row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[n - 1 - col][row];
                matrix[n - 1 - col][row] = matrix[n - 1 - row][n - 1 - col];
                matrix[n - 1 - row][n - 1 - col] = matrix[col][n - 1 - row];
                matrix[col][n - 1 - row] = temp;
            }
        }
    }
}
