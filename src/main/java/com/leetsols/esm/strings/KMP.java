package com.leetsols.esm.strings;

import java.util.ArrayList;
import java.util.List;

public class KMP {
    /*
     * Prefix Function:
     *
     * Naive Approach:
     * - Generate all possible prefixes.
     * - Generate all possible suffixes.
     * - Get union entries from both lists.
     * - Take the longest one
     *
     * Prefix-Longest-Matching array:
     * -
     * -
     */
    public static int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int overlapLen = 0;

        for (int i = 1; i < m; i++) {
            // Fall back to a shorter known overlap while characters don't match
            while (overlapLen > 0 && pattern.charAt(i) != pattern.charAt(overlapLen)) overlapLen = pi[overlapLen - 1];
            // Extend the length of the overlap if characters match
            if (pattern.charAt(i) == pattern.charAt(overlapLen)) overlapLen++;

            pi[i] = overlapLen;
        }
        return pi;
    }

    public List<Integer> search(String text, String pattern) {
        var matches = new ArrayList<Integer>();
        if (pattern.isEmpty()) return matches;

        int[] pi = computePrefixFunction(pattern);
        int n = text.length();
        int m = pattern.length();
        int ptrnCharsMatched = 0;

        for (int i = 0; i < n; i++) {
            /*
             * Mismatch: Fall-back using the prefix function instead of
             * resetting [j] to 0 and re-checking already-confirmed characters
             */
            while (ptrnCharsMatched > 0 && text.charAt(i) != pattern.charAt(ptrnCharsMatched))
                ptrnCharsMatched = pi[ptrnCharsMatched - 1];

            if (text.charAt(i) == pattern.charAt(ptrnCharsMatched)) ptrnCharsMatched++;

            if (ptrnCharsMatched == m) {
                matches.add(i - m + 1);
                // Keep Searching for overlapped matches.
                ptrnCharsMatched = pi[ptrnCharsMatched - 1];
            }
        }
        return matches;
    }
}
