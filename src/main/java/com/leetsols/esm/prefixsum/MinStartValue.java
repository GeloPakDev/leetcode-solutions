package com.leetsols.esm.prefixsum;

/*
 * Problem type: Array, Prefix Sum
 * Number: 1413 Minimum Value to Get Positive Step by Step Sum
 */
public class MinStartValue {
    public int minStartValue(int[] nums) {
        int res = Integer.MAX_VALUE;
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            if (prefixSum[i] < res) {
                res = Math.abs(prefixSum[i]);
            }
        }
        return res + 1;
    }
}
