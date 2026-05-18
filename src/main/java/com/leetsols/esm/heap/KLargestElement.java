package com.leetsols.esm.heap;

import java.util.PriorityQueue;
import java.util.Random;

public class KLargestElement {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.peek();
    }

    /*
     * Find k(th) largest element from the array without sorting
     * Use of QuickSelect algorithm
     */
    public int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    /*
     *
     */
    public int quickSelect(int[] nums, int left, int right, int k) {
        // If the partition contains only one element, we've found our target
        if (left == right) return nums[left];

        // Pick a random pivot to avoid O(n^2) worst-case on sorted arrays.
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        // Partition the array around the pivot
        pivotIndex = partition(nums, left, right, pivotIndex);

        // Decide which side to discard
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            // Target is in the left part
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            // Target is in the right part
            return quickSelect(nums, pivotIndex + 1, right, k);
        }

    }

    public int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];

        // Move pivot to the end temporarily
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        // Move all elements smaller than pivotValue to the left
        for (int i = left; i < right; i++) {
            if (nums[i] > pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // Move pivot to its final sorted place
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
