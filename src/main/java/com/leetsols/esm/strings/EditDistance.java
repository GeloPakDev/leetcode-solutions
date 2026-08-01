package com.leetsols.esm.strings;

public class EditDistance {
    /*
     * Levenshtein algorithm
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        int len1 = word1.length();
        int len2 = word2.length();

        // It holds the Levenshtein distance between s1[0...i-1] & s2[0...j-1]
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Transforming an empty s1 into s2 requires [i] insertions
        for (int i = 0; i <= len2; i++) dp[0][i] = i;

        // Transforming an empty s1 into s2 requires [i] deletions
        for (int i = 0; i < len1; i++) dp[i][0] = i;

        // Fil the dp table
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // If there is a match, no new operation is needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    /*
                     * If they don't match, find the min cost among
                     * - Deletion (dp[i - 1][j] + 1)
                     * - Insertion (dp[i][j - 1] + 1)
                     * - Substitution (dp[i - 1][j - 1] + 1)
                     */
                    int minOp = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(minOp, dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
