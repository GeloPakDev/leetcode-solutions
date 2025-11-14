package com.leetsols.esm.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastNumberOfK {
    /*
     * - The Least number of unique integers left after k removals
     * - arr = [4,3,1,1,3,3,2], k = 3
     * - 4 -> 1
     * - 2 -> 1
     * - 1 -> 2
     * - 3 -> 3
     * - Count frequencies
     * -
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<Integer> ordered = new ArrayList<>(count.values());

        ordered.sort(Comparator.reverseOrder());

        while (k > 0) {
            int val = ordered.getLast();
            if (val <= k) {
                k -= val;
                ordered.removeLast();
            } else {
                break;
            }
        }
        return ordered.size();
    }
}
