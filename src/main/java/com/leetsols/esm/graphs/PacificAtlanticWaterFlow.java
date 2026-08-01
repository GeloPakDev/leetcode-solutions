package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
    private static final int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int numRows;
    private int numCols;
    private int[][] landHeights;

    /*
     * Algorithm:
     * - We can reverse the appoach to start looking for the cells from the side of
     *   the Ocean, it means we can search for the cells which are higher, it will
     *   state that water from these cells can flow into an ocean(both Pacific and Atlantic)
     * - Intialize 2 queues (one for the Atlantic and one for the Pacific) that will be
     *   used for BFS
     * - Initialize 2 boolean arrays to keep track of the visited cells.
     * - Perform the BFS from both oceans
     * - Find the intersection of the res arrays
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights.length == 0 || heights[0].length == 0) return new ArrayList<>();

        numRows = heights.length;
        numCols = heights[0].length;
        landHeights = heights;

        var pacificQueue = new LinkedList<int[]>();
        var atlanticQueue = new LinkedList<int[]>();
        for (int i = 0; i < numRows; i++) {
            // Fill all cells in the column 0
            pacificQueue.offer(new int[]{i, 0});
            // Fill all cells in the last column
            atlanticQueue.offer(new int[]{i, numCols - 1});
        }

        for (int i = 0; i < numCols; i++) {
            // Fill all cells in the first row
            pacificQueue.offer(new int[]{0, i});
            // Fill all cells in the last row
            atlanticQueue.offer(new int[]{numRows - 1, i});
        }

        //Perform the BFS from both oceans to find the cells accessible by each ocean
        boolean[][] pacificIsReachable = bfs(pacificQueue);
        boolean[][] atlanticIsReachable = bfs(atlanticQueue);

        // Find all cells that are reachable from both oceans
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (pacificIsReachable[i][j] && atlanticIsReachable[i][j]) res.add(List.of(i, j));
            }
        }
        return res;
    }

    public boolean[][] bfs(Queue<int[]> queue) {
        var res = new boolean[numRows][numCols];

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            // The cell is reachable
            res[cell[0]][cell[1]] = true;

            for (int[] direction : directions) {
                int newRow = cell[0] + direction[0];
                int newCol = cell[1] + direction[1];

                // Check if the new cell with the bounds
                if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) continue;

                // Check if the new cell hash not already been visited;
                if (res[newRow][newCol]) continue;

                // Check that the new height of the cell is higher, so water can flow from the new cell to the old one
                if (landHeights[newRow][newCol] < landHeights[cell[0]][cell[1]]) continue;

                // The new cell is reachable
                queue.offer(new int[]{newRow, newCol});
            }
        }
        return res;
    }

    public void dfs(int row, int col, boolean[][] reachable) {
        reachable[row][col] = true;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = row + direction[1];

            // Check if the new cell with the bounds
            if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) continue;

            // Check if the new cell hash not already been visited;
            if (reachable[newRow][newCol]) continue;

            // Check that the new height of the cell is higher, so water can flow from the new cell to the old one
            if (landHeights[newRow][newCol] < landHeights[row][col]) continue;

            dfs(newRow, newCol, reachable);
        }
    }

    public boolean isValid(int row, int col, int newRow, int newCol, boolean[][] reachable) {
        return (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) &&
                (reachable[newRow][newCol]) &&
                (landHeights[newRow][newCol] < landHeights[row][col]);
    }
}
