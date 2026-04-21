package com.leetsols.esm.sorting;

public class CountingSort {
    /*
     * Algorithm:
     * - Auxiliary (Count array) uses indices to represent the values of the input
     *   data, in case of the big values [1001, 1002, 1003], we will need to create
     *   an array of the size 1003, even 0->1002 spaces would be 0.
     * - With Min and Max we would be able to understand the range of the values by
     *   max - min + 1
     * - An array might have negative values and indexes cannot be the negative
     *   values, by calculating the min value, value can be mapped to index as follows:
     *   [value - min].
     * -
     *
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) return new int[]{0};

        //Find the range (min and max)
        int max = nums[0];
        int min = nums[0];
        for (int i : nums) {
            if (i > max) max = i;
            if (i < min) min = i;
        }

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[nums.length];

        /*
         * Frequency Mapping
         */
        for (int i : nums) count[i - min]++;

        /*
         * Prefix Sum
         */
        for (int i = 1; i < range; i++) count[i] += count[i - 1];

        /*
         * Stable Replacement.
         * - Iterate backwards through the original array.
         * -
         */
        for (int i = nums.length - 1; i >= 0; i--) {
            int value = nums[i];
            int position = count[value - min] - 1;
            output[position] = value;
            count[value - min]--;
        }
        return output;
    }
}
