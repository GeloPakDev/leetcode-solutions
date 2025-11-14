package com.leetsols.esm.arrays;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Array, Sorting, Prefix Sum, Ordered Set
 * Number: 2021 Brightest Position on Street
 */
public class BrightestPosition {
    public int findBrightestPosition(int[][] lights) {
        /*
         * - brightness changes by value at each position
         * - entering the light coverage left  -> increase the brightness
         * - exiting  the light coverage right -> decrease the brightness
         */
        List<int[]> change = new ArrayList<>();
        for (int[] light : lights) {
            int position = light[0], radius = light[1];
            change.add(new int[]{position - radius, 1});
            change.add(new int[]{position + radius + 1, -1});
        }

        change.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int ans = 0;
        int curr = 0;
        int brightest = 0;

        for (int[] ints : change) {
            int position = ints[0], value = ints[1];
            curr += value;
            if (curr > brightest) {
                brightest = curr;
                ans = position;
            }
        }
        return ans;
    }
}
