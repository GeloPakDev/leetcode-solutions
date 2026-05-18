package com.leetsols.esm.sorting;

import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        // Partition array around the random pivot
        int pivotIdx = randomPartition(nums, left, right);

        // Check if the pivot is the k-th element
        if (k == pivotIdx) {
            return nums[k];
        } else if (k < pivotIdx) {
            // Target is in the left part
            return quickSelect(nums, left, pivotIdx - 1, k);
        } else {
            // Target is in the right part
            return quickSelect(nums, pivotIdx + 1, right, k);
        }
    }

    public int randomPartition(int[] nums, int left, int right) {
        //Pick the random pivot between the left and right
        int pivotIdx = left + random.nextInt(right - left + 1);
        swap(nums, pivotIdx, right); // Move the pivot to the end
        return partition(nums, left, right);
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
