package com.leetsols.esm.arrays;

import java.util.HashMap;

/*
 * Problem type: Array, Binary Search, Sliding Window, Prefix Sum
 * Number: 713 Subarray Product Less Than K
 *
 * Problem type: Array, Hash Table, Prefix Sum
 * Number: 560 Subarray Sum Equals K
 */
public class SubarrayK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int ans = 0;
        int left = 0;
        int curr = 1;

        for (int right = 0; right < nums.length; right++) {
            curr *= nums[right];
            while (curr >= k) {
                curr /= nums[left];
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }

    public static int subarraySum(int[] nums, int k) {
        var map = new HashMap<Integer, Integer>();
        map.put(0, 1);

        int ans = 0;
        int curr = 0;

        for (int num : nums) {
            curr += num;
            ans += map.getOrDefault(curr - k, 0);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        return ans;
    }
}
