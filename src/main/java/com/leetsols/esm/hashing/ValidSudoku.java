package com.leetsols.esm.hashing;

import java.util.HashSet;

public class ValidSudoku {
    /*
     * Validation:
     * - each row contain number from 1-9 without repetition
     * - each column contain number from 1-9 without repetition
     * - each sub-grid (3x3) contain digits 1-9 without repetition
     */
    public boolean isValidSudoku(char[][] board) {
        int N = 9;
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] box = new HashSet[N];

        for (int i = 0; i < N; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            box[i] = new HashSet<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                char val = board[row][col];

                if (val == '.') continue;

                if (rows[row].contains(val)) return false;
                rows[row].add(val);

                if (cols[col].contains(val)) return false;
                cols[col].add(val);

                int idx = (row / 3) * 3 + col / 3;
                if (box[idx].contains(val)) return false;
                box[idx].add(val);
            }
        }
        return true;
    }

    public boolean isValidSudokuArray(char[][] board) {
        int n = 9;
        int[][] rows = new int[n][n];
        int[][] cols = new int[n][n];
        int[][] boxes = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == '.') continue;

                int pos = board[row][col] - '1';
                if (rows[row][pos] == '1') return false;
                rows[row][pos] = '1';

                if (cols[pos][col] == '1') return false;
                cols[pos][col] = '1';

                int idx = (row / 3) * 3 + col / 3;
                if (boxes[idx][pos] == 1) return false;
                boxes[idx][pos] = 1;
            }
        }
        return true;
    }

    public boolean isValidSudokuBitMasking(char[][] board) {
        int n = 9;
        int[] rows = new int[n];
        int[] cols = new int[n];
        int[] boxes = new int[n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (board[row][col] == '.') continue;

                int val = board[row][col] - '0';
                int pos = 1 << (val - 1);

                if ((rows[row] & pos) > 0) return false;
                rows[row] |= pos;

                if ((cols[col] & pos) > 0) return false;
                cols[col] |= pos;

                int idx = (row / 3) * 3 + col / 3;
                if ((boxes[idx] & pos) > 0) return false;
                boxes[idx] |= pos;
            }
        }
        return true;
    }
}
