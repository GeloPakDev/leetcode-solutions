package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Two Pointers, Greedy
 * Number: 11. Container With Most Water
 */
public class ContainerWithMostWater {
    /*
     * Goal:
     * - Find two lines that together with the x-axis form a container,
     *   such that the container contains the most water.
     *   Return the maximum amount of water a container can store.
     * - Find out 2 vertical lines such that
     * - What forms a container that contains most of the water?
     * - Container with the largest area?
     * - Formula for the largest area S = width * height
     * - [1,8,6,2,5,4,8,3,7]
     * - S = Math.max(arr[left], arr[right]) * (right - left)
     * - What is the decision-making behind moving right or left pointers?
     * - Always move the pointer that points at the current lower line of two
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }
}
