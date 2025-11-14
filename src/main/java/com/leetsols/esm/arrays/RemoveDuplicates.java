package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Two Pointers
 * Number: 26 Remove Duplicates from Sorted Array
 *         27 Remove Element
 */
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static int removeElement(int[] nums, int val) {
        int end = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {

            if (i == end) {
                break;
            }

            if (nums[end] == val) {
                end--;
            }

            if (nums[i] != val) {
                i++;
            }

            if (nums[i] == val) {
                i++;
                nums[i] = nums[val];
            }
        }
        return end;
    }
}
