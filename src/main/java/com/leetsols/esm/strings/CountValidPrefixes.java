package com.leetsols.esm.strings;

public class CountValidPrefixes {
    public int countValidPrefixes(String s) {
        int zeros = 0;
        int ones = 0;
        int validCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') zeros++;
            else ones++;

            if (Math.abs(zeros - ones) <= 1) validCount++;
        }

        return validCount;
    }
}
