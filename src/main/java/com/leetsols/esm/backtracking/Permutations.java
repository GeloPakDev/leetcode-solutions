package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Array, Backtracking
 * Number: 46 Permutations
 */
public class Permutations {
    /*
     * Permutation - contains all the elements of nums with NO DUPLICATES
     * In order to generate all permutations -> put each number in the first pos
     * Then for each number in the first position, pick all left numbers in the second pos and so on
     *
     * Path from root to any given node -> curr
     * When we return, we are removing last node in the path
     *
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new ArrayList<>(), ans, nums);
        return ans;
    }

    private void backtrack(List<Integer> curr, List<List<Integer>> ans, int[] nums) {
        /*
         * - On the base case, add curr to the answer
         * - When adding to the answer -> create a copy of curr, because curr is only the reference
         */
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        /*
         * - Check if a number is already in curr
         * - The Leaves are the base cases and answers to the problem
         * - When we add element to curr, we make another call to backtrack -> moving to a child
         * - In the end curr will have the same length as nums -> valid permutation
         * - When we return, we are removing the last node in the path
         */
        for (int num : nums) {
            if (!curr.contains(num)) {
                curr.add(num);
                backtrack(curr, ans, nums);
                curr.removeLast();
            }
        }
    }
}
