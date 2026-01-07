package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Two Pointers, Sorting
 * Number: 905 Sort Array by Parity
 */
public class SortByArray {
    public int[] sortArrayByParity(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 > nums[j] % 2) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            /*
             * If either of the items in the right place increase counters
             */
            if (nums[i] % 2 == 0) i++;
            if (nums[j] % 2 == 1) j--;
        }
        return nums;
    }
}
