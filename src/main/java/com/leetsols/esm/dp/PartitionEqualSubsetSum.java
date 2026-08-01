package com.leetsols.esm.dp;

public class PartitionEqualSubsetSum {
    /*
     * Approach:
     * - To partition array into the 2 subsets, the size of the 2 subsets should be
     *   equal to the sum of the numbers in the nums array
     * - totalSum = targetSum * 2
     * - TotalSum of the array should be even, only then we can divide it into 2 equal subsets
     *
     * Brute Force Algorithm:
     * - Generate all possible subsets of an array and return true if we find a subset
     *   with required sum.
     * -
     */
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // If the totalSum, is odd, it cannot be partitioned into equal sum
        if (totalSum % 2 != 0) return false;
        int subSetSum = totalSum / 2;
        int n = nums.length;
        return dfs(nums, n - 1, subSetSum);
    }

    /*
     * Base cases:
     * - For any given element x from an array, there might be 2 possibilities:
     *  - [x] is included in the subset sum, subSetSum = subSetSum - x
     *  - [x] is not included in the subset sum, so we must take previous sum without [x]
     *    subSetSum = subSetSum
     * - Recurrence Relation: isSum(subSetSum, n) = isSum(subSetSum - nums[n], n - 1) ||
     *                                              isSum(subSetSum, n - 1)
     * - Base cases:
     *  - if (subSetSum == 0) -> return true
     *  - if (subSetSum < 0)  -> return false
     *
     */
    public boolean dfs(int[] nums, int n, int subSetSum) {
        if (subSetSum == 0) return true;
        if (n == 0 || subSetSum < 0) return false;

        return dfs(nums, n - 1, subSetSum - nums[n - 1]) ||
                dfs(nums, n - 1, subSetSum);
    }

    /*
     * Algorithm:
     * - 2D array memo,
     * - check if the subSetSum for a given [n] exists, to avoid re-calculation
     *   the answer and return the result in memo
     */
    public boolean canPartitionDP(int[] nums) {
        int res = 0;
        for (int num : nums) res += num;

        if (res % 2 == 0) return false;

        int subSetSum = res / 2;
        int n = nums.length;
        Boolean[][] memo = new Boolean[n + 1][subSetSum + 1];
        return dfsDp(nums, n - 1, subSetSum, memo);
    }

    public boolean dfsDp(int[] nums, int n, int subSetSum, Boolean[][] memo) {
        if (subSetSum == 0) return true;
        if (n == 0 || subSetSum < 0) return false;
        if (memo[n][subSetSum] != null) return memo[n][subSetSum];

        boolean res = dfsDp(nums, n - 1, subSetSum - nums[n - 1], memo) ||
                dfsDp(nums, n - 1, subSetSum, memo);

        memo[n][subSetSum] = res;
        return res;
    }

    /*
     * Algorithm:
     * - dp[n][subSetSum], for an array element [i] and sum [j]
     *   dp[i][j] = true, if the sum [j] can be formed by array elements in subset
     *   nums[0]...nums[i], otherwise dp[i][j] = false
     * - dp[i][j] == true, if:
     *  - sum can be formed without including i[th] element, if (dp[i - 1][j] == true)
     *  - sum can be formed including i[th] element, if (dp[i - 1][j - nums[i]] == true)
     *
     */
    public boolean canPartitionTabulation(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        if (totalSum % 2 != 0) return false;

        int subSetSum = totalSum / 2;
        int n = nums.length;

        boolean[][] dp = new boolean[n + 1][subSetSum + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            for (int j = 0; j <= subSetSum; j++) {
                if (j < curr) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] || (dp[i - 1][j - curr]);
            }
        }
        return dp[n][subSetSum];
    }
}
