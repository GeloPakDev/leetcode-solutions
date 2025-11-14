package com.leetsols.esm.arrays;

import java.util.HashMap;

/*
 * Problem type: Array, Hashtable, Sorting
 * Number: 217 Contains Duplicate
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        var maps = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!maps.containsKey(num)) {
                maps.putIfAbsent(num, 0);
            } else {
                return true;
            }
        }
        return false;
    }
}
