package com.leetsols.esm.sorting;

public class SelectionSort {
    /*
     * it partitions the list into the 2 sections:
     * - sorted
     * - unsorted
     *
     * At each step the algorithm, it finds the smallest one from the unsorted section
     * and swaps and swaps it with the leftmost element unsorted element, step by step
     * increasing the size of the sorted section.
     */
    public void selectionSort(int[] array) {
        int n = array.length;
        //Move the boundary of the unsorted array
        for (int i = 0; i < n - 1; i++) {
            //Find the minimum index in the unsorted array
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) minIdx = j;
            }
            //Swap the found minIdx with left most unsorted index
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }
}
