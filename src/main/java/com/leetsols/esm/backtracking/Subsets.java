package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Array, Backtracking, Bit Manipulation
 * Number: 78 Subsets
 */
public class Subsets {
    /*
     * - To avoid duplicates to find every subset -> pass [i] to indicate where we should start
     * - We will loop over the [i, n) instead of [0, n)
     * - Pass index of the number which is adding + 1
     *
     * - integer is used as very common method of avoiding duplicates
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new ArrayList<>(), 0, ans, nums);
        return ans;
    }

    /*
     * Algorithm:
     * - If the current combination is done, add to the final output
     * - Otherwise, iterate from the index [i] to entire sequence length
     *  - Add nums[i] into the current combination
     *  - Proceed to add more integers into the combination backtrack(i + 1, curr)
     *  - Backtrack by removing the nums[i] from curr.
     *
     * Time Complexity:
     * - O(N * 2 ^ (N))
     *
     */
    private void backtrack(List<Integer> curr, int i, List<List<Integer>> ans, int[] nums) {
        /*
         * - Run out the numbers to use
         */
        if (i > nums.length) return;

        ans.add(new ArrayList<>(curr));
        /*
         * - To avoid duplicates, iterate over the elements after the current one
         */
        for (int j = i; j < nums.length; j++) {
            curr.add(nums[j]);
            backtrack(curr, j + 1, ans, nums);
            curr.removeLast();
        }
    }

    /*
     * Bitmask
     * - 1 << n gives us the total count of subsets. Each number from 0 to (1 << n) - 1,
     *   when written in binary, is a bitmask where each bit position tells us whether to
     *   include the corresponding element from the input array.
     * -
     */
    public List<List<Integer>> subsetsB(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int total = 1 << n; // total subsets -> 

        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) subset.add(nums[j]);
            }
            result.add(subset);
        }
        return result;
    }
}