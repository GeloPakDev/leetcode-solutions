package com.leetsols.esm.backtracking;

/*
 * Constraint
 * - Each row, column and sub-grid should not contain duplicates numbers
 *
 * Algorithm:
 * - At each step we do have 9 candidates at hand to fill the empty cell,
 *   we could filter out the candidates by checking the rules of Sudoku game.
 * - Among all candidates, try one by one placing the number on the board,
 *   after backtracking, revert solution, by removing the cell.
 * - Base case for to backtrack is the situation when solver cannot place
 *   any suitable candidate, or the solver finds solution of the problem
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    public boolean backtrack(char[][] board, int row, int col) {
        //Reach end of the row, move to the next one
        if (col == 9) return backtrack(board, row + 1, 0);

        // If the row is 9, we have filled entire board
        if (row == 9) return true;

        // Skip cell that are already filled
        if (board[row][col] != '.') return backtrack(board, row, col + 1);

        for (char ch = '1'; ch <= '9'; ch++) {
            if (isValid(board, row, col, ch)) {
                board[row][col] = ch;
                if (backtrack(board, row, col + 1)) return true;
                board[row][col] = '.';
            }
        }
        return false;
    }

    public boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;
            int targetRow = (row / 3) * 3;
            int targetCol = (col / 3) * 3;
            if (board[targetRow + i / 3][targetCol + i % 3] == num) return false;
        }
        return true;
    }
}
