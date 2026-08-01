package com.leetsols.esm.strings;

import java.util.ArrayDeque;

/*
 * Problem type: Two Pointers, String
 * Number: 344 Reverse String
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            var temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public String reverseString(String input) {
        StringBuilder res = new StringBuilder();
        for (int i = input.length() - 1; i > 0; i--) {
            res.append(input.charAt(i));
        }
        return String.valueOf(res);
    }

    public String reverseStringStack(String input) {
        var stack = new ArrayDeque<Character>();
        for (int i = input.length() - 1; i > 0; i--) {
            stack.push(input.charAt(i));
        }

        var res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return String.valueOf(res);
    }

    public void reverseStringRecursive(char[] charArray, int start, int end) {
        if (start > end) return;

        swap(charArray, start, end);
        reverseStringRecursive(charArray, start + 1, end - 1);
    }

    public void swap(char[] charArray, int start, int end) {
        char temp = charArray[start];
        charArray[start] = charArray[end];
        charArray[end] = temp;
    }
}
