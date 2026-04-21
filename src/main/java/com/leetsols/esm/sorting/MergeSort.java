package com.leetsols.esm.sorting;

import java.util.Arrays;

public class MergeSort {
    public int[] merge_sort(int[] nums) {
        if (nums.length <= 1) return nums;

        int pivot = nums.length / 2;

        int[] leftList = merge_sort(Arrays.copyOfRange(nums, 0, pivot));
        int[] rightList = merge_sort(Arrays.copyOfRange(nums, pivot, nums.length));
        return merge(leftList, rightList);
    }

    public int[] merge(int[] leftList, int[] rightList) {
        int[] res = new int[leftList.length + rightList.length];
        int leftPointer = 0, rightPointer = 0, resPointer = 0;

        while (leftPointer < leftList.length && rightPointer < rightList.length) {
            if (leftList[leftPointer] < rightList[rightPointer]) {
                res[resPointer++] = leftList[leftPointer++];
            } else {
                res[resPointer++] = rightList[rightPointer++];
            }
        }

        while (leftPointer < leftList.length) res[resPointer++] = leftList[leftPointer++];
        while (rightPointer < rightList.length) res[resPointer++] = rightList[rightPointer++];

        return res;
    }
}
