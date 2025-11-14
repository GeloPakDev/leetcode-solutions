package com.leetsols.esm.greedy;

import java.util.Arrays;

public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int x = nums[0];

        for (int num : nums) {
            if (num - x > k) {
                x = num;
                ans++;
            }
        }
        return ans;
    }


}
