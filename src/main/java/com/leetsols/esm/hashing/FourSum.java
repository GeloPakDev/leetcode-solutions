package com.leetsols.esm.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum {
    /*
     * Algorithm:
     * - apply the kSum approach where we will have k-2 nested loops to
     *   enumerate all combinations of k-2 values
     * - Recursion, when k == 2 apply 2sum approach
     * - kSum:
     *  - Did we check all numbers to choose from?
     *  - Is the smallest remaining number greater than target/k? -> any chosen number will be too large
     *  - Is the largest remaining number smaller than target/k? -> any chosen number will be too small
     *
     *  - If k == 2 -> call twoSum
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> list = new ArrayList<>();

        if (start == nums.length) return list;

        long average = target / k;

        if (nums[start] > average || average > nums[nums.length - 1]) return list;

        if (k == 2) return twoSum(nums, target, start);

        for (int i = start; i < nums.length; i++) {
            // If the current value is the same as previous -> duplicate
            if (i == start || nums[i - 1] != nums[i]) {
                /*
                 * Recursively call kSum with
                 * - start -> i + 1
                 * - k     -> k - 1
                 */
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    list.add(new ArrayList<>(List.of(nums[i])));
                    list.getLast().addAll(subset);
                }
            }
        }
        return list;
    }

    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start;
        int hi = nums.length - 1;
        while (lo < hi) {
            int currSum = nums[lo] + nums[hi];
            /*
             * nums[lo] == nums[lo - 1] -> remove duplicates
             * nums[hi] == nums[hi - 1] -> remove duplicates
             */
            if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                ++lo;
            } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }
        return res;
    }

    public List<List<Integer>> twoSumHashSet(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Long> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (res.isEmpty() || res.getLast().get(1) != nums[i]) {
                if (set.contains(target - nums[i])) {
                    res.add(Arrays.asList((int) target - nums[i], nums[i]));
                }
            }
            set.add((long) nums[i]);
        }
        return res;
    }

    /*
     * Approach:
     * - a + b = -(c + d)
     * - Count sums of elements a + b from the first 2 arrays
     * - Find complementary sum -(c + d) from the last 2 arrays
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        var map = new HashMap<Integer, Integer>();
        for (int a : nums1) {
            for (int b : nums2) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }

        for (int c : nums3) {
            for (int d : nums4) {
                count += map.getOrDefault(-(c + d), 0);
            }
        }
        return count;
    }
}