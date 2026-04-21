package com.leetsols.esm.stack;

import java.util.ArrayDeque;

/*
 * Problem type: Math, String, Stack
 * Number: 227. Basic Calculator
 */
public class BasicCalculator {
    /*
     * Algorithm:
     * - If the current operation is addition (+) or subtraction (-), then the
     *   expression is evaluated based on the precedence of the next operation.
     * Example:
     * - 4 + 3 - 5 -> 4 + 3 is evaluated first because the next operation has equal precedence
     *
     * - If the current operator is multiplication (*) or division (/), then the
     *   expression is evaluated irrespective of the next operation.
     * - 4 + 3 * 5 -> 4 + 3 is evaluated later because the next operation is *
     *
     *
     * Algorithm:
     * - If the current character is a digit, add it to the currentNumber
     * - If the current character is (+,-,*,/)
     *  - (+ or -) - evaluate later based on the next operation and store
     *               the currentNumber to be used later, push it to the stack.
     *               To properly evaluate the expression in the future, we
     *               assume that all operations in the stack is '+' and for
     *               the (-), currentNumber is (-currentNumber)
     *  - (* or /) - pop the value from the stack, evaluate the expression and
     *               put it back to the stack
     */
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        var length = s.length();

        var stack = new ArrayDeque<Integer>();
        var currentNumber = 0;
        var operation = '+';
        for (int i = 0; i < s.length(); i++) {
            var currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) currentNumber = (currentNumber * 10) + (currentChar - '0');

            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+') stack.push(currentNumber);
                else if (operation == '-') stack.push(-currentNumber);
                else if (operation == '*') stack.push(stack.pop() * currentNumber);
                else if (operation == '/') stack.push(stack.pop() / currentNumber);
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    /*
     * Algorithm:
     * - [lastNumber] is used to track the value of the last evaluated expression
     * - [currentNumber] is used to track the value of the current number
     *
     * 3 + 2 * 2
     */
    public int calculateTwo(String s) {
        if (s == null || s.isEmpty()) return 0;

        int length = s.length();
        int currentNumber = 0;
        int lastNumber = 0;
        int result = 0;

        char operation = '+';

        for (int i = 0; i < length; i++) {
            var currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) currentNumber = (currentNumber * 10) + (currentChar - '0');

            if (!Character.isDigit(currentChar) &&
                    !Character.isWhitespace(currentChar) ||
                    i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }
}
