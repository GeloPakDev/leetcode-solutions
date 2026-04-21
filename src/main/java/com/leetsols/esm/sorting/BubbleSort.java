package com.leetsols.esm.sorting;

public class BubbleSort {
    /*
     * - Repeatedly swapping adjacent elements that are out of order
     * - This comparison-based makes multiple passes over the array
     *   , ensuring each pass pushes the largest unsorted element to
     *   its correct position.
     *
     * - If no swaps made in that pass, it means the elements are correctly
     *   placed and the array is sorted. Boolean flag swapped is helping to make it,
     *   if we make any swaps during the iteration, flag is marked as false, else
     *   the flag stays the true, we can terminate it early.
     */
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    /*
     * Last Swap Index:
     * - We are looking for the index where the whole right part of the array
     *   is considered as sorted, i.e. from index [lastSwapIndex -> end].
     * - As this part of the array is already sorted, we don't need to touch it.
     */
    public static void bubbleSortTwo(int[] nums) {
        int n = nums.length;
        int lastSwapIndex = n - 1;

        while (lastSwapIndex > 0) {
            int currentSwapIndex = 0;
            for (int i = 0; i < lastSwapIndex; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;

                    currentSwapIndex = i;
                }
            }
            lastSwapIndex = currentSwapIndex;
        }
    }
}
