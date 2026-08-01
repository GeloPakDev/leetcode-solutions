package com.leetsols.esm.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0;
        for (int i : dp) {
            longest = Math.max(longest, i);
        }

        return longest;
    }

    public int lengthOfLisTwo(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                int j = 0;
                while (num > sub.get(j)) {
                    j += 1;
                }
                sub.set(j, num);
            }
        }
        return sub.size();
    }

    public int lengthOfLisDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int maxLen = 1;
        // Iterate backwards from the second-last element to 0
        for (int i = n - 1; i >= 0; i--) {
            // Check all elements to the right of [i]
            for (int j = i + 1; j < n; j++) {
                // Check all formed subsequences to the right of [i], check each of them
                if (nums[i] < nums[j]) dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            // Store the longest increasing subsequence found
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
