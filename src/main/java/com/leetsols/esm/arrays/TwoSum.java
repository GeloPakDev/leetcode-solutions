package com.leetsols.esm.arrays;

import java.util.LinkedHashMap;

/*
 * Problem type: Array, Math, Simulation
 * Number: 54. Spiral Matrix
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        var map = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
