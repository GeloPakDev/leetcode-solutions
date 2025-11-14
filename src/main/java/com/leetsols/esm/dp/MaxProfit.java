package com.leetsols.esm.dp;

import java.util.Arrays;

public class MaxProfit {
    int n;
    int[][][] memo;
    int[] prices;

    public int maxProfit(int k, int[] prices) {
        n = prices.length;
        memo = new int[n][2][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        this.prices = prices;
        return dp(0, 0, k);
    }

    private int dp(int i, int holding, int remain) {
        if (i == n || remain == 0) {
            return 0;
        }

        if (memo[i][holding][remain] != -1) {
            return memo[i][holding][remain];
        }

        int ans = dp(i + 1, holding, remain);
        if (holding == 1) {
            ans = Math.max(ans, prices[i] + dp(i + 1, 0, remain - 1));
        } else {
            ans = Math.max(ans, -prices[i] + dp(i + 1, 1, remain));
        }

        memo[i][holding][remain] = ans;
        return ans;
    }
    /*
     * Data at each state:
     *  - Day
     *  - Holding stock or not
     *  - Allowed transaction
     *
     * Decisions made at each state
     *  - Not holding the stock -> buy or sell
     *  - Buy -> -prices[i] + dp(i + 1, true, remain)
     *            prices[i] -> buy the stock
     *            i + 1     -> move to the next day
     *            true      -> holding stock
     *            remain    -> haven't completed transaction yet
     *
     *  - Holding the stock -> sell today or skip
     *  - Sell -> prices[i] + dp(i + 1, false, remain - 1)
     *            prices[i] -> gain money
     *            i + 1     -> move to the next day
     *            false     -> not holding
     *            remain - 1-> used up one transaction
     *
     *  - Skip -> dp(i + 1, holding, remain)
     *
     * Recurrence relation:
     *  - dp(i, holding, remain) = max(skip, buy, sell)
     *  skip = dp(i + 1, holding, remain)
     *  buy  = -prices[i] + dp(i + 1, true, remain)
     *  sell = prices[i] + dp(i + 1, false, remain - 1)
     *
     * Base cases:
     *  - end of the input
     *  - k = 0 -> run out of transactions
     */

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] free = new int[n];
        int[] hold = new int[n];

        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], free[i - 1] - prices[i]);
            free[i] = Math.max(free[i - 1], hold[i - 1] + prices[i] - fee);
        }

        return free[n - 1];
    }

    /*
     * Go over an array to:
     *  - find out the smallest number
     *  - if the number is smaller than the current -> update
     *  - update the res by comparing the difference within the lowest number and current one and lowest
     */
    public int maxProfitOne(int[] prices) {
        int largestDifference = 0;
        int minSoFar = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < minSoFar) {
                minSoFar = price;
            } else {
                largestDifference = Math.max(minSoFar, price - minSoFar);
            }
        }
        return largestDifference;
    }

    /*
     * Largest profit
     *  - Sum of all positive differences prices
     *
     * How to track the positive differences in the graph?
     *  - lowest current point
     *  - highest current point
     *
     * Keep track of the closest valley(local minimum) we are coming from
     *  - Check if the node we are on lower than the highest peak we have seen so far
     *  - If it is, set the local min and max to be valley
     *  - If it is not(the node we are on higher than the highest peak, update the highest peak to the larger value)
     *  -
     *
     * When to add found differences to our overall total
     *  - It is done everytime we move to new valley
     *  - total += local max - local min
     */
    public int maxProfitTwo(int[] prices) {
        int total = 0;
        int valley = Integer.MAX_VALUE;
        int peak = valley;
        /*
         * Compare the current value to the value in our peak
         *  - If the value is smaller -> we found the end of our previous positive difference
         *  - If the value is larger -> moved on to the new peak
         */
        for (int price : prices) {
            if (price < peak) {
                total += peak - valley;

                valley = price;
                peak = valley;
            } else {
                peak = price;
            }
        }

        total += peak - valley;
        return total;
    }

    public int maxProfitThree(int[] prices) {
        int length = prices.length;

        if (length <= 1) return 0;

        int minLeft = prices[0];
        int maxRight = prices[length - 1];

        int[] profitLeft = new int[length];
        int[] profitRight = new int[length + 1];

        for (int i = 1; i < length; i++) {
            profitLeft[i] = Math.max(profitLeft[i - 1], prices[i] - minLeft);
            minLeft = Math.min(prices[i], minLeft);

            int r = length - 1 - i;
            profitRight[r] = Math.max(profitRight[r + 1], maxRight - prices[r]);
            maxRight = Math.max(maxRight, prices[r]);
        }

        int res = 0;
        for (int i = 0; i < length; i++) {
            res = Math.max(res, profitLeft[i] + profitRight[i + 1]);
        }
        return res;
    }

}