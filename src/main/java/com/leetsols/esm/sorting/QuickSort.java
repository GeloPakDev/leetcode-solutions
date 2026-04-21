package com.leetsols.esm.sorting;

public class QuickSort {
    public void quickSort(int[] array) {
        int n = array.length;
        qSort(array, 0, n - 1);
    }

    public void qSort(int[] array, int low, int high) {
        if (low < high) {
            int p = partitioning(array, low, high);
            qSort(array, low, p - 1);
            qSort(array, p + 1, high);
        }
    }

    public int partitioning(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }
        int temp = array[i];
        array[i] = array[high];
        array[high] = temp;
        return i;
    }
}
