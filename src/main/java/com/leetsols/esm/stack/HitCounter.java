package com.leetsols.esm.stack;

import java.util.ArrayDeque;

public class HitCounter {
    /*
     * Goal:
     * - Count a number of hits received in the last 5 minutes (the past 300 seconds)
     * - Accept [timestamp] parameter in seconds
     * - Calls (hits) are made in the chronological order (monotonically increasing)
     * - Several hits may arrive at the same time
     */
    private int startTimeStamp;
    private final ArrayDeque<Integer> queue;

    public HitCounter() {
        queue = new ArrayDeque<>();
        startTimeStamp = 0;
    }

    /*
     * - Several hits can occur at the same time
     *
     * - 360,303,6,5,4,3,2,1,1,1
     * - each time a new request arrives, count the difference between the firstTimeStamp
     *   and a new arrived one, if it exceeds 300, remove all previous hits until the diff
     *   lastTimestamp - queue.pop() <= 300
     * - By the end of the iteration in the queue, peek element would be the correct and current
     *   startTimeStamp
     */
    public void hit(int timestamp) {
        if (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
                queue.pop();
            }
        }
        queue.offer(timestamp);
    }

    /*
     * Return the number of hits made in the last 5 minutes
     * - Get the size of the queue or stack. The idea is to preserve the window
     *   of 5 minutes range based on the start and last hit arrived.
     * - If 5 minutes didn't expire yet, return the current size of the array
     * - startTimeStamp - indicate when first hit arrived
     * - lastTimeStamp
     *  if (lastTimeStamp - startTimeStamp < 300)
     *   return size
     * - remove from the head of the queue until the diff between the
     *   lastTimeStamp - startTimeStamp <= 300
     *
     *
     *
     * - 360,303,6,5,4,3,2,1,1,1
     * - each time a new request arrives, count the difference between the firstTimeStamp
     *   and a new arrived one, if it exceeds 300, remove all previous hits until the diff
     *   lastTimestamp - queue.pop() <= 300
     * - getHits can be called after, continuous period of time, the same check will be required
     *
     *
     *
     * - getHits(timestamp) -> timestamp here is the capture when the call is done
     * - timestamp here is the lasTimeStamp.
     * - duplicates should be preserved, as total amount should be returned.
     */
    public int getHits(int timestamp) {
        if (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
                queue.pop();
            }
        }
        return queue.size();
    }
}
