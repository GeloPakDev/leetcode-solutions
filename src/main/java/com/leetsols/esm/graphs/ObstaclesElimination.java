package com.leetsols.esm.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class ObstaclesElimination {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestPath(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;

        Queue<State> queue = new LinkedList<>();
        boolean[][][] seen = new boolean[m][n][k + 1];
        queue.add(new State(0, 0, k, 0));
        seen[0][0][k] = true;

        while (!queue.isEmpty()) {
            State state = queue.remove();
            int row = state.row;
            int col = state.col;
            int remain = state.remain;
            int steps = state.steps;

            if (row == m - 1 && col == n - 1) {
                return steps;
            }

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextColumn = col + direction[1];
                if (valid(nextRow, nextColumn)) {
                    if (grid[nextRow][nextColumn] == 0) {
                        if (!seen[nextRow][nextColumn][remain]) {
                            seen[nextRow][nextColumn][remain] = true;
                            queue.add(new State(nextRow, nextColumn, remain, steps + 1));
                        }
                    } else if (remain > 0 && !seen[nextRow][nextColumn][remain - 1]) {
                        seen[nextRow][nextColumn][remain - 1] = true;
                        queue.add(new State(nextRow, nextColumn, remain - 1, steps - 1));
                    }
                }
            }
        }

        return -1;
    }

    public boolean valid(int row, int col) {
        return 0 <= row && row < m &&
                0 <= col && col < n;
    }

    static class State {
        int row;
        int col;
        int remain;
        int steps;

        State(int row, int col, int remain, int steps) {
            this.row = row;
            this.col = col;
            this.remain = remain;
            this.steps = steps;
        }
    }
}
