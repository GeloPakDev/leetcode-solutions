package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num)) counter.put(num, 0);
            counter.put(num, counter.get(num) + 1);
        }

        var linkedList = new LinkedList<Integer>();
        backtrackDup(linkedList, nums.length, counter, ans);
        return ans;
    }

    private void backtrackDup(LinkedList<Integer> comb, Integer N, HashMap<Integer, Integer> counter, List<List<Integer>> results) {
        if (comb.size() == N) {
            results.add(new ArrayList<>(comb));
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            var num = entry.getKey();
            var count = entry.getValue();
            if (count == 0) continue;
            // Add this number into current combination
            comb.addLast(num);
            counter.put(num, count -1);

            // continue the exploration
            backtrackDup(comb, N, counter, results);

            // revert the choice for the next exploration.
            comb.removeLast();
            counter.put(num, count);
        }
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
