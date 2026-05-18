package com.leetsols.esm.arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class KClosestPoints {

    /*
     * Max-Heap approach
     *
     * Given: points[i] = [x[i], y[i]]
     * Return the k[th] closest points to the origin [0,0]
     * Distance between 2 points given as follows:
     * - sqrt((x[1] - x[2]) ^ 2 - (y[1] - y[2]) ^ 2)
     * - sqrt((x[0] - x[points[i][0]]) ^ 2 -
     *        (y[0] - y[points[i][1]]) ^ 2)
     *
     * Algorithm:
     * - Push Pair where key is array and value is the distance into the MinHeap
     * - Poll k top elements from the heap and put into the array
     */
    public static int[][] kClosest(int[][] points, int k) {
        var heap = new PriorityQueue<>(Map.Entry.<int[], Integer>comparingByValue().reversed());
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            int distSq = (x * x) + (y * y);

            heap.add(Map.entry(point, distSq));

            if (heap.size() > k) heap.poll();
        }

        var res = new int[k][];
        for (int i = 0; i < k; i++) res[i] = Objects.requireNonNull(heap.poll()).getKey();
        return res;
    }


    /*
     * QuickSelect approach:
     * - Repeatedly partition a range of elements in the given array while
     *   homing on the k[th] element
     * - Partition function:
     *  - Choose the pivot element. It should be the squared Euclidean distance
     *    from the origin to the pivot element and will be compared to the
     *    squared Euclidean distance of all other points in the partition
     *  - Start at the pointers at the left and right ends of the partition,
     *    then while 2 pointers have not yet met:
     *   - If the value of the element at the left pointer is smaller than the
     *     pivot value, increment the left pointer.
     *   - Otherwise swap the elements at the two pointers and decrement the
     *     right pointer.
     *  - Make sure that the left pointer is past the last element whose value
     *    is lower than the pivot value.
     *  - Return the left pointer as the new pivot index
     */
    public int[][] kClosest2(int[][] points, int k) {
        return quickSelect(points, k);
    }

    private int[][] quickSelect(int[][] points, int k) {
        int left = 0;
        int right = points.length - 1;
        int pivotIdx = points.length;
        while (pivotIdx != k) {
            /*
             * Repeatedly partition the array
             * while narrowing in on the k[th] element
             */
            pivotIdx = partition(points, left, right);
            if (pivotIdx < k) left = pivotIdx;
            else right = pivotIdx - 1;
        }
        // Return the first k elements of the partially sorted array
        return Arrays.copyOf(points, k);
    }

    public int partition(int[][] points, int left, int right) {
        int[] pivot = choosePivot(points, left, right);
        int pivotDistance = squaredDistance(pivot);

        while (left < right) {
            /*
             * Iterate through the range and swap elements to make sure
             * that all points closer than the pivot are to the left.
             */
            if (squaredDistance(points[left]) >= pivotDistance) {
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                right--;
            } else {
                left++;
            }
        }
        /*
         * Ensure the left pointer is just past the end of
         * the left range then return it as the new pivotIndex
         */
        if (squaredDistance(points[left]) < pivotDistance) left++;
        return left;
    }
    private int[] choosePivot(int[][] points, int left, int right) {
        // Choose a pivot element of the array
        return points[left + (right - left) / 2];
    }

    private int squaredDistance(int[] point) {
        // Calculate and return the squared Euclidean distance
        return point[0] * point[0] + point[1] * point[1];
    }
}
