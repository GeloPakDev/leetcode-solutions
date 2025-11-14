package com.leetsols.esm.backtracking;

import java.util.HashSet;

/*
 * Problem type: Backtracking
 * Number: 52 N-Queens 2
 */
public class NQueensTwo {
    int size;

    /*
     * General idea:
     * - Every queen in the separate row
     * - Two queens should in the separate column
     */
    public int totalNQueens(int n) {
        size = n;
        return backtracking(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private int backtracking(int row, HashSet<Integer> cols, HashSet<Integer> diagonals, HashSet<Integer> antiDiagonals) {
        /*
         * - Base case: passed over all rows
         */
        if (row == size) {
            return 1;
        }

        int solutions = 0;
        /*
         * continue -> change the sequence of execution back to the for loop iteration
         */
        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;

            if (cols.contains(col) || diagonals.contains(currAntiDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
                continue;
            }

            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);

            solutions += backtracking(row + 1, cols, diagonals, antiDiagonals);

            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
        }
        return solutions;
    }
}
