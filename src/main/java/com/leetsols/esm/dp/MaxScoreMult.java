package com.leetsols.esm.dp;

public class MaxScoreMult {
    /*
     * State:
     * - [i] how many operations have done so far, or the current level
     *   multipliers[i] is the current multiplier to be used.
     * - If we know how many elements we have picked from the left side
     *   and we know how many elements we have picked in total [i], then
     *   the right side -> [i - right]
     * - idx of the rightmost element is [n - 1 - (i - left)]
     *
     * Recurrence Relation:
     * - Left:
     *  - mult * nums[left]
     *  - next operation -> (i + 1, left + 1), [i] gets incremented at every operation
     *    as it indicates how many operations we have done,
     *  - mult * nums[left] + dp(i + 1, left + 1)
     * - Right:
     *  - mult * nums[right]
     *  - next operation -> (i + 1, left)
     *  - mult * nums[right] + dp(i + 1, left)
     * - Score is needed to maximized:
     *  - dp(i, left) = max(mult * nums[left] + dp(i + 1, left + 1),
     *                      mult * nums[right] + dp(i + 1, left))
     */
    private int[][] memo;
    private int[] nums, multipliers;
    private int n, m;

    public int maximumScore(int[] nums, int[] multipliers) {
        n = nums.length;
        m = multipliers.length;

        int[][] dp = new int[m + 1][m + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int left = i; left >= 0; left--) {
                int mult = multipliers[i];
                int right = n - 1 - (i - 1);
                dp[i][left] = Math.max(
                        mult * nums[left] + dp[i + 1][left + 1],
                        mult * nums[right] + dp[i + 1][left]);
            }
        }
        return dp[0][0];
    }

    private int dp(int i, int left) {
        if (i == m) return 0;

        int mult = multipliers[i];
        int right = n - 1 - (i - left);

        if (memo[i][left] == 0) {
            memo[i][left] = Math.max(
                    mult * nums[left] + dp(i + 1, left + 1),
                    mult * nums[right] + dp(i + 1, left));
        }
        return memo[i][left];
    }
}
