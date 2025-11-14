package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Binary Search, Sliding Window, Prefix Sum
 * Number: 209. Minimum Size Subarray Sum
 */
public class MinSubarrayLength {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int curr = 0;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            curr += nums[right];
            while (curr >= target) {
                answer = Math.min(answer, right - left + 1);
                curr -= nums[left];
                left++;
            }
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}
