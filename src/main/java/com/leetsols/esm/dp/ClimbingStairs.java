package com.leetsols.esm.dp;

public class ClimbingStairs {
    /*
     * - Brute force
     */
    public int climbStairsOne(int n) {
        return climbBruteStairs(0, n);
    }

    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climbMemoStairs(0, n, memo);
    }

    public int climbMemoStairs(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }

        if (memo[i] > 0) {
            return memo[i];
        }

        memo[i] = climbMemoStairs(i + 1, n, memo) + climbMemoStairs(i + 2, n, memo);
        return memo[i];
    }

    private int climbBruteStairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climbBruteStairs(i + 1, n) + climbBruteStairs(i + 2, n);
    }

    private int climbDynamicStairs(int n) {
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
