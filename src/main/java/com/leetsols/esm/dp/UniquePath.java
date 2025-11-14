package com.leetsols.esm.dp;

import java.util.Arrays;

public class UniquePath {
    /*
     * - Return the number os possible unique paths
     * - Square above -> row - 1, col
     * - Square left  -> row, col - 1
     * - Sum of the number of ways to reach (row - 1, col) and (row, col - 1)
     * - dp(row, col) = dp(row - 1, col) + dp(row, col - 1)
     *
     * - Base cases:
     *  - (0, 0)
     *  - dp(0, 0) = 1
     */

    int[][] memo;

    public int uniquePaths(int m, int n) {
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(m - 1, n - 1);
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
