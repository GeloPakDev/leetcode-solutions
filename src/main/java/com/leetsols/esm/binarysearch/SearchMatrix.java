package com.leetsols.esm.binarysearch;

public class SearchMatrix {
    /*
     * Algorithm:
     * Base case:
     * - Contains no elements
     * - target < mat[0][0] || target > mat[mat.len - 1][mat.len - 1]
     *
     * Recursive case:
     * - As 2 base cases above doesn't meet.
     * - Seek along the middle column for an index of the row such:
     *  - mat[row - 1][mid] < target < mat[row][mid]
     *  - Existing matrix can be partitioned into 4 matrices where
     *    top-left and bottom-right cannot contain target.
     */

    private int[][] matrix;
    private int target;

    public boolean searchRec(int left, int up, int right, int down) {
        //Invalid matrix
        if (left > right || up > down) return false;
            // current element is larger than largest element
            // current element is smaller than smallest element
        else if (target < matrix[up][left] || target > matrix[down][right]) return false;

        int mid = left + (right - left) / 2;
        int row = up;
        //Locate the row in the column such that
        // mat[row - 1][mid] < target < mat[row][mid]
        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) return true;
            row++;
        }

        /*
         * Bottom-Left:
         * searchRec(left, mid - 1, row, down)
         * - From the far left to the column just below [mid-1]
         * - From the current row down to the [down] boundary
         *
         * Top-Right:
         * - From the column after the mid + 1 to the far right
         * - From the top [up] boundary down to the row, above our position
         */
        return searchRec(left, row, mid - 1, down) ||
                searchRec(mid + 1, up, right, row - 1);
    }

    public boolean searchMatrix(int[][] mat, int targ) {
        matrix = mat;
        target = targ;

        if (matrix == null || matrix.length == 0) return false;

        return searchRec(0, 0, matrix[0].length - 1, matrix.length - 1);
    }
}
