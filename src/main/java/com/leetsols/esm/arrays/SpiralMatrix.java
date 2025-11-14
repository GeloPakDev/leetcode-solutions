package com.leetsols.esm.arrays;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Array, Math, Simulation
 * Number: 54. Spiral Matrix
 */
public class SpiralMatrix {
    /*
     * [row, col]
     * - Directions
     *  - move right  -> [row, col + 1]
     *  - move down   -> [row + 1, col]
     *  - move left   -> [row, col - 1]
     *  - move upward -> [row - 1, col]
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = cols - 1;
        int down = rows - 1;

        while (res.size() < rows * cols) {
            // traverse from left -> right
            for (int col = left; col <= right; col++) {
                res.add(matrix[up][col]);
            }
            // Traverse downwards
            for (int row = up + 1; row <= down; row++) {
                res.add(matrix[row][right]);
            }
            // Make sure you are on the different row
            if (up != down) {
                // from right -> left
                for (int col = right - 1; col >= left; col--) {
                    res.add(matrix[down][col]);
                }
            }
            // Make sure you are on the different col
            if (left != right) {
                for (int row = down - 1; row > up; row--) {
                    res.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}
