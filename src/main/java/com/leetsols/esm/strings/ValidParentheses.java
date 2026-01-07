package com.leetsols.esm.strings;

import java.util.Stack;

/*
 * Problem type: String, Dynamic Programming, Stack, Greedy
 * Number: 20. ValidParentheses, 678. Valid Parenthesis String
 */
public class ValidParentheses {
    /*
     * * - can represent '(', ')', and empty string
     * * - leads us to multiple branching possibilities
     *
     * To explore all branching scenarios, use recursion and counting
     * - if s[i] == '(' -> opening++;
     * - if s[i] == ')' && opening > 0 -> opening--;
     * - if s[i] == '*' explore all scenarios
     *  - opening++;
     *  - if opening > 0 -> opening --;
     *  - add empty string and move forward
     * Recursive approach will result in TLE
     *
     * 2 Stacks
     * - Keeps track of indices of '('
     * - Keeps track of indices of '*'
     *
     * - When ')' encountered
     *  - if openBrackets is not empty -> remove the matching open bracket
     *  - if asterisks is not empty -> use asterisk to balance closing bracket
     *  - if both stacks are empty -> return false
     * - In the end of iteration
     *  - Check if any of the remaining open brackets and asterisks can balance each other
     *  - while openBrackets && asterisks are not empty
     *   - if top of the openBracket > asterisks -> return false; It means that open comes after the closing one,
     *     so it cannot be balanced
     *   - else -> pop from both stacks;
     *  - Check that both stack are empty
     */
    public boolean checkValidString(String s) {
        Stack<Integer> openBrackets = new Stack<>();
        Stack<Integer> asterisks = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') openBrackets.push(i);
            else if (ch == '*') asterisks.push(i);
                // Current character is closing bracket
            else {
                /*
                 * - Open brackets are available, use them to balance the closing bracket
                 * - Asterisks are available, use them to balance the closing bracket
                 * - If no brackets are available return false
                 */
                if (!openBrackets.isEmpty()) openBrackets.pop();
                else if (!asterisks.isEmpty()) asterisks.pop();
                else return false;
            }
        }

        /*
         * - Check if the remaining brackets can balance the other
         * - If open bracket placed after the *, it cannot be balanced
         */
        while (!openBrackets.isEmpty() && !asterisks.isEmpty()) {
            if (openBrackets.pop() > asterisks.pop()) return false;
        }

        return openBrackets.isEmpty();
    }

    /*
     * Check the open and closed brackets simultaneously, ensuring that there are
     * no surplus or deficit brackets are left
     *
     * Algorithm:
     * - From the left
     *  - Count occurrences of open brackets and asterisks
     *  - If ')' encountered, decrement count of open ones
     * - From the right
     *  - Count occurrences of closed brackets and asterisks
     *  - If '(' encountered, decrement count of closed ones
     * - If either the count of open or closed brackets falls below 0 it means that sequence is invalid
     * - Otherwise the sequence is valid
     */
    public boolean checkValidStringTwo(String s) {
        int open = 0;
        int close = 0;
        int n = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            char start = s.charAt(i);
            char last = s.charAt(n - i);
            if (start == '(' || start == '*') open++;
            else open--;

            if (last == ')' || last == '*') close++;
            else close--;

            if (open < 0 || close < 0) return false;
        }
        return true;
    }
}