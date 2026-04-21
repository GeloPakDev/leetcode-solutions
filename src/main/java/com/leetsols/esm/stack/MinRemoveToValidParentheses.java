package com.leetsols.esm.stack;

import java.util.ArrayDeque;
import java.util.HashSet;

public class MinRemoveToValidParentheses {
    /*
     * lee(t(c)o)de)
     *
     * Valid Parentheses Condition:
     * - Same number of "(" and ")"
     * - Not acceptable to have more ")" than "("
     *
     * Example of Invalid String:
     * L ( e ) e ( t ) c  ) o ) ( d ) e
     * 0 1 1 0 0 1 1 0 0 -1
     * - Balance goes negative, as there are more closing brackets before ")"
     *
     * L ( e ) e ( t ( ) c ( o ( d ) e
     * 0 1 1 0 0 1 1 2 1 1 2 2 3 3 2 2
     * - Balance is not 0 at the end, as there are more open brackets without corresponded "("
     *
     * Algorithm:
     * - Each time "(" is seen, add it to the stack
     * - Each time ")" is seen, remove it from the stack
     *  - If the stack is empty at that moment, add index of ")" for removal (prevent negative balance)
     * - Remove "(" that is left on the stack at the end (prevent non-zero balance)
     */
    public String minRemoveToMakeValid(String s) {
        var indexesToRemove = new HashSet<Integer>();
        var stack = new ArrayDeque<Integer>();
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        var res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) res.append(s.charAt(i));
        }
        return res.toString();
    }
}
