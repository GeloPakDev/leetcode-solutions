package com.leetsols.esm.dp;

import java.util.LinkedList;

public class RemoveKDigits {
    public String removeKDigits(String num, int k) {
        var stack = new LinkedList<Character>();

        for (char digit : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k -= 1;
            }
            stack.addLast(digit);
        }

        for (int i = 0; i < k; i++) stack.removeLast();

        var res = new StringBuilder();
        boolean leadingZero = true;
        for (char digit : stack) {
            if (leadingZero && digit == '0') continue;
            leadingZero = false;
            res.append(digit);
        }

        if (res.isEmpty()) return "0";
        return res.toString();
    }
}
