package com.leetsols.esm.arrays;

public class MaximumSubarray {
    /*
     * Find subarray with larget sum
     * - When the negative number is worth keeping in the subarray?
     *  - Any subarray whose sum is positive is worth keeping.
     *  - Whenever the sum becomes negative, entire array is not worth keeping.
     *
     */
    public static int maxSubArray(int[] nums) {
        int curr = nums[0];
        int maxSubarray = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            curr = Math.max(num, curr + num);
            maxSubarray = Math.max(curr, maxSubarray);
        }
        return maxSubarray;
    }

    /*
     * Optimal subarray is the one that uses the elements
     * - only from the left side
     * - only from the right side
     * - combination of the elements from the left and right side
     *
     * Answer should be the largest of:
     * - Max subarray contained only from the left side
     * - Max subarray contained only from the right side
     * - Max subarray contained that can use the elements from both sides
     *
     * Since we want elements from both sides, we must also use the
     * middle element. Now we can also take from the left and right, but
     * every element must be connected to the middle
     */

    private int[] numsArray;

    public int maximumSubarray(int[] nums) {
        numsArray = nums;
        return findBestSubArray(0, numsArray.length - 1);
    }


    public int findBestSubArray(int left, int right) {
        if (left > right) return Integer.MIN_VALUE;

        int mid = Math.floorDiv(left + right, 2);
        int curr = 0;
        int bestLeftSum = 0;
        int bestRightSum = 0;

        /*
         * Iterate from the middle to the beginning
         */
        for (int i = mid - 1; i >= left; i--) {
            curr += numsArray[i];
            bestLeftSum = Math.max(bestLeftSum, curr);
        }

        /*
         * Reset curr and iterate from the middle to the end
         */
        curr = 0;
        for (int i = mid + 1; i <= right; i++) {
            curr += numsArray[i];
            bestRightSum = Math.max(bestRightSum, curr);
        }
        /*
         * bestCombinedSum uses middle element and the best possible
         * sum from each half
         */
        int bestCombinedSum = numsArray[mid] + bestLeftSum + bestRightSum;

        /*
         * Find the best subarray possible from both halves
         */
        int leftHalf = findBestSubArray(left, mid - 1);
        int rightHalf = findBestSubArray(mid + 1, right);
        return Math.max(bestCombinedSum, Math.max(leftHalf, rightHalf));
    }
}