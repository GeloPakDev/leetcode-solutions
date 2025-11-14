package com.leetsols.esm.hashing;

import java.util.HashMap;

/*
 * Problem type: Array, Hashtable, Matrix, Simulation
 * Number: 2352 Equal Row and Column Pairs
 */
public class EqualRowAndColumns {
    public int equalPairs(int[][] grid) {
        /*
         * Go over the rows in matrix
         */
        var dicOne = new HashMap<String, Integer>();
        for (int[] row : grid) {
            /*
             * Covert an array to String for key in hashtable
             */
            String key = convertToKey(row);
            dicOne.put(key, dicOne.getOrDefault(key, 0) + 1);
        }

        /*
         * Go over the columns in matrix
         */
        var dicTwo = new HashMap<String, Integer>();
        for (int col = 0; col < grid[0].length; col++) {
            int[] currentCol = new int[grid.length];
            /*
             * Going down in the column
             */
            for (int row = 0; row < grid.length; row++) {
                currentCol[row] = grid[row][col];
            }

            String key = convertToKey(currentCol);
            dicTwo.put(key, dicTwo.getOrDefault(key, 0) + 1);
        }

        int ans = 0;
        for (String key : dicOne.keySet()) {
            /*
             * If key doesn't exist in the map answer will be 0
             */
            ans += dicOne.get(key) * dicTwo.getOrDefault(key, 0);
        }
        return ans;
    }

    public String convertToKey(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) {
            sb.append(j);
            sb.append(",");
        }
        return sb.toString();
    }
}
