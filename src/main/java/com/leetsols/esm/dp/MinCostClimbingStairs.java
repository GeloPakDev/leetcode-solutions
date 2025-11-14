package com.leetsols.esm.dp;

import java.util.HashMap;
import java.util.Map;

public class MinCostClimbingStairs {
    Map<Integer, Integer> cache = new HashMap<>();

    public int minCostClimbing(int[] cost) {
        return dp(cost.length, cost);
    }

    private int dp(int i, int[] cost) {
        if (i <= 1) {
            return 0;
        }

        if (cache.containsKey(i)) {
            return cache.get(i);
        }

        cache.put(i, Math.min(dp(i - 1, cost) + cost[i - 1], dp(i - 2, cost) + cost[i - 2]));
        return cache.get(i);
    }

    public int minCostClimbingIter(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}
