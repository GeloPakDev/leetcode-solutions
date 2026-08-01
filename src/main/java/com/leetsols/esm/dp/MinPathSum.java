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
     * Algorithm:
     * - Count sequence sum in the first row as we can move to the right
     * - Count sequence sum in the first col as we can move down
     * - Iterate over the whole grid and count the minimum sum of the current cell
     *   by adding the current value from the grid[row][col] and one of the values
     *   that placed to the left and above the item
     */
    public int minPathSumDP(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];

        for (int i = 0; i < m; i++) Arrays.fill(memo[i], 1);

        memo[0][0] = grid[0][0];
        // Fill the first col
        for (int i = 1; i < m; i++) memo[i][0] = grid[i][0] + memo[i - 1][0];

        // Fill the first row
        for (int i = 1; i < n; i++) memo[0][i] = grid[0][i] + memo[0][i - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int above = memo[i - 1][j] + grid[i][j];
                int left = memo[i][j - 1] + grid[i][j];
                memo[i][j] = Math.min(above, left);
            }
        }
        return memo[m - 1][n - 1];
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
