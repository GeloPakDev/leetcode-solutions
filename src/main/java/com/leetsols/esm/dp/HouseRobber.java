package com.leetsols.esm.dp;

import java.util.Arrays;

public class HouseRobber {
    private int[] memo;

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        int[] dp = new int[n];

        //Base cases
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    public int robDP(int[] nums) {
        int n = nums.length;

        if (n == 0) return 0;

        int[] maxRobbedAmount = new int[nums.length - 1];

        maxRobbedAmount[n] = 0;
        maxRobbedAmount[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            maxRobbedAmount[i] = Math.max(maxRobbedAmount[i + 1], maxRobbedAmount[i + 2] + nums[i]);
        }
        return maxRobbedAmount[0];
    }

    public int robBacktrack(int[] nums, int i) {
        if (i >= nums.length) return 0;

        if (memo[i] != -1) return memo[i];

        int robCurrent = nums[i] + robBacktrack(nums, i + 2);

        int skipCurrent = robBacktrack(nums, i + 1);

        memo[i] = Math.max(robCurrent, skipCurrent);
        return memo[i];
    }

    /*
     * Houses are placed in the circle
     * Approach:
     * - Break the circle into the 2 Linear House Robber problems
     * - Rob within a range [0 to n - 2] (Exclude the last house)
     * - Rob within a range [0 to n - 1] (Include the last house)
     */
    public int robTwo(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // Case 1: Rob from idx 0 to n - 2
        memo = new int[n];
        Arrays.fill(memo, -1);
        int case1 = robRange(nums, 0, n - 2);

        // Case 2: Rob from idx 0 to n - 1
        Arrays.fill(memo, -1);
        int case2 = robRange(nums, 1, n - 1);

        return Math.max(case1, case2);
    }

    public int robRange(int[] nums, int i, int end) {
        if (i > end) return 0;
        if (memo[i] != -1) return memo[i];

        int robCurrent = nums[i] + robRange(nums, i + 2, end);
        int skipCurrent = robRange(nums, i + 1, end);

        memo[i] = Math.max(robCurrent, skipCurrent);
        return memo[i];
    }
}
