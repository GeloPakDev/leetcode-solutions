package com.leetsols.esm.dp;

public class IntegerBreak {
    int[] memo;

    public int integerBreak(int n) {
        if (n <= 3) return n - 1;

        int[] dp = new int[n + 1];

        for (int i = 1; i <= 3; i++) dp[i] = i;

        for (int num = 4; num <= n; num++) {
            int ans = num;
            for (int i = 2; i < num; i++) {
                ans = Math.max(ans, i * dp[num - i]);
            }
            dp[num] = ans;
        }
        return dp[n];
    }

    /*
     * Algorithm:
     * - if (n <= 3) return num
     * - ans = num -> case of not splitting number at all
     * - Iterate [i] from 2 until [num]
     *  - Update [ans] with max(ans, i * dp(num - i))
     * - return ans
     */
    public int dp(int num) {
        if (num <= 3) return num;
        if (memo[num] != 0) return memo[num];

        int ans = num;
        for (int i = 2; i < num; i++) {
            ans = Math.max(ans, i * dp(num - i));
        }
        memo[num] = ans;
        return ans;
    }
}
