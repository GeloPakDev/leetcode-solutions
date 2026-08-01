package com.leetsols.esm.strings;

public class LongestPalindromicSubsequence {
    static int[][] dp;

    void initializeDPTable(int n) {
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) dp[i][j] = -1;
        }
    }

    public int longestPalindromeSubseq(String s) {
        return LPS(s.toCharArray(), 0, s.length() - 1);
    }

    /*
     * Recursive approach:
     * - If the single character is considered, it's always a palindrome of len 1.
     * - If the [first] and [last] chars are different length of the LPS is the maximum
     *   taken of either excluding the [first] character or the [last] character.
     * - If there are only 2 characters and both are the same, len is 2.
     * - If the first and last character are the same: Add 2 to the length of the LPS
     *   by excluding the first and last characters.
     */
    public int LPS(char[] s, int low, int high) {
        if (dp[low][high] != -1) return dp[low][high];

        int ans = 0;
        // input of length 1
        if (low == high) ans = 1;
            // String of length 2
        else if ((high - low) == 1) {
            // Both chars are equal
            if (s[low] == s[high]) ans = 2;
                // Otherwise
            else ans = 1;
        } else if (s[low] == s[high]) ans = 2 + LPS(s, low + 1, high - 1);
        else ans = Math.max(LPS(s, low, high - 1), LPS(s, low + 1, high));

        dp[low][high] = ans;
        return dp[low][high];
    }
}
