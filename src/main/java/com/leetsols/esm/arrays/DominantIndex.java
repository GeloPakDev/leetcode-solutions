package com.leetsols.esm.arrays;

import java.util.Arrays;

/*
 * Problem type: Array, Sorting
 * Number: 747. Largest Number At Least Twice of Others
 */
public class DominantIndex {
    /*
     * Largest element is at least twice as every other number in the array.
     */
    public static int dominantIndex(int[] nums) {
        /*
         * Find the largest number and its index
         */
        int index = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }
        }

        Arrays.sort(nums);
        int preLast = nums[nums.length - 2];
        if (preLast == 0) {
            return index;
        }

        if (max / preLast < 2) {
            return -1;
        } else {
            return index;
        }
    }
}
