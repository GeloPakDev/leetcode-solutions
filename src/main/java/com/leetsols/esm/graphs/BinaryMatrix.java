package com.leetsols.esm.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryMatrix {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] updateMatrix(int[][] mat) {
        m = mat.length;
        n = mat[0].length;

        Queue<State> queue = new LinkedList<>();
        boolean[][] seen = new boolean[m][n];

        /*
         * - Iterate over the matrix to count all 0's and put them into the queue as level 0
         */
        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                if (mat[row][column] == 0) {
                    queue.add(new State(row, column, 1));
                    seen[row][column] = true;
                }
            }
        }
        /*
         * - Start BFS
         */
        while (!queue.isEmpty()) {
            State state = queue.remove();
            int row = state.row;
            int column = state.col;
            int steps = state.steps;
            /*
             * - Explore surrounding squares on consistency of 1
             * - Mark visited square
             * - Mark number of steps from current 0's node
             */
            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextColumn = column + direction[1];
                if (valid(nextRow, nextColumn, mat) && !seen[nextRow][nextColumn]) {
                    seen[nextRow][nextColumn] = true;
                    queue.add(new State(nextRow, nextColumn, steps + 1));
                    mat[nextRow][nextColumn] = steps;
                }
            }
        }
        return mat;
    }

    /*
     * Required to check the bounds of the matrix and the MAIN condition where square equals 1
     */
    public boolean valid(int row, int column, int[][] matrix) {
        return 0 <= row && row < m &&
                0 <= column && column < n &&
                matrix[row][column] == 1;
    }
}
