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

    private void backtrack(List<Integer> curr, int i, List<List<Integer>> ans, int[] nums) {
        /*
         * - Run out the numbers to use
         */
        if (i > nums.length) {
            return;
        }

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
}