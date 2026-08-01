package com.leetsols.esm.dp;

public class DecodeWays {
    public int numDecodings(String s) {
        Integer[] memo = new Integer[s.length()];
        return helper(s, 0, memo);
    }

    /*
     * Base Case: Leaf node in the state-space tree has been reached, we can increase our answer
     * Edge Case: Leading '0' has been reached.
     *
     */
    public int helper(String num, int idx, Integer[] memo) {
        // Leaf node in the state-space tree has been reached.
        if (idx == num.length()) return 1;
        // Leading 0 has been reached
        if (num.charAt(idx) == '0') return 0;

        if (memo[idx] != null) return memo[idx];

        int ways = helper(num, idx + 1, memo);

        if (idx + 1 < num.length() && Integer.parseInt(num.substring(idx, idx + 2)) <= 26) {
            ways += helper(num, idx + 2, memo);
        }
        return memo[idx] = ways;
    }
}
