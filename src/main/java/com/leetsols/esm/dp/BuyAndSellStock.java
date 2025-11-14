package com.leetsols.esm.dp;

public class BuyAndSellStock {
    /*
     * - Buy the stock -> dp[i] = -prices[i] + dp[i - 1]
     * - Sell a  stock -> dp[i] = prices[i] + dp[i - 1]
     * - Do nothing    -> dp[i] = dp[i - 1]
     *
     *
     * State
     * - on the day[i] to sell the stock you should hold it
     * - on the day[i] there are 2 situations
     *  - hold the stock
     *  - not hold the stock
     *
     * dp[i] -> 2 states
     *  - max profit when free of stock
     *  - max profit when hold of stock
     *
     * Buy
     *  - max profit without holding the stock free[i - 1] - prices[i]
     *   hold[i] = free[i - 1] - prices[i]
     *
     * Sell
     *  - hold -> not hold
     *  - max profit of hold[i - 1] + profit from selling prices[i] - fee
     *   free[i] = hold[i - 1] + prices[i] - fee
     *
     * Hold
     *  - free[i] = free[i - 1]
     *  - hold[i] = hold[i - 1]
     *
     * free[i] = max(free[i - 1], hold[i - 1] + prices[i] - fee)
     * hold[i] = max(hold[i - 1], free[i - 1] - prices[i])
     *
     * Initial states:
     *  - free[0] = 0
     *  - hold[0] = -prices[0]
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
}
