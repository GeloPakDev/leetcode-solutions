package com.leetsols.esm.sorting;

public class InsertionSort {
    /*
     * - Continually inserting each element from unsorted subarray into a sorted subarray
     * - It virtually splits the array into sorted and unsorted subarray, initially the
     *   sorted subarray consists of the single element, and the rest from unsorted sub
     *   array.
     * - Iterate over the unsorted subarray elements, remove them from the unsorted
     *   subarray and place them at correct position in the sorted subarray. We repeat
     *   this process until no element remains in the sorted subarray.
     *
     * Algorithm:
     * - Consider the first element of the array as sorted and the rest as
     *   unsorted subarray.
     * - Iteratively take elements from the unsorted part of the sub-array
     *   and move to the sorted one.
     * - For each unsorted element, compare current element with elements before it.
     * - If the current element is greater than its preceding element, leave it there
     *   as it is already at the desired position. If not keep comparing it with
     *   elements before it until:
     *  - A smaller or equal element is found, insert the current after it
     *  - All comparisons are made and no smaller element is found. Insert the current element
     *    at the beginning of the array.
     * - Repeat the above process for every element of the unsorted subarray until the array
     *   is sorted.
     */
    public void insertionSort(int[] array) {
        int n = array.length;
        /*
         * - Consider the first element as sorted, and other part of the subarray
         *   as unsorted.
         * - while (j >= 0 && array[j] > key)
         *  - [j] indicates the position of sorted elements.
         *  - iterate over all sorted elements to find out the position for the new
         *    unsorted element.
         *  - array[j] > key -> element in the sorted part of the array larger than
         *    the current one.
         *  - array[j + 1] = array[j] -> place a larger element on the position of
         *    the current element to free up the space for the insertion position
         *  - array[j + 1] = key -> put the unsorted element in its correct position
         */
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
