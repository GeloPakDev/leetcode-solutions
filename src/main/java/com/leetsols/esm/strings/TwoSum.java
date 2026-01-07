package com.leetsols.esm.strings;

import java.util.HashMap;

/*
 * Problem type: Array, Hash Table
 * Number: 1 Two Sum
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int res = numbers[left] + numbers[right];
            if (res == target) {
                return new int[]{left + 1, right + 1};
            }
            if (res > target) {
                right--;
            } else {
                left--;
            }
        }
        return new int[2];
    }

    public int[] twoSumTwo(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int x = target - cur;
            if (map.containsKey(x)) {
                return new int[]{i, map.get(x)};
            }
            map.put(cur, i);
        }
        return null;
    }
}
