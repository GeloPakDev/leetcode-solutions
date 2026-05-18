package com.leetsols.esm.sorting;

public class RelativeSort {
    /*
     * Given array1 and array2
     * - elements of the array2 are distinct.
     * - ALL elements in the array2 are also in array1.
     *
     * Sort elements of an array1 such that relative ordering of items in them are
     * the same as in array2, elements that don't appear in the array2, should be
     * placed at the end of the array1 in the ascending order.
     *
     * Algorithm:
     * - Take the element at arr2.
     *
     * Example:
     * - Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6].
     * - For each element in the array2, check the corresponding one in the array1,
     *   iterate through the array:
     *  - If element are equals  -> increment insertionIdx pos and swap the elements
     *  - If they are not equals -> update the insertionIdx and continue going over an array
     * - After the iteration over the array2, check does the last sorted element
     *   position < length of the array1, if it is, sort the last part of the array.
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int lastSortedPos = 0;
        for (int k : arr2) {
            for (int j = 0; j < arr1.length; j++) {
                if (k == arr1[j]) {
                    swap(arr1, lastSortedPos, j);
                    lastSortedPos++;
                }
            }
        }
        return arr1;
    }

    public static void swap(int[] array, int start, int end) {
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }
}