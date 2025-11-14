package com.leetsols.esm.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class StackProblems {
    public static boolean isValid(String s) {
        var matching = new HashMap<Character, Character>();
        matching.put('(', ')');
        matching.put('[', ']');
        matching.put('{', '}');

        var stack = new Stack<Character>();
        for (char ch : s.toCharArray()) {
            if (matching.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char takenLetter = stack.pop();
                if (matching.get(takenLetter) != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static String removeDuplicates(String s) {
        var stack = new Stack<Character>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        var res = new StringBuilder();
        for (char ch : stack) {
            res.append(ch);
        }
        return res.toString();
    }

    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(s));
    }

    private String build(String s) {
        var stack = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch != '#') {
                stack.append(ch);
            } else if (!stack.isEmpty()) {
                stack.deleteCharAt(stack.length() - 1);
            }
        }
        return stack.toString();
    }

    public String simplifyPath(String path) {
        var list = new ArrayList<String>();
        for (var dir : path.split("/")) {
            if (dir.isEmpty() || dir.equals(".")) {
                continue;
            }

            if (dir.equals("..")) {
                if (!list.isEmpty()) {
                    list.removeLast();
                }
            } else {
                list.add(dir);
            }
        }
        return "/" + String.join("/", list);
    }

    public static String makeGood(String s) {
        var stack = new Stack<Character>();

        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(stack.lastElement() - ch) == 32)
                stack.pop();
            else
                stack.add(ch);
        }

        var res = new StringBuilder();
        for (char ch : stack)
            res.append(ch);

        return res.toString();
    }
}
