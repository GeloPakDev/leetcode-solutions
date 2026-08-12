package com.leetsols.esm.stack;

import java.util.ArrayDeque;

public class ReversePolishNotation {
    /*
     * Reverse Polish Notation
     * - Operators follow their operands
     * - 3 4 + -> 3 + 4
     */
    public int evalRPN(String[] tokens) {
        var stack = new ArrayDeque<Integer>();
        for (String temp : tokens) {
            if (isOperator(temp)) {
                int right = stack.pop();
                int left = stack.pop();
                int res = switch (temp) {
                    case "+" -> left + right;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    default -> left / right;
                };
                stack.push(res);
            } else {
                stack.push(Integer.parseInt(temp)); // Safely parses "-11", "5", etc.
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.length() == 1 && "+-*/".contains(token);
    }
}
