package com.leetsols.esm.prefixsum;

public class NumMatrix {
    private int[][] matrix;
    private int[][] prefix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        prefix = new int[matrix.length][matrix[0].length];

        // Fill first column
        for (int i = 0; i < matrix.length; i++) {
            prefix[i][0] = matrix[i][0];
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 1; col < matrix[0].length; col++) {
                prefix[row][col] = prefix[row][col - 1] + matrix[row][col];
            }
        }
    }

    /*
     * Calculate the sum of the elements of matrix inside the rectangle defined by its:
     * - upper left corner  (row1, col1)
     * - lower right corner (row2, col2)
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        var sum = 0;
        for (int curr = row1; curr <= row2; curr++) {
            sum += prefix[curr][col2] - prefix[curr][col1] + matrix[curr][col1];
        }
        return sum;
    }
}
