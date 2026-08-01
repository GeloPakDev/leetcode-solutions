package com.leetsols.esm.strings;

public class RunLengthEncoding {
    public static String compress(String input) {
        if (input == null || input.isEmpty()) return "";

        var stringBuilder = new StringBuilder();
        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            char nextChar = input.charAt(i);
            if (nextChar == currentChar) count++;
            else {
                stringBuilder.append(count).append(currentChar);
                currentChar = nextChar;
                count = 1;
            }
        }

        stringBuilder.append(count).append(currentChar);
        return stringBuilder.toString();
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";

        return compress(countAndSay(n - 1));
    }
}