package com.leetsols.esm.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] seen;

    /*
     * Algorithm DFS:
     * - Treat the 2d grid map as an undirected graph and there is
     *   an edge between 2 horizontally or vertically adjacent nodes
     *   of value '1's.
     * - Count the number of root nodes that triggers the [dfs], this number
     *   would be the number of islands since each [DFS] starting at some
     *   root identifies an island.
     *
     * Algorithm BFS:
     * - Make a linear scan of the 2D matrix, as soon as you have encounter
     *   the '1', that is the root node, start BFS from that node, and mark it
     *   as '0', also put it into the queue.
     */
    public int numIslands(char[][] grid) {
        int ans = 0;
        m = grid.length;
        n = grid[0].length;
        seen = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1' && !seen[row][col]) {
                    ans++;
                    seen[row][col] = true;
                    dfs(row, col, grid);
                }
            }
        }
        return ans;
    }

    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        m = grid.length;
        n = grid[0].length;
        int numOfIslands = 0;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1') {
                    numOfIslands++;
                    bfs(row, col, grid);
                }
            }
        }
        return numOfIslands;
    }

    /*
     * - Row and Column doesn't exceed over the grid
     * - If the square is 1 -> land
     */
    public boolean valid(int row, int col, char[][] grid) {
        return 0 <= row && row < m &&
                0 <= col && col < n &&
                grid[row][col] == '1';
    }

    public void dfs(int row, int col, char[][] grid) {
        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (valid(nextRow, nextCol, grid) && !seen[nextRow][nextCol]) {
                seen[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, grid);
            }
        }
    }

    public void bfs(int row, int col, char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        grid[row][col] = '0';

        while (!queue.isEmpty()) {
            var coordinate = queue.poll();
            int currRow = coordinate[0];
            int currCol = coordinate[1];

            for (int[] direction : directions) {
                int nextRow = currRow + direction[0];
                int nextCol = currCol + direction[1];
                if (valid(nextRow, nextCol, grid)) {
                    queue.offer(new int[]{nextRow, nextCol});
                    grid[nextRow][nextCol] = '0';
                }
            }
        }
    }
}

