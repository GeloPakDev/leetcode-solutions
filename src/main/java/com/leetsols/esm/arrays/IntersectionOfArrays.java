package com.leetsols.esm.arrays;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem type: Array, Hash Table, Two Pointers, Binary Search, Sorting
 * Number: 349 Intersection of Two Arrays
 */
public class IntersectionOfArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) set.add(i);
        for (int j : nums2) set1.add(j);

        Set<Integer> out = new HashSet<>();
        for (int num : set) {
            if (set1.contains(num)) {
                out.add(num);
            }
        }
        return out.stream().mapToInt(Integer::intValue).toArray();
    }
}
