package com.leetsols.esm.dp;

import java.util.Arrays;

public class UniquePath {
    /*
     * Approach:
     * - Robot can move either right or down
     * - To reach all cells in the first row it can only go to the right
     * - To reach all cells in the first col it can only go to the down
     * - To reach the inner cells, it can move either from the left or from
     *   the cell above.
     * - Total number of paths to move into the (m, n) cell is
     *   uniquePath(m - 1, n) + uniquePath(m, n - 1)
     *
     * Algorithm:
     * - Initiate the 2d array dp[m][n] = number of paths, at the start the number
     *   of paths in the first column and row is equal to 1
     * - Iterate over all "inner" cells:
     *  - dp[col][row] = dp[col - 1][row] + dp[col][row + 1]
     * - Return dp[m - 1][n - 1]
     */

    int[][] memo;

    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(m - 1, n - 1);
    }

    public int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];

        for (int[] arr : dp) Arrays.fill(arr, 1);

        for (int col = 1; col < m; col++) {
            for (int row = 1; row < n; row++) {
                dp[col][row] = dp[col - 1][row] + dp[col][row - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    private int dp(int row, int col) {
        // start of the matrix
        if (row + col == 0) {
            return 1;
        }

        if (memo[row][col] != -1) {
            return memo[row][col];
        }

        int ways = 0;
        if (row > 0) {
            ways += dp(row - 1, col);
        }
        if (col > 0) {
            ways += dp(row, col - 1);
        }

        memo[row][col] = ways;
        return ways;
    }
}
