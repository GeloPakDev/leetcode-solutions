package com.leetsols.esm.greedy;

import java.util.Arrays;

public class MaxNumberOfApples {
    public int maxNumberOfApples(int[] weight) {
        int ans = 0;
        int count = 0;
        Arrays.sort(weight);
        for (int j : weight) {
            count += j;
            if (count < 5000) {
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }
}
