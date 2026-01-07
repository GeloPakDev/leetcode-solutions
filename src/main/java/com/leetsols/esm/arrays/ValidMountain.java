package com.leetsols.esm.arrays;

/*
 * Problem type: Array
 * Number: 941. Valid Mountain Array
 */
public class ValidMountain {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        int i = 0;
        while (i + 1 < n && arr[i] < arr[i + 1]) i++;
        if (i == 0 || i == n - 1) return false;
        while (i + 1 < n && arr[i + 1] < arr[i]) i++;
        return i == n - 1;
    }
}
