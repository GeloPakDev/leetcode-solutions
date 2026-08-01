package com.leetsols.esm.strings;

public class RotateString {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        char[] sChars = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            sChars = rotateOne(sChars);
            if (new String(sChars).equals(goal)) return true;
        }
        return false;
    }

    private char[] rotateOne(char[] array) {
        char firstChar = array[0];
        System.arraycopy(array, 1, array, 0, array.length - 1);
        array[array.length - 1] = firstChar;
        return array;
    }

    /*
     * A clever way to exploit this is by concatenating [s] with itself. Why? Because this
     * effectively creates a string that contains all possible rotations of [s] within it.
     * For example, if s = "abcde", then s + s = "abcdeabcde". Notice how every possible
     * rotation of s appears somewhere in this concatenated string.
     */
    public boolean rotateStringRot(String s, String goal) {
        if (s.length() != goal.length()) return false;
        var text = s + s;
        return text.contains(goal);
    }

    public boolean rotateStringRotPrefix(String s, String goal) {
        if (s.length() != goal.length()) return false;

        var text = s + s;

        return kmpSearch(text, goal);
    }

    public boolean kmpSearch(String text, String pattern) {
        int[] lps = computePrefixFunction(pattern);
        int textIdx = 0, patternIdx = 0;
        int textLen = text.length(), patternLen = pattern.length();

        // Loop through the text to find out the pattern
        while (textIdx < textLen) {
            // If characters mismatch, move both indices
            if (text.charAt(textIdx) == pattern.charAt(patternIdx)) {
                textIdx++;
                patternIdx++;
                // If we have matched the entire pattern, return true
                if (patternIdx == patternLen) return true;
            } else if (patternIdx > 0) {
                // If there is mismatch after some matches, use LPS array to skip unnecessary comparisons
                patternIdx = lps[patternIdx - 1];
            } else {
                // If no matches, move to the next char
                textIdx++;
            }
        }
        return false;
    }

    public static int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int overlapLen = 0;

        for (int i = 1; i < m; i++) {
            while (overlapLen > 0 && pattern.charAt(i) != pattern.charAt(overlapLen))
                overlapLen = pi[overlapLen - 1];
            if (pattern.charAt(i) == pattern.charAt(overlapLen)) overlapLen++;
            pi[i] = overlapLen;
        }
        return pi;
    }
}
