package com.leetsols.esm.greedy;

import java.util.Arrays;

public class MaximumUnits {
    /*
     * - Sort the boxes by unit sizes
     * - Decrement the truck sizes
     * - Increment the sum of the
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int ans = 0;
        Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < boxTypes.length; i++) {
            while (boxTypes[i][0] > 0) {
                if (truckSize > 0) {
                    ans += boxTypes[i][1];
                    boxTypes[i][0]--;
                    truckSize--;
                } else {
                    break;
                }
            }
        }
        return ans;
    }
}
