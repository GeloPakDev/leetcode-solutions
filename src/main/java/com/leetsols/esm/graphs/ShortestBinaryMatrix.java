package com.leetsols.esm.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBinaryMatrix {
    int n;
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        /*
         * - Top-left square is 1 -> which is not clear
         */
        if (grid[0][0] == 1) {
            return -1;
        }

        n = grid.length;
        boolean[][] seen = new boolean[n][n];
        seen[0][0] = true;

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 1));

        while (!queue.isEmpty()) {
            State state = queue.remove();
            int col = state.col;
            int row = state.row;
            int steps = state.steps;
            /*
             * - The end has been reached
             */
            if (row == n - 1 && col == n - 1) {
                return steps;
            }

            for (int[] direction : directions) {
                int nextRow = direction[0];
                int nextCol = direction[1];

                if (valid(row, col, grid) && !seen[row][col]) {
                    seen[row][col] = true;
                    queue.add(new State(nextRow, nextCol, steps + 1));
                }
            }
        }
        return -1;
    }

    public boolean valid(int row, int col, int[][] grid) {
        return 0 <= row && row < n &&
                0 <= col && col < n &&
                grid[row][col] == 0;
    }
}

