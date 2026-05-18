package com.leetsols.esm.geometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MergeIntervals {
    /*
     * Description:
     * Merge all overlapping intervals and return an array of the non-overlapping
     * intervals that cover all the intervals in the input
     *
     * Algorithm:
     * - Sort the list of intervals by [start] value, then each set of intervals
     *   be merged will appear as contiguous run in the sorted list.
     * - If the current interval begins after the previous interval ends, then
     *   they do not overlap, and we can append the current interval to merged list.
     * - Otherwise they do overlap, and we can merge them by updating the end of the
     *   previous interval if it is less than the end of the current interval
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            /*
             * If the list of merged intervals is empty or if the current
             * interval does not overlap with the previous, append it
             */
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) merged.add(interval);
            else merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
