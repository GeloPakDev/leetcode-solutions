package com.leetsols.esm.binarysearch;

public class SplitArrayLargestSum {
    /*
     * Algorithm:
     * - Find the minimum largest subarray sum with [k] subarrays.
     * - Instead looking for the value directly, we can apply search on the answer space
     *   and check whether this particular value could be the largest subarray sum with
     *   [k] subarrays.
     * - Given an array of integers and a value [x], determine the minimum number of
     *   subarrays the array needs to be divided into such that no subarray sum is greater
     *   than [x].
     */
    public int splitArray(int[] nums, int k) {
        int sum = 0;
        int maxElement = Integer.MIN_VALUE;
        for (int element : nums) {
            sum += element;
            maxElement = Math.max(maxElement, element);
        }

        int left = maxElement;
        int right = sum;
        int minLargestSplitSum = 0;

        while (left <= right) {
            int maxSumAllowed = left + (right - left) / 2;

            if (minSubarrayRequired(nums, maxSumAllowed) <= k) {
                right = maxSumAllowed - 1;
                minLargestSplitSum = maxSumAllowed;
            } else {
                left = maxSumAllowed + 1;
            }
        }
        return minLargestSplitSum;
    }

    private int minSubarrayRequired(int[] nums, int maxSumAllowed) {
        int currSum = 0;
        int splitRequired = 0;

        for (int element : nums) {
            if (currSum + element <= maxSumAllowed) {
                currSum += element;
            } else {
                currSum = element;
                splitRequired++;
            }
        }
        return splitRequired;
    }
}