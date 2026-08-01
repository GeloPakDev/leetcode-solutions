package com.leetsols.esm.arrays;

import java.util.TreeMap;

public class RankTransform {
    /*
     * Algorithm:
     * - Traverse and create buckets for the corresponding elements.
     * - Sort the hashmap based on the key
     * - For each, assign the rank based on the position in the hashmap
     * - Iterate over the original array, take the value based on the
     *   key for corresponding element in the array and put it into the
     *   resulting list
     */
    public int[] arrayRankTransform(int[] arr) {
        var map = new TreeMap<Integer, Integer>();
        for (int j : arr) map.putIfAbsent(j, 0);

        var res = new int[arr.length];
        int i = 1;
        for (var entry : map.entrySet()) {
            entry.setValue(i);
            i++;
        }

        for (int j = 0; j < arr.length; j++) res[j] = map.get(arr[j]);
        return res;
    }
}
