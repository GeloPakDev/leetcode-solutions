package com.leetsols.esm.prefixsum;

public class NumArray {

    private int[] nums;
    private int[] prefix;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.prefix = new int[nums.length];

        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
    }

    /*
     * Calculate the prefix sum array
     *
     * For the range the prefix sum:
     * - prefix[right] - prefix[left] + nums[left]
     */
    public int sumRange(int left, int right) {
        return prefix[right] - prefix[left] + nums[left];
    }
}
