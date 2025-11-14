package com.leetsols.esm.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class EscapeMaze {
    int n;
    int m;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int nearestExit(char[][] maze, int[] entrance) {
        n = maze.length;
        m = maze[0].length;

        int initRow = entrance[0];
        int initCol = entrance[1];

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(initRow, initCol, 0));

        maze[initRow][initCol] = '+';

        while (!queue.isEmpty()) {
            State state = queue.remove();
            int row = state.row;
            int col = state.column;
            int steps = state.steps;

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextColumn = col + direction[1];

                if (valid(nextRow, nextColumn, maze)) {
                    if (isExit(nextRow, n, nextColumn, m)) {
                        return steps + 1;
                    }

                    maze[nextRow][nextColumn] = '+';
                    queue.add(new State(nextRow, nextColumn, steps + 1));
                }
            }
        }
        return -1;
    }

    public boolean valid(int row, int column, char[][] maze) {
        return 0 <= row && row < n &&
                0 <= column && column < m &&
                maze[row][column] == '.';
    }

    public boolean isExit(int nextRow, int n, int nextCol, int m) {
        return nextRow == 0 || nextRow == n - 1 ||
                nextCol == 0 || nextCol == m - 1;
    }

    static class State {
        int row;
        int column;
        int steps;

        public State(int row, int column, int steps) {
            this.row = row;
            this.column = column;
            this.steps = steps;
        }
    }
}
