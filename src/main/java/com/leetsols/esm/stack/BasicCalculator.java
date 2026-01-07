package com.leetsols.esm.stack;

import java.util.Stack;

/*
 * Problem type: Math, String, Stack
 * Number: 227. Basic Calculator
 */
public class BasicCalculator {
    /*
     * Algorithm:
     * - If the current operation is addition (+) or subtraction (-), then the
     *   expression is evaluated based on the precedence of the next operation.
     * - If the current operator is multiplication (*) or division (/), then the
     *   expression is evaluated irrespective of the next operation.
     */
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int len = s.length();

        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
