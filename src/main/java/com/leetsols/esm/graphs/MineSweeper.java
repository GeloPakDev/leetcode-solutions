package com.leetsols.esm.graphs;

/*
 * Problem type: Array, Depth-First Search, Breadth-First Search, Matrix
 * Number: 529. Mine Sweeper
 */
public class MineSweeper {
    /*
     * Algorithm:
     * - If click on mine 'M', change it to 'X' and return
     * - If click on empty square 'E', count how many mines are in the surrounding cells
     *  - If the count is greater than 0, change 'E' to digit and stop exploring the path
     *  - If the count is 0, change 'E' to 'B' and recursively call the function on all 8 neighbors
     * - 8 directions: Up, Down, Left, Right, and 4 diagonals
     */
    private final int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    private int R, C;

    /*
     * - If we click on the mine, loose immediately
     * - Otherwise start exploring the path in depth
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        R = board.length;
        C = board[0].length;
        int r = click[0];
        int c = click[1];

        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }

        dfs(board, r, c);
        return board;
    }

    /*
     * First Check
     * - isValid(r, c) check the outside of the board
     * - board[r][c] != 'E' check the revealed empty cell
     *
     * Second Check
     * - Count the number of mines around the cell
     *
     * Third Check
     * - mineCount > 0 -> cell becomes number, we stop here
     * - mineCount == 0 -> cell becomes blank, recursively call [dfs] on
     *                     all 8 neighbors to keep the chain reaction going
     */
    private void dfs(char[][] board, int r, int c) {
        if (!isValid(r, c) || board[r][c] != 'E') return;

        int mineCount = countAdjacentMines(board, r, c);
        if (mineCount > 0) {
            board[r][c] = (char) (mineCount + '0');
        } else {
            board[r][c] = 'B';
            for (int[] direction : directions) {
                dfs(board, r + direction[0], c + direction[1]);
            }
        }
    }

    private int countAdjacentMines(char[][] board, int r, int c) {
        int count = 0;
        for (int[] direction : directions) {
            int nextRow = r + direction[0];
            int nextCol = c + direction[1];
            if (isValid(nextRow, nextCol) && board[nextRow][nextCol] == 'M') count++;
        }
        return count;
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
