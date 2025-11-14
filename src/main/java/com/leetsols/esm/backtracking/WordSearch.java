package com.leetsols.esm.backtracking;

/*
 * Problem type: Array, String, Backtracking, Depth-First Search, Matrix
 * Number: 79 Word Search
 */
public class WordSearch {
    /*
     * - Use seen set to avoid using the same letter in the same path and remove as backtracking
     * - Apply the DFS to traverse the path
     * - Use set to avoid using a square multiple times in the same path
     * - Build a word by traversing the squares that have next letter
     */
    int m;
    int n;
    String target;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][] seen;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        target = word;
        seen = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                /*
                 * - Search for the start letter in matrix
                 */
                if (board[row][col] == word.charAt(0)) {
                    seen[row][col] = true;
                    if (backtrack(row, col, 1, board)) {
                        return true;
                    }
                    seen[row][col] = false;
                }
            }
        }
        return false;
    }

    public boolean backtrack(int row, int col, int curr, char[][] board) {
        if (curr == target.length()) {
            return true;
        }

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            if (valid(nextRow, nextCol) && !seen[nextRow][nextCol]) {
                if (board[nextRow][nextCol] == target.charAt(curr)) {
                    seen[nextRow][nextCol] = true;
                    if (backtrack(nextRow, nextCol, curr + 1, board)) {
                        return true;
                    }
                    seen[nextRow][nextCol] = false;
                }
            }
        }
        return false;
    }

    public boolean valid(int row, int col) {
        return 0 <= row && row < m && 0 <= col && col < n;
    }
}
