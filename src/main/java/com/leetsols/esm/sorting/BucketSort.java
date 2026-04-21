package com.leetsols.esm.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void sort(double[] array) {
        int n = array.length;
        if (n == 0) return;

        // 1. Create [n] empty buckets
        List<Double>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) buckets[i] = new ArrayList<>();

        /*
         * 2. Scatter: Put the elements into buckets
         * - Mapping values to [0, n - 1]
         */
        for (double val : array) {
            int bucketIndex = (int) (val * n);
            buckets[bucketIndex].add(val);
        }

        /*
         * 3. Sort each bucket
         * 4. gather back to the array.
         */
        int index = 0;
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
            for (double val : buckets[i]) {
                array[index++] = val;
            }
        }
    }
}
