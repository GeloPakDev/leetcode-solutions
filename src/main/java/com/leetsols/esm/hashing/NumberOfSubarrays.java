package com.leetsols.esm.hashing;

import java.util.HashMap;

/*
 * Problem type: Array, Hashtable, Math, Sliding Window, Prefix Sum
 * Number: 1248 Counting Number of Nice Subarrays
 */
public class NumberOfSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        var map = new HashMap<Integer, Integer>();
        map.put(0, 1);

        int ans = 0;
        /*
         * Track the count of odd numbers
         */
        int curr = 0;

        for (int num : nums) {
            curr += num % 2;
            ans += map.getOrDefault(curr - k, 0);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        return ans;
    }
}
