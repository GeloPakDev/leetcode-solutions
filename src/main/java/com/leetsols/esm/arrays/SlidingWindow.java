package com.leetsols.esm.arrays;

public class SlidingWindow {
    /*
     * Base Sliding Window approach
     */
    public int findLength(int[] nums, int k) {
        int left = 0;
        int curr = 0;
        int answer = 0;

        for (int right = 0; right < nums.length; right++) {
            curr += nums[right];
            while (curr > k) {
                curr -= nums[left];
                left++;
            }
            answer = Math.max(answer, right - left + 1);
        }
        return answer;
    }

    public int findBestSubarray(int[] nums, int k) {
        int curr = 0;
        // Build the first window
        for (int i = 0; i < k; i++) {
            curr += nums[i];
        }

        int ans = curr;
        for (int i = k; i < nums.length; i++) {
            curr += nums[i] - nums[i - k];
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int curr = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == '0') {
                curr++;
            }

            while (curr > k) {
                if (nums[left] == '0') {
                    curr--;
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
