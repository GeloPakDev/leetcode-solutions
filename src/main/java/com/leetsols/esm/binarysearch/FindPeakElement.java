package com.leetsols.esm.binarysearch;

/*
 * Problem type: Array, Binary Search
 * Number: 162. Find Peak Element
 */
public class FindPeakElement {
    /*
     * Binary Search
     * - Peak - Strictly greater than its neighbors
     * - Any adjacent elements in the array are not going to be equal
     * - Don't need the top peak element, can return any
     * - How do we move to the right or left sides of the array? What is condition for it?
     *  - Monotonically increasing or decreasing
     *   The core principle is that:
     *   - If you are on a "rising slope" (the next element is larger), there must be a peak to your right.
     *   - If you are on a "falling slope" (the next element is smaller), a peak must exist at your current position or to your left
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid + 1] > nums[mid]) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
