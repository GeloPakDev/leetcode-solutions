package com.leetsols.esm.arrays;

/*
 * Problem type: Array
 * Number: 485 Max Consecutive Ones
 */
public class MaxConsecutive {
    /*
     * Maximum number of consecutive 1's in the array.
     * - Setup 2 pointers which will work as a Sliding Window
     * - Increase the window if the nums[i] = 1;
     * - Shrink the window if the nums[i] = 0;
     * - Update the variable res Math.max
     * - curr < right - left
     *   [1,1,1,0]
     *   - number of elements in the subarray more than the sum of this subarray
     *   - shrink it right - - let
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int curr = 0;
        int ans = 0;
        for (int right = 0; right < nums.length; right++) {
            curr += nums[right];
            while (curr < right - left) {
                curr -= nums[left];
                left++;
            }
            ans = Math.max(ans, left - right + 1);
        }
        return ans;
    }
}
