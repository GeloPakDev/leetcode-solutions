package com.leetsols.esm.prefixsum;

/*
 * Problem type: Array, Prefix Sum
 * Number: 2270 Number of Ways to Split Array
 */
public class WaysToSplitArray {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        /*
         * Create the Prefix-Sum array
         */
        long[] prefixSum = new long[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            long firstPart = prefixSum[i];
            /*
             * The right section begins at index i + 1 and ends at the final index n - 1. This means it has a sum of prefix[n - 1] - prefix[i]
             */
            long secondPart = prefixSum[n - 1] - prefixSum[i];
            if (firstPart >= secondPart) {
                res++;
            }
        }
        return res;
    }
}
