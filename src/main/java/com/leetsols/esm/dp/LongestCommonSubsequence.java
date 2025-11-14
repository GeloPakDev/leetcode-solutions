package com.leetsols.esm.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {
    /*
     * At each pair (i, j) there are 2 possibilities (recurrence relation)
     * - text1[i] == text2[j] -> Match is found, then move to the next dp(i, j) = 1 + dp(i + 1, j + 1)
     * - text1[i] != text2[j] -> Move to the next char in tx1 or tx2
     *  dp(i, j) = max(dp(i + 1), j), dp(i, j + 1));
     *
     * Base cases:
     * - i = text1.length
     * - j = text2.length
     * both strings are exhausted -> return 0
     */
    int m;
    int n;
    int[][] memo;
    String text1;
    String text2;

    public int longestCommonSubsequence(String text1, String text2) {
        m = text1.length();
        n = text2.length();
        this.text1 = text1;
        this.text2 = text2;
        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(0, 0);
    }

    private int dp(int i, int j) {
        /*
         * Both strings are exhausted -> return 0
         */
        if (i == m || j == n) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int ans = 0;
        if (text1.charAt(i) == text2.charAt(j)) {
            ans = 1 + dp(i + 1, j + 1);
        } else {
            ans = Math.max(dp(i + 1, j), dp(i, j + 1));
        }
        memo[i][j] = ans;
        return ans;
    }
}
