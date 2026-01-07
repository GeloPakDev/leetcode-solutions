package com.leetsols.esm.strings;

/*
 * Problem type: Two Pointers, String, Dynamic Programming
 * Number: 5 Longest Palindromic String
 */
public class LongestPalindromicString {
    /*
     * If the [word[i] == word[j]], you can check that the largest substring is a palindrome
     * by comparing the edge characters equals or not
     *
     * State transition:
     * dp[i][j] = (S[i] == S[j]) && (dp[i + 1][j + 1])
     * It is true if NEW edge characters are equal and substring in between them is a palindrome
     *
     * All substrings of length 1 are palindromes.
     * For odd substrings  j - i = 2 -> Any single character is a palindrome
     * For even substrings j - i = 0 -> Any 2 identical chars are palindrome
     *
     * dp[i][j] - boolean states if substring with inclusive bounds [i,j] is a palindrome
     * Initialize the dp[i][i] = true for the substrings of length 1
     *                dp[i][i + 1] = (s[i] == s[i + 1]) for the substrings of length 2
     *
     * In order to populate the table, we iterate over all (i, j) pairs, starting with
     * pairs with diff 2, 3 and so on.
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] ans = new int[]{0, 0};

        /*
         * Set all SINGLE characters as valid palindromes
         */
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        /*
         * Set all even strings as valid even palindromes
         * If some valid even palindrome found, save the answer
         */
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                ans[0] = i;
                ans[1] = i + 1;
            }
        }

        /*
         * Why do we start checking from diff = 2, because the palindrome with length of 2
         * has been found in the second loop already
         *
         * Outer loop: Starts checking each substring starting from length 3
         * Inner loop: Moves the window(substring) to check is it a palindrome or not
         *
         * int j = i + diff -> set the end of current window
         * s[i] == s[j]     -> Check the outer characters matching
         * dp[i + 1][j - 1] -> Check does the inner side is palindrome or not
         */
        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }
}
