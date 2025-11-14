package com.leetsols.esm.intervals;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingAttendant {
    /*
     * - Conflict meetings would be adjacent if we sorted them out
     * - If the i-th meeting starts before the i-1 th meeting ends -> conflict
     */
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
