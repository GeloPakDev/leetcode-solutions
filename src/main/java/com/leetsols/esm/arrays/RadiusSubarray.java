package com.leetsols.esm.arrays;

import java.util.Arrays;

/*
 * Problem type: Array, SlidingWindow
 * Number: 238 Product of Array Except Self
 */
public class RadiusSubarray {
    public int[] getAverages(int[] nums, int k) {
        if (k == 0) {
            return nums;
        }

        int windowSize = 2 * k + 1;
        int n = nums.length;
        int[] averages = new int[n];
        Arrays.fill(averages, -1);

        if (windowSize > n) {
            return averages;
        }

        long windowSum = 0;
        for (int i = 0; i < windowSize; ++i) {
            windowSum += nums[i];
        }
        averages[k] = (int) (windowSum / windowSize);

        for (int i = windowSize; i < n; ++i) {
            windowSum = windowSum - nums[i - windowSize] + nums[i];
            averages[i - k] = (int) (windowSum / windowSize);
        }

        return averages;
    }
}
