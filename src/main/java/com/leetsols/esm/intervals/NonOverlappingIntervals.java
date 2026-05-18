package com.leetsols.esm.intervals;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    /*
     * Description:
     * - Given unsorted list of intervals, we need to remove the minimum number
     *   of intervals to make all intervals in the list as not overlapped. In
     *   other words, we should find out the maximum number of non-overlapping
     *   intervals. (Interval scheduling problem).
     *
     * Approach (Greedy):
     * - To minimize removals, we need to keep as many intervals as possible,
     *   the best way to do this, is to pick the interval that ends the earliest
     *   because it leaves the maximum amount of space for subsequent intervals
     *   to fit.
     *
     * Algorithm:
     * - Sort the intervals based on their [end] times
     * - Iterate through the sorted intervals
     * - If the current interval starts after or at the same time the previous
     *   one [ended], they don't overlap, we update 'end' marker.
     * - If they overlap, we remove one. Since we sorted by the end times, the
     *   current one ends later than our, we should skip it and increment
     *   removal count.
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 0;
        // Initialize [end] with the end time of the first interval
        int end = intervals[0][1];

        // Iterate starting from the second interval
        for (int i = 1; i < intervals.length; i++) {
            /*
             * If the start of the current one is less than the end of the
             * previous one, overlap is found, increment removals
             *
             * No overlap is found, update the end marker to the current interval
             */
            if (intervals[i][0] < end) count++;
            else end = intervals[i][1];
        }
        return count;
    }
}
