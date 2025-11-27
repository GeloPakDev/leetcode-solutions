package com.leetsols.esm.arrays;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Array, Dynamic Programming
 * Number: 118. Pascal's Triangle
 */
public class PascalTriangle {
    /*
     * Dynamic Programming
     * - Ones on the right and left side of each row
     * - All numbers are the sum of the two numbers above
     *
     * Base case:
     * - Values at the beginning and end of the row is always 1
     *
     * Recurrence relation:
     * - Each number is the sum of the 2 numbers directly above it
     * - triangle[row][col] = triangle[row - 1][col - 1]
     *                      + triangle[row - 1][col]
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> curr = new ArrayList<>();
            List<Integer> prev = res.get(rowNum - 1);
            curr.add(1);
            for (int col = 1; col < prev.size(); col++) {
                curr.add(prev.get(col - 1) + prev.get(col));
            }
            curr.add(1);
            res.add(curr);
        }
        return res;
    }

    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.get(0).add(1);

        /*
         * - Outer loop for rows and iteration in depth
         * - curr -> new row on rowIndex level
         * - prev -> upper level row
         * - curr.add(1) -> before and after the loop as indicates in the Pascal Triangle
         * - Inner loop forming values by summing two adjacent elements of the upper row.
         * - Add a new level to the triangle
         */
        for (int row = 1; row <= rowIndex; row++) {
            List<Integer> curr = new ArrayList<>();
            List<Integer> prev = res.get(row - 1);
            curr.add(1);
            for (int col = 1; col < prev.size(); col++) {
                curr.add(prev.get(col - 1) + prev.get(col));
            }
            curr.add(1);
            res.add(curr);
        }
        return res.get(rowIndex);
    }
}
