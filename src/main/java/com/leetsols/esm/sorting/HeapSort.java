package com.leetsols.esm.sorting;

public class HeapSort {
    /*
     * Algorithm:
     * - Build the Max heap first
     * - Start from the last non-leaf node (n/2) - 1
     *  - Left child at position: 2 * i + 1
     *  - Right child at position: 2 * i + 2
     *  - For a node to be a non-leaf, it must have at least one child.
     *    2 * i + 1 < n -> i = (n - 1) / 2
     *
     * - Extract the elements one by one from the heap
     *  - Move the current maximum to the end of the array swap(nums, 0, i);
     *  - Call heapify for the reduced heap                heapify(nums, i, 0);
     */
    public int[] heapSort(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int n = nums.length;

        // Build Max Heap
        for (int i = n / 2 - 1; i >= 0; i--) heapify(nums, n, i);

        for (int i = n - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, i, 0);
        }
        return nums;
    }

    /*
     * Heapify:
     * - Check if the LEFT child exists, and it is greater than the root
     * - Check if the RIGHT child exists, and it is greater than the root
     * - if (largest == i) -> parent is already >= both children
     * - if (largest != i) -> code swaps arr[i] with the largest
     */
    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[left] > arr[largest]) largest = left;
            if (right < n && arr[right] > arr[largest]) largest = right;
            if (largest != i) {
                swap(arr, i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
