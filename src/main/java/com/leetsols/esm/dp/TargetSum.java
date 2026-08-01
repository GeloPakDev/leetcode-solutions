package com.leetsols.esm.dp;

import java.util.Arrays;

public class TargetSum {
    int totalWays = 0;

    public int findTargetSumWays(int[] nums, int target) {
        calculateWays(nums, 0, 0, target);
        return totalWays;
    }

    /*
     * Algorithm:
     * - If currIdx == length of nums -> if the currSum == target -> totalWays++;
     * - Else
     *  - Recurse with a number of the positive sign
     *    (currIdx + 1, currSum + nums[currIdx])
     *  - Recurse with a number of the negative sign
     *    (currIdx + 1, currSum - nums[currIdx])
     */
    public void calculateWays(int[] nums, int idx, int currSum, int target) {
        if (idx == nums.length) {
            if (currSum == target) totalWays++;
        } else {
            calculateWays(nums, idx + 1, currSum + nums[idx], target);
            calculateWays(nums, idx + 1, currSum - nums[idx], target);
        }
    }

    public int calculateWaysDP(int[] nums, int idx, int currSum, int target, int[][] memo) {
        if (idx == currSum) {
            if (currSum == target) return 1;
            else return 0;
        } else {
            // Check if the result already computed
            if (memo[idx][currSum + totalWays] != Integer.MIN_VALUE) return memo[idx][currSum + totalWays];

            // Calculate the ways by adding the current number
            int add = calculateWaysDP(nums, idx + 1, currSum + nums[idx], target, memo);
            int sub = calculateWaysDP(nums, idx + 1, currSum - nums[idx], target, memo);

            // Store the curr result in memo table
            memo[idx][currSum + totalWays] = add + sub;
            return memo[idx][currSum + totalWays];
        }
    }

    public int findTargetSumWaysDP(int[] nums, int target) {
        int totalSum = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2 * totalSum + 1];

        // Initialize the first row of the DP table
        dp[0][nums[0] + totalSum] = 1;
        dp[0][-nums[0] + totalSum] += 1;

        // Fill in the DP table
        for (int index = 1; index < nums.length; index++) {
            for (int sum = -totalSum; sum <= totalSum; sum++) {
                if (dp[index - 1][sum + totalSum] > 0) {
                    dp[index][sum + nums[index] + totalSum] += dp[index - 1][sum + totalSum];
                    dp[index][sum - nums[index] + totalSum] += dp[index - 1][sum + totalSum];
                }
            }
        }
        return Math.abs(target) > totalSum ? 0 : dp[nums.length - 1][target + totalSum];
    }
}
