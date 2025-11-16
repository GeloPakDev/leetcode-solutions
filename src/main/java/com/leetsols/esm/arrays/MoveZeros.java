package com.leetsols.esm.arrays;

public class MoveZeros {
    /*
     * First approach for solving the problem
     */
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 1;
        int length = nums.length - 1;
        while (left <= length && right <= length) {
            if (nums[left] == 0 && nums[right] == 0) {
                right++;
            } else if (nums[left] == 0 && nums[right] != 0) {
                swap(left, right, nums);
                left++;
                right = left;
            } else if (nums[left] != 0 && nums[right] == 0) {
                right++;
                left++;
            } else {
                right++;
                left++;
            }
        }
    }

    public void swap(int left, int right, int[] nums) {
        while (left < right) {
            int num = nums[left];
            nums[left] = nums[right];
            nums[right] = num;
            left++;
            right--;
        }
    }

    public void moveZerosTwo(int[] nums) {
        int writer = 0;
        for (int reader = 0; reader < nums.length; reader++) {
            if (nums[reader] != 0) {
                nums[writer] = nums[reader];
                writer++;
            }
        }

        while (writer < nums.length) {
            nums[writer] = 0;
            writer++;
        }
    }
}
