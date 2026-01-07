package com.leetsols.esm.arrays;

import java.util.HashMap;

/*
 * Problem type: Array, Hashtable, Sorting
 * Number: 217 Contains Duplicate, 219 Contains Duplicate
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

    /*
     * Problem paraphrasing:
     * - Find out the duplicate elements in the array such that their
     *   distance between each other is less than or equals K
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        var maps = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!maps.containsKey(nums[i])) {
                maps.putIfAbsent(nums[i], i);
            } else {
                if (Math.abs(maps.get(nums[i]) - i) <= k) return true;
                else maps.put(nums[i], i);
            }
        }
        return false;
    }
}
