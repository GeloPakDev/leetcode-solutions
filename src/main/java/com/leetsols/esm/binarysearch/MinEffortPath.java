package com.leetsols.esm.binarysearch;

import java.util.Stack;

public class MinEffortPath {
    int m;
    int n;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumEffortPath(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        int left = 0;
        int right = 0;

        for (int[] row : heights) {
            for (int num : row) {
                right = Math.max(right, num);
            }
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, heights)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int effort, int[][] heights) {
        boolean[][] seen = new boolean[m][n];
        Stack<Pair> stack = new Stack<>();
        seen[0][0] = true;
        stack.push(new Pair(0, 0));

        while (stack.isEmpty()) {
            Pair pair = stack.pop();
            int row = pair.row;
            int col = pair.col;

            if (row == m - 1 && col == n - 1) {
                return true;
            }

            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];
                if (valid(nextRow, nextCol) && !seen[nextRow][nextCol]) {
                    if (Math.abs(heights[nextRow][nextCol] - heights[row][col]) <= effort) {
                        seen[nextRow][nextCol] = true;
                        stack.push(new Pair(nextRow, nextCol));
                    }
                }
            }
        }
        return false;
    }


    public boolean valid(int row, int col) {
        return 0 <= row && row < m && 0 <= col && col < n;
    }

    static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
