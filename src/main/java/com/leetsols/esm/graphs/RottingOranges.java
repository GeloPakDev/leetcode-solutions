package com.leetsols.esm.graphs;


import java.util.LinkedList;

public class RottingOranges {
    /*
     * Algorithm:
     * - Check positions of all rotten oranges
     * - Count the number of all fresh oranges
     * - If number of fresh oranges is 0 -> return -1
     * - Number of fresh oranges used in the while loop as we
     *   pass over the queue.
     * - Iterate over the grid
     */
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0) return -1;

        var queue = new LinkedList<int[]>();

        var freshOranges = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 2) {
                    queue.offer(new int[]{row, col});
                } else if (grid[row][col] == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) return 0;

        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty() && freshOranges > 0) {
            var size = queue.size();

            // Iterate over the current rotten oranges in the queue
            for (int i = 0; i < size; i++) {
                var curr = queue.poll();
                var row = curr[0];
                var col = curr[1];

                for (int[] direction : directions) {
                    int nr = row + direction[0];
                    int nc = col + direction[1];

                    /*
                     * If the orange is fresh, decrement the fresh orange and add this cell
                     * to the next candidate for exploration of the fresh oranges around it
                     */
                    if (isFresh(nr, nc, grid)) {
                        grid[nr][nc] = 2;
                        freshOranges--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            minutes++;
        }
        return freshOranges == 0 ? minutes : -1;
    }

    /*
     * Boundaries:
     * - (nextRow >= 0) && (nextRow <= rows) -> out of boundaries for row
     * - (nextCol >= 0) && (nextCol <= cols) -> out of boundaries for col
     * - grid[nextRow][nextCol] == 1         -> cell has a fresh orange
     */
    public boolean isFresh(int nextRow, int nextCol, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        return (nextRow >= 0 && nextRow < rows)
                && (nextCol >= 0 && nextCol < cols)
                && grid[nextRow][nextCol] == 1;
    }
}