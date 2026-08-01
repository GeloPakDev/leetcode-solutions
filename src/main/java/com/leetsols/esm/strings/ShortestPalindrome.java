package com.leetsols.esm.strings;

public class ShortestPalindrome {
    /*
     * Approach:
     * - Finding the longest palindromic substring that starts from idx 0
     * - Once the length of the substring is known, we can create a shortest
     *   palindromic substring, by appending the reverse of the remaining
     *   part of the string to the original one to make [s] complete palindrome
     *
     * Brute Force:
     * - Identify which part of the string is already a palindrome.
     * - Check original string and see how much of it matches the end of its
     *   reversed version.
     *
     */
    public String shortestPalindrome(String s) {
        int len = s.length();
        String reversedString = new StringBuilder(s).reverse().toString();

        for (int i = 0; i < len; i++) {
            if (s.substring(0, len - i).equals(reversedString.substring(i))) {
                return reversedString.substring(0, i) + s;
            }
        }
        return "";
    }

    public String shortestPalindromeTwoPointer(String s) {
        int len = s.length();
        if (len == 0) return s;

        // Find the longest palindromic prefix
        int left = 0;
        for (int right = len - 1; right >= 0; right--) {
            if (s.charAt(right) == s.charAt(left)) left++;
        }

        // If the whole string is palindrome, return the orig string
        if (left == len) return s;

        // Extract the suffix that is not part of the palindromic prefix
        String nonPalindromeSuffix = s.substring(left);
        StringBuilder reverseSuffix = new StringBuilder(nonPalindromeSuffix).reverse();

        return reverseSuffix
                .append(shortestPalindromeTwoPointer(s.substring(0, left)))
                .append(nonPalindromeSuffix)
                .toString();
    }

    public String shortestPalindromeKMP(String s) {
        var reversed = new StringBuilder(s).reverse().toString();
        var combined = s + '#' + reversed;
        int[] prefixTable = buildPrefixTable(combined);

        int palindromeLength = prefixTable[combined.length() - 1];
        StringBuilder suffix = new StringBuilder(s.substring(palindromeLength)).reverse();
        return suffix.append(s).toString();
    }

    public String shortestPalindromeRollingHash(String s) {
        long hashBase = 29;
        long modValue = (long) 1e9 + 7;
        long forwardHash = 0, reverseHash = 0, powerValue = 1;
        int palindromeEdnIdx = -1;

        // Calculate rolling hashes and the longest palindromic prefix
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);

            // Update forward hash
            forwardHash = (forwardHash * hashBase + (currChar - 'a' + 1)) % modValue;

            // Update reverse hash
            reverseHash = (reverseHash + (currChar - 'a' + 1) * powerValue) % modValue;
            powerValue = (powerValue * hashBase) % modValue;

            // If forward and reverse match, update palindrome end idx
            if (forwardHash == reverseHash) palindromeEdnIdx = i;
        }

        String suffix = s.substring(palindromeEdnIdx + 1);
        StringBuilder reversedSuffix = new StringBuilder(suffix).reverse();
        return reversedSuffix.append(s).toString();
    }

    private int[] buildPrefixTable(String s) {
        int[] prefixTable = new int[s.length()];
        int len = 0;
        for (int i = 1; i < s.length(); i++) {
            while (len > 0 && s.charAt(i) != s.charAt(len)) {
                len = prefixTable[len - 1];
            }
            if (s.charAt(i) == s.charAt(len)) len++;
            prefixTable[i] = len;
        }
        return prefixTable;
    }
}
