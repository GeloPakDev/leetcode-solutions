package com.leetsols.esm.prefixsum;

/*
 * Problem type: Array, Prefix Sum
 * Number: 1480 Running Sum of 1d Array
 */
public class RunningSum {
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] array = new int[n];

        array[0] = nums[0];
        for (int i = 1; i < n; i++) {
            array[i] = array[i - 1] + nums[i];
        }
        return array;
    }
}
