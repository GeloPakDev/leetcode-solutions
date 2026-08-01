package com.leetsols.esm.strings;

public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] pi = computePrefix(s);

        int longestOverlap = pi[n - 1];
        int potentialPeriod = n - longestOverlap;

        return longestOverlap > 0 && n % potentialPeriod == 0;
    }

    public int[] computePrefix(String s) {
        int n = s.length();
        int[] pi = new int[n];

        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = pi[j - 1];
            if (s.charAt(i) == s.charAt(j)) j++;
            pi[i] = j;
        }

        return pi;
    }
}
