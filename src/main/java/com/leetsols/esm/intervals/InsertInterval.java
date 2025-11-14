package com.leetsols.esm.intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    /*
     * Three possible cases:
     *  - Case 1. The current interval ends before the new interval starts.
     *  - Case 2. There is an overlap, and the intervals need merging.
     *  - Case 3. The current interval starts after the new interval ends.
     *
     * Algorithm:
     *  - endpoint of curr interval < starting point of the new_one
     *  - overlap is detected if endpoint of the new interval and starting point of the current one
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
}
