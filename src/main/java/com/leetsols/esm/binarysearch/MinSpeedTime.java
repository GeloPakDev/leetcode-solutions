package com.leetsols.esm.binarysearch;

public class MinSpeedTime {
    /*
     * - Even the speed of the train is fast, you will need to wait dist[i] which is the departure of the train
     * - The task is impossible when the number of trains is more than amount of hours
     */
    double limit;

    public int minSpeedOnTime(int[] dist, double hour) {
        /*
         * - If we have more trains than amount of hours available
         */
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }

        limit = hour;
        int left = 1;
        int right = (int) Math.pow(10, 7);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, dist)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /*
     * t -> How much time we have spent so far
     * Math.ceil indicates how much we have to wait
     * t += d/k -> distance / speed
     * finally check if the time taken match within the limit
     */
    public boolean check(int k, int[] dist) {
        double t = 0;
        for (double d : dist) {
            t = Math.ceil(t);
            t += d / k;
        }
        return t <= limit;
    }
}
