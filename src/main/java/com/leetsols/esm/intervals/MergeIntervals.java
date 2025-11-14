package com.leetsols.esm.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MergeIntervals {
    /*
     * [1, 3], [2, 6], [8, 10], [15, 18]
     * - Merge all overlapping intervals
     *  - At the current interval, start <= previous end time
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(a -> a[0]));
        LinkedList<int[]> ans = new LinkedList<>();

        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            /*
             * start value at the current interval <= previous end time
             * update the end time of the previous -> max(previous end, current end)
             */
            if (!ans.isEmpty() && start <= ans.getLast()[1]) {
                ans.getLast()[1] = Math.max(ans.getLast()[1], end);
            } else {
                ans.addLast(interval);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}