package com.leetsols.esm.arrays;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem type: Array, Bit Manipulation
 * Number: 136. Single Number
 */
public class SingleNumber {
    public static int singleNumber(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        var res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) res = entry.getKey();
        }
        return res;
    }
}