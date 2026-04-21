package com.leetsols.esm.stack;

import java.util.ArrayDeque;

public class LargestRectangleInHistogram {
    /*
     * Naive approach:
     * - Area of the rectangle dependent on the lowest height of the bar
     *   between 2 indices.
     * - Consider all pairs of indices
     * - Time complexity: O(n^2)
     * - Space complexity: O(1)
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                int width = j - i + 1;
                maxArea = Math.max(maxArea, minHeight * width);
            }
        }
        return maxArea;
    }

    /*
     * What is the maximum width of the rectangle we can have that completely covers the
     * bar at bar[i], or how much to the left or right I can move from bar[i] so the height
     * doesn't decrease.
     *
     * - Right Limit - nearest index to the right from bar[i] that is lower.
     * - Left  Limit - nearest index to the left  from bar[i] that is lower.
     *
     * Algorithm:
     * - Keep track of the bars till it increases (to encounter the right limit)
     * - Keep discarding the elements when the height start decreasing, also calculate the
     *   largest area by: (right limit - left limit) * current pop top element from the stack
     * - When the lower element is found (the one lower than the top) pop top element
     *   to calculate its LEFT and RIGHT for it which will provide us max area, where
     *   the RIGHT limit is the lower element found and the LEFT limit is next top element in the stack
     * - Calculation of the [maxArea] starts when the next element (or the found right limit)
     *   starts decreasing.
     * - When there are elements left in the stack after traversing through the array
     *   , the right limit for the top element in the stack will be length of the array
     */
    public int largestRectangleArea2(int[] heights) {
        var stack = new ArrayDeque<Integer>();
        stack.push(-1);

        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = n - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }
        return maxArea;
    }
}
