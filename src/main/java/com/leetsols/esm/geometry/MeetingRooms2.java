package com.leetsols.esm.geometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MeetingRooms2 {
    /*
     * Algorithm:
     * - Represent each time as events:
     *  - (Start of the event, +1)
     *  - (End   of the event, -1)
     * - Sort all events
     * - Iterate over all events and for each one;
     *  - Add value of each event to the current sum
     *  - Update the maximum number of the rooms required at the current moment
     */
    public int minMeetingRooms(int[][] intervals) {
        var map = new TreeMap<Integer, Integer>();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }

        var currSum = 0;
        var sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            currSum += entry.getValue();
            sum = Math.max(sum, currSum);
        }
        return sum;
    }

    /*
     * Priority Queue:
     * - Sort an array of intervals based on their start values
     *  - (1, 10), (2, 7), (3, 19), (8, 12), (10, 20), (11, 30)
     * - During the check of the new meeting, checking the available room
     *   can take O(n) time complexity, however it can be done in the
     *   time complexity O(1) using the Min-Heap, with the key being the
     *   ending time of meeting.
     * - Every time we want to check if any room is empty or not, we take
     *   the top-most element from the min-heap, if it isn't free then we
     *   allocate the new room.
     *
     * Algorithm:
     * - Sort the array by the start time
     * - Initialize the Min-Heap and store only the ending times
     * - For every meeting room, check if the min element of the heap
     *   , the room at the top of the heap is free or not
     *  - If it is free, we extract the top most element and add it back
     *    with ending time of the current meeting we are processing
     *  - If not, we allocate the new meeting room
     * - Size of the heap will tell us the number of rooms allocated
     */
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals.length == 0) return 0;

        var pq = new PriorityQueue<Integer>(intervals.length, Comparator.comparingInt(o -> o));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        pq.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            /*
             * If the current room starts after the min one in the heap, we can allocate
             * this room, by removing it from the min-heap
             */
            if (intervals[i][0] >= pq.peek()) pq.poll();

            /*
             * If the new room is to be assigned, we add this interval to the heap
             * If the old room is allocated, then we also have to add to the heap
             */
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }

    /*
     * Chronological Ordering:
     * - The main idea to make separate and sort the start and end events,
     *   when we encounter the ending event, that means that some event
     *   that started earlier has ended and the room become available.
     *
     * Algorithm:
     * - Separate out the start and end times
     * - Sort them separately
     * - Use 2 pointers:
     *  - start pointer - track when the meeting has been started
     *  - end pointer - track when the meeting has been ended.
     *  - if (start < end) -> the room is not available yet, allocate another one
     *   move the start pointer
     *  - if (start > end) -> some meeting has been ended, the room become empty
     *   move the end pointer
     * - Repeat until start_pointer processes all the meetings
     */
    public int minMeetingRooms3(int[][] intervals) {
        int n = intervals.length;

        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            int[] interval = intervals[i];
            start[i] = interval[0];
            end[i] = interval[1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int res = 0;
        int rooms = 0;

        int startPtr = 0;
        int endPtr = 0;

        while (startPtr < n) {
            if (start[startPtr] < end[endPtr]) {
                startPtr++;
                rooms++;
            } else {
                endPtr++;
                rooms--;
            }
            res = Math.max(res, rooms);
        }
        return res;
    }
}
