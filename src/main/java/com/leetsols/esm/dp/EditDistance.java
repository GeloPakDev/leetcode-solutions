package com.leetsols.esm.dp;

public class EditDistance {
    Integer[][] memo;

    /*
     * Algorithm:
     * - Edit Distance is a string metric, it quantifies how different 2 strings
     *   are, measured by the minimum number of operations required to transform
     *   one string into the other.
     * - Levenshtein Distance -> finds edit distances by allowing 3 types of
     *   transformation: addition, deletion, replacements
     * - Lower the edit distance the more similar they are
     */
    public int minDistance(String word1, String word2) {
        memo = new Integer[word1.length() + 1][word2.length() + 1];
        return minDistanceDP(word1, word2, word1.length(), word2.length());
    }

    /*
     * Approach: Recursion
     * - If 2 strings are the same, edit distance is 0
     * - if (word1[wordIdx1] == word2[wordIdx2]) -> characters match, move to the next one
     * - if (word1[wordIdx1] != word2[wordIdx2]) ->
     *  - delete, replace, add character in [word1].
     *  - Replace: rec(word1, word2, word1Idx - 1, word2Idx - 1) + 1
     *  - Insert:  rec(word1, word2, word1Idx,     word2Idx - 1) + 1
     *  - Delete:  rec(word1, word2, word1Idx - 1, word2Idx    ) + 1
     * - Minimum of all above operations is edit distance
     * - Base Case:
     *  - word1 is empty -> there could be 0 or more characters left in the word2
     *  - word2 is empty -> there could be 0 or more characters left in the word1
     */
    int minRecurDistance(String word1, String word2, int wordIdx1, int wordIdx2) {
        if (wordIdx1 == 0) return wordIdx2;
        if (wordIdx2 == 0) return wordIdx1;

        if (word1.charAt(wordIdx1 - 1) == word2.charAt(wordIdx2 - 1)) {
            return minRecurDistance(word1, word2, wordIdx1 - 1, wordIdx2 - 1);
        } else {
            int insert = minRecurDistance(word1, word2, wordIdx1, wordIdx2 - 1);
            int delete = minRecurDistance(word1, word2, wordIdx1 - 1, wordIdx2);
            int replace = minRecurDistance(word1, word2, wordIdx1 - 1, wordIdx2 - 1);
            return Math.min(insert, Math.min(delete, replace) + 1);
        }
    }

    int minDistanceDP(String word1, String word2, int word1Idx, int word2Idx) {
        if (word1Idx == 0) return word2Idx;
        if (word2Idx == 0) return word1Idx;

        if (memo[word1Idx][word2Idx] != null) return memo[word1Idx][word2Idx];

        int minDistance = 0;

        if (word1.charAt(word1Idx - 1) == word2.charAt(word2Idx - 1)) {
            minDistance = minDistanceDP(word1, word2, word1Idx - 1, word2Idx - 1);
        } else {
            int insert = minDistanceDP(word1, word2, word1Idx, word2Idx - 1);
            int delete = minDistanceDP(word1, word2, word1Idx - 1, word2Idx);
            int replace = minDistanceDP(word1, word2, word1Idx - 1, word2Idx - 1);
            minDistance = Math.min(insert, Math.min(delete, replace)) + 1;
        }
        memo[word1Idx][word2Idx] = minDistance;
        return minDistance;
    }
}
