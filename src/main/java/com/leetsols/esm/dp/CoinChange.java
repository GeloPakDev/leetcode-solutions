package com.leetsols.esm.dp;

import java.util.Arrays;

public class CoinChange {
    private Integer[] memo;

    /*
     * - Base cases:
     *  - If the amount can't be made up by any combination return -1
     *  - If the amount is 0 -> return 0
     *  - If the counter hit amount, update max variable
     *
     * - Recurrence relation:
     *  - For each of the coins[i], calculate the
     *
     *
     *  - Return the fewest number of coins to make up the amount,
     */
    public int coinChange(int[] coins, int amount) {
        return coinRecursiveCheck(coins, amount);
    }

    public int coinChangeMemo(int[] coins, int amount) {
        memo = new Integer[amount + 1];
        return recursionHelper(coins, amount);
    }

    private int recursionHelper(int[] coins, int remain) {
        if (remain < 0) return -1;
        if (remain == 0) return 0;

        if (memo[remain] != null) return memo[remain];

        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = recursionHelper(coins, remain - coin);
            if (count == -1) continue;
            minCount = Math.min(minCount, count + 1);
        }

        memo[remain] = minCount == Integer.MAX_VALUE ? -1 : minCount;
        return memo[remain];
    }

    private int coinRecursiveCheck(int[] coins, int remain) {
        if (remain < 0) {
            return -1;
        }

        if (remain == 0) {
            return 0;
        }

        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = coinRecursiveCheck(coins, remain - coin);
            if (count == -1) continue;
            minCount = Math.min(minCount, count + 1);
        }

        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private int dynamicCoinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == (amount + 1) ? -1 : dp[amount];
    }
}
