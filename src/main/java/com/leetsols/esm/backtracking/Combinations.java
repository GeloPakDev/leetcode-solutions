package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Backtracking
 * Number: 77 Combinations
 */
public class Combinations {
    /*
     * - Return all combinations of k numbers in the range of [1, n]
     * - Return all subsets of the range [1, n] of length k
     * - Duplicates are not allowed for the combinations
     * - Deduce that all subsets of length k will be needed
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new ArrayList<>(), ans, 1, n, k);
        return ans;
    }

    private void backtrack(List<Integer> curr, List<List<Integer>> res, int i, int n, int k) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int num = i; num <= n; num++) {
            curr.add(num);
            backtrack(curr, res, num + 1, n, k);
            curr.remove(curr.size() - 1);
        }
    }
}
