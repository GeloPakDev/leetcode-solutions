package com.leetsols.esm.strings;

public class LongestHappyPrefix {
    /*
     * Algorithm: Prefix Function
     * - Happy Prefix -> It is non-empty prefix which is also a suffix
     */
    public String longestPrefix(String s) {
        int m = s.length();
        if (m <= 1) return "";

        int[] pi = new int[m];
        int k = 0;

        // Standard KMP Prefix Function
        for (int i = 1; i < m; i++) {
            while (k > 0 && s.charAt(i) != s.charAt(k)) k = pi[k - 1];

            if (s.charAt(i) == s.charAt(k)) k++;

            pi[i] = k;
        }
        // The last element tells us the length of the longest prefix-suffix for the WHOLE string
        int longestLen = pi[m - 1];

        // Since it's a prefix, it always starts at index 0
        return s.substring(0, longestLen);
    }
}
