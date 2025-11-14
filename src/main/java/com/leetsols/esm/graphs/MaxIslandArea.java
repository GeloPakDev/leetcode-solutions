    //package com.leetsols.esm.graphs;
    //
    //public class MaxIslandArea {
    //    /*
    //     * - n -> length of the row
    //     * - m -> length of the column
    //     * - seen -> marked passed nodes
    //     */
    //    int n;
    //    int m;
    //    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //    boolean[][] seen;
    //
    //    public int maxAreaOfIsland(int[][] grid) {
    //        int maxArea = Integer.MIN_VALUE;
    //        n = grid.length;
    //        m = grid[0].length;
    //        seen = new boolean[n][m];
    //
    //        for (int row = 0; row < n; row++) {
    //            for (int col = 0; col < n; col++) {
    //                if (grid[row][col] == '1' && !seen[row][col]) {
    //                    seen[row][col] = true;
    //                    int curr = 0;
    //                    maxArea = Math.max(maxArea, dfs());
    //                }
    //            }
    //        }
    //        return maxArea;
    //    }
    //
    //    public boolean valid(int row, int col, char[][] grid) {
    //        return 0 <= row && row < n &&
    //                0 <= col && col < m &&
    //                grid[row][col] == '1';
    //    }
    //
    //    public int dfs(int row, int col, char[][] grid, int curr) {
    //        for (int[] direction : directions) {
    //            int nextRow = row + direction[0];
    //            int nextCol = col + direction[1];
    //
    //            if (valid(row, col, grid) && !seen[col][row]) {
    //                seen[row][col] = true;
    //
    //            }
    //        }
    //    }
    //
    //}
