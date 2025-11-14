package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: String, Dynamic Programming, Backtracking
 * Number: 22 Generate Parentheses
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res, n, new StringBuilder(), 0, 0);
        return res;
    }

    /*
     * - For the valid solution "well-formed" parenthesis should be start open
     */
    public void backtracking(List<String> res, int n, StringBuilder curr, int leftCount, int rightCount) {
        if (curr.length() == 2 * n) {
            res.add(curr.toString());
            return;
        }
        /*
         * the number of open parenthesis
         */
        if (leftCount < n) {
            curr.append("(");
            backtracking(res, n, curr, leftCount + 1, rightCount);
            curr.deleteCharAt(curr.length() - 1);
        }

        if (leftCount > rightCount) {
            curr.append(")");
            backtracking(res, n, curr, leftCount, rightCount + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
