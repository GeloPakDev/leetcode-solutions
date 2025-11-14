package com.leetsols.esm.dp;

import java.util.Arrays;

public class MinPathSum {
    int m;
    int n;
    int[][] memo;
    int[][] grid;

    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        this.grid = grid;
        return dp(m - 1, n - 1);
    }

    /*
     * - minimum path from (0, 0) to (row, col)
     * - dp(m - 1, n - 1) -> answer
     * - dp(row, col) = grid[row][col] + min(dp(row - 1, col), dp(row, col - 1))
     */
    public int dp(int row, int col) {
        if (row + col == 0) {
            return grid[row][col];
        }
        if (memo[row][col] != 1) {
            return memo[row][col];
        }
        int ans = Integer.MAX_VALUE;
        if (row > 0) {
            ans = Math.min(ans, dp(row - 1, col));
        }
        if (col > 0) {
            ans = Math.min(ans, dp(row, col - 1));
        }

        memo[row][col] = grid[row][col] + ans;
        return memo[row][col];
    }
}
