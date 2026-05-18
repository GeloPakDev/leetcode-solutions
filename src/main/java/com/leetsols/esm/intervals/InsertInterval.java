package com.leetsols.esm.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    /*
     * Main points:
     * - List is sorted already based on the start values.
     *
     * 3 possible cases during the comparison of current interval with the new one.
     *  - Case 1. The current interval ends before the new interval starts.
     *  - Case 2. There is an overlap, and the intervals need merging.
     *  - Case 3. The current interval starts after the new interval ends.
     *
     * How to identify an overlap and merge intervals?
     * - start of the newInterval is less than the end of the current interval
     * - max(first[end], second[end]) && min(first[start], second[start]) -> merging
     *
     * No overlaps before Merging
     * - Current intervals ends before the new interval starts
     *
     * Overlapping and Merging
     * - Starting point of the current interval <= ending point of the new interval
     *
     * No overlapping after Merging
     * - Current interval starts after the new interval ends
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int i = 0;
        List<int[]> res = new ArrayList<>();

        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);

        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }

    /*
     * 1) Finding the insertion position:
     * - Perform the binary search comparing the starting position of the current
     *   interval and the starting position of the new interval
     *  - If the mid < target move to the right, left = mid + 1
     *  - If the mid > target move to the left, right = mid - 1
     *
     * 2) Merging:
     *  - If res is empty or the [end] of the [last interval] < start of the current one
     *    there is no overlapping, current interval cna be added to the res
     *  - Else, current interval is merged with the last interval from the [res],
     *    The end of the last interval in the res is updated to the
     *    max(last_from_list[end], current[end])
     */
    public int[][] insertBS(int[][] intervals, int[] newInterval) {
        // If the list is empty we can return the newInterval
        if (intervals.length == 0) return new int[][]{newInterval};

        int n = intervals.length;
        int target = newInterval[0];
        int left = 0, right = n - 1;

        /*
         * Apply the binary search by comparing the start values of the middle and
         * the current interval.
         */
        while (left <= right) {
            int mid = (left + right) / 2;
            if (intervals[mid][0] < target) left = mid + 1;
            else right = mid - 1;
        }

        /*
         * Insert the newInterval at the found position
         */
        var list = new ArrayList<int[]>();
        for (int i = 0; i < left; i++) list.add(intervals[i]);
        list.add(newInterval);
        for (int i = left; i < n; i++) list.add(intervals[i]);

        /*
         * Merge overlapping intervals
         * - If the res is empty or if the end of the last interval is less than the
         *   start of the current one. Append it in the final list
         * - If there is an overlap, update the endpoint of the current interval
         *   by updating taking the max from the two.
         */
        var res = new ArrayList<int[]>();
        for (int[] interval : list) {
            if (res.isEmpty() || res.getLast()[1] < interval[0]) res.add(interval);
            else res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
        }
        return res.toArray(new int[0][]);
    }
}
