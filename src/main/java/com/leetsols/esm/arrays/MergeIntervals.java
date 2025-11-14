package com.leetsols.esm.arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Problem type: Array, Sorting
 * Number: 56. Merge Intervals
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        /*
         * [[1, 10], [2, 6], [8, 10], [15, 18]]
         * - Sort an array by starting points
         * - Take the end of the first interval and compare with start of the others
         * - As soon as the end < start == False, take the start of the first interval
         * and end of the interval with the highest start.
         *  [1,  10]
         *  [2,  6]
         *  [8,  10]  [1,  10]
         *  [15, 18]  [15, 18]
         */

        if (intervals.length <= 1)
            return intervals;
        // Sorting sub-arrays by starting numbers
        java.util.Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        //Create a list of intervals
        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0]; // [1, 3]
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
