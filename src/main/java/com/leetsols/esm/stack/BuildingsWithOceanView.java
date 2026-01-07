package com.leetsols.esm.stack;

import java.util.Stack;

/*
 * Problem type: Array, Stack, Monotonic Stack
 * Number: 1762. Building With an Ocean View
 */
public class BuildingsWithOceanView {
    /*
     * [4,2,3,1]
     * - Iterate from right -> left
     * - Identify the largest number along the list
     */
    public static int[] findBuildings(int[] heights) {
        int largest = Integer.MIN_VALUE;
        var stack = new Stack<Integer>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > largest) {
                largest = heights[i];
                stack.push(i);
            }
        }
        int[] res = new int[stack.size()];
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
}