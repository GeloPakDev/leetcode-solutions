package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Sorting, Simulation, Prefix Sum, Heap,
 * Number: 1094 Car Pooling
 */
public class CarPooling {
    /*
     * - Find the largest value of TO
     * - Build an array of size TO + 1
     * - For each passenger entering at location [from] -> arr[from] += numOfPassengers
     * - For each passenger leaving  at location [to]   -> arr[to]   -= numOfPassengers
     * - After processing all events, each value at arr[i] indicates change of passengers at each position
     * - Process a prefix sum and check if the sum exceeds current capacity
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int farthest = 0;
        for (int[] trip : trips) {
            farthest = Math.max(farthest, trip[2]);
        }

        int[] arr = new int[farthest + 1];
        for (int[] trip : trips) {
            int value = trip[0], left = trip[1], right = trip[2];
            arr[left] += value;
            arr[right] -= value;
        }

        int curr = 0;
        for (int j : arr) {
            curr += j;
            if (curr > capacity) {
                return false;
            }
        }
        return true;
    }
}
