package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Sliding Window
 * Number: 643 Maximum Average Subarray I
 */
public class MaxAvgSubarray {
    public double findMaxAverage(int[] nums, int k) {
        double curr = 0;
        double ans;
        for (int i = 0; i < k; i++) {
            curr += nums[i];
        }
        curr = curr / k;
        ans = curr;

        for (int i = k; i < nums.length; i++) {
            curr = curr * k;
            curr += nums[i] - nums[i - k];
            curr = curr / k;
            ans = Math.max(ans, curr);
        }
        return ans;
    }
}
