package com.leetsols.esm.hashing;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem type: Array, Hashtable
 * Number: 1426 Counting Elements
 */
public class CountingElements {
    public int countElements(int[] arr) {
        Set<Integer> hashSet = new HashSet<>();
        for (int x : arr) {
            hashSet.add(x);
        }
        int count = 0;
        for (int x : arr) {
            if (hashSet.contains(x + 1)) {
                count++;
            }
        }
        return count;
    }
}
