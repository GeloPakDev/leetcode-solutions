package com.leetsols.esm.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * Problem type: Array, Hashtable, Sorting, Counting
 * Number: 2248 Intersection of Multiple Arrays
 */
public class IntersectionOfArrays {
    /*
     * - Go over an arrays and find out intersected elements or union between these arrays
     * [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
     * - Based on the Hint since all the numbers in arrays are distinct the count of
     * these numbers should be the same within the number of arrays in it
     * - Go over each array and insert it TreeMap which maintains insertion order
     */
    public static List<Integer> intersection(int[][] nums) {
        var map = new HashMap<Integer, Integer>();
        for (int[] num : nums) {
            for (int i : num) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }

        int n = nums.length;
        var list = new ArrayList<Integer>();
        for (int key : map.keySet()) {
            if (map.get(key) == n) {
                list.add(key);
            }
        }
        Collections.sort(list);
        return list;
    }
}
