package com.leetsols.esm.stack;

import java.util.Stack;

public class RemovingStars {

    /*
     * Approach:
     * - Keep track of the most recently seen non-star character while
     *   iterating from the beginning to the end of the string.
     * -
     */
    public static String removeStarsStack(String s) {
        var stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') stack.push(s.charAt(i));
            else stack.pop();
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) res.append(stack.pop());
        return res.reverse().toString();
    }

    public static String removeStars(String s) {
        var res = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (!res.isEmpty()) res.deleteCharAt(res.length() - 1);
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}
