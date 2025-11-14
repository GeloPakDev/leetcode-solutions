package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Backtracking, Breadth-First Search
 * Number: 967 Numbers With Same Consecutive Differences
 */
public class ConsecutiveDiff {
    /*
     * - n -> length of each integer
     * - k -> difference between every 2 consecutive digits
     */
    public int[] numsSameConsecDiff(int n, int k) {
        /*
         * Edge case -> return the numbers of a single digit
         */
        if (n == 1) {
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }

        List<Integer> res = new ArrayList<>();
        for (int num = 1; num < 10; num++) {
            this.DFS(n - 1, num, k, res);
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private void DFS(int n, int num, int k, List<Integer> res) {
        /*
         * - Base case: No more remaining digits to complete the number
         */
        if (n == 0) {
            res.add(num);
            return;
        }
        List<Integer> curr = new ArrayList<>();

        /*
         * Algorithm
         * - Extract the last digit of num.
         * - Compute possible next digits
         *   - If k = 0, we only add once (avoiding duplicates).
         *   - curr.add(tailDigit + k);
         *   - curr.add(tailDigit - k);
         * - Ensure valid digit (must be in range 0–9).
         * - Form a new number: newNum = num * 10 + next.
         * - Recurse with one less digit remaining (n - 1).
         */
        int tailDigit = num % 10;
        curr.add(tailDigit + k);

        if (k != 0)
            curr.add(tailDigit - k);
        for (Integer next : curr) {
            if (0 <= next && next < 10) {
                int newNum = num * 10 + next;
                this.DFS(n - 1, newNum, k, res);
            }
        }
    }
}