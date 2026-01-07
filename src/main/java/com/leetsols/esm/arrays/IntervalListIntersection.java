package com.leetsols.esm.arrays;

import java.util.ArrayList;

/*
 * Problem type: Array, Two Pointers, Line Sweep
 * Number: 986 Interval List Intersections
 */
public class IntervalListIntersection {
    /*
     * Input:
     * firstList = [[0,2],[5,10],[13,23],[24,25]]
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     *
     * Algorithm:
     * - Find intersection between 2 intervals [a, b] && [c, d]
     *  - Start: Intersection starts at MAX(a, c)
     *  - End: Intersection starts at MIN(b, d)
     *  - Condition: Intersection exists only if start <= end
     * - Move pointer that points to the interval that ends earlier. Since the lists are sorted,
     *   interval that ends first cannot intersect with future intervals
     *
     */

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        var list = new ArrayList<int[]>();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {
            // Find boundaries of intersection
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.max(firstList[i][1], secondList[j][1]);

            // If start <= end, it's valid intersection
            if (start <= end) list.add(new int[]{start, end});

            // Move pointer of the interval that ends first
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }

}
