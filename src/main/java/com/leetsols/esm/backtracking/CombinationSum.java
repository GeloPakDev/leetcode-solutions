package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Backtracking, Array
 * Number: 39 Combination Sum
 */
public class CombinationSum {
    /*
     * - Combinations of candidates where chosen numbers sum to target
     * - The same number can be used multiple times
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(new ArrayList<>(), 0, 0, res, candidates, target);
        return res;
    }

    /*
     * - path -> represents the number in the current path
     * - start -> represents where in the input iteration should start from
     * - curr -> sum of all nimbers in the path
     */
    private void backtrack(List<Integer> path, int start, int curr, List<List<Integer>> ans, int[] candidates, int target) {
        if (curr == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            if (curr + num <= target) {
                path.add(num);
                backtrack(path, i, curr + num, ans, candidates, target);
                path.remove(path.size() - 1);
            }
        }
    }
}