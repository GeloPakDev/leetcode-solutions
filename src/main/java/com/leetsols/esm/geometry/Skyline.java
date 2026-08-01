package com.leetsols.esm.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Skyline {
    /*
     * Algorithm:
     * Brute Force:
     * - Collect all the points of the left and right edges from buildings.
     * - If a building with the height [h] covers the indexes from x[i] to x[j]
     *   then all indexes in this range have a height of [h].
     * - We can iterate over all buildings, and for each one, we update the
     *   maximum height for all indexes in the range of [left_idx, right_idx].
     * - Iterate over heights, and add all positions where height changes
     *   as a skyline key points.
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        /*
         * Collect all unique positions for the [left] and [right] edges of the
         * buildings and save them in the [edgeSet], it is required as building
         * with the same positions exists [[4 5],[7 5],[11 5]] -> right side is
         * the same, height might be different.
         *
         * Sorted unique positions from the set put into the list in order to become
         * associated with the indexes.
         */
        var edgeSet = new TreeSet<Integer>();
        for (int[] building : buildings) {
            int left = building[0];
            int right = building[1];
            edgeSet.add(left);
            edgeSet.add(right);
        }
        var edges = new ArrayList<Integer>(edgeSet);

        var edgeIndexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < edges.size(); i++) {
            edgeIndexMap.put(edges.get(i), i);
        }

        // Record the maximum height at each index.
        int[] heights = new int[edges.size()];

        // Iterate over all buildings
        for (int[] building : buildings) {
            // For each of the building get the indexes of its left and right edges
            int left = building[0];
            int right = building[1];
            int height = building[2];
            int leftIndex = edgeIndexMap.get(left);
            int rightIndex = edgeIndexMap.get(right);
            // Update maximum height within the range [left_idx, right_idx]
            for (int i = leftIndex; i < rightIndex; i++) {
                heights[i] = Math.max(heights[i], height);
            }
        }

        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            int currentHeight = heights[i];
            int currentPos = edges.get(i);

            if (answer.isEmpty() || answer.getLast().get(1) != currentHeight) {
                answer.add(Arrays.asList(currentPos, currentHeight));
            }
        }
        return answer;
    }

    /*
     * Algorithm:
     * Brute Force ii, Sweep Line:
     * - Collects every left anf right points in the set, they are indicators for
     *   the points of each building for the line.
     * -
     *
     *
     *
     *
     *
     * - Iteratre over the sorted positions, and for each position:
     *  - Check for building intersect with the imaginary vertical line
     *    at [position] (Intersected building is the one if position is
     *    within the range [left, right]).
     * - max_height is the maximum height of the intersecting buildings
     *   at [position], or 0 if no building intersects with the line.
     */
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        // Collect and sort all unique positions of the edges of each building
        var edgeSet = new TreeSet<Integer>();
        for (int[] building : buildings) {
            int left = building[0];
            int right = building[1];
            edgeSet.add(left);
            edgeSet.add(right);
        }
        var positions = new ArrayList<>(edgeSet);

        List<List<Integer>> ans = new ArrayList<>();
        int maxHeight, left, right, height;

        /*
         * Go over each position (x coordinate of the building), pos is acting as
         * a vertical line here, to record the maximum height among all the buildings
         * that intersect with the line.
         */
        for (int pos : positions) {
            maxHeight = 0;

            for (int[] building : buildings) {
                left = building[0];
                right = building[1];
                height = building[2];

                // If the current building intersects with the line, update 'maxHeight'
                if (left <= pos && pos < right) maxHeight = Math.max(maxHeight, height);
            }

            /*
             * If the tallest height at the current position is the same as the tallest
             * height at the previous position, we don't add a new point
             */
            if (ans.isEmpty() || ans.getLast().get(1) != maxHeight) {
                ans.add(Arrays.asList(pos, maxHeight));
            }
        }
        return ans;
    }
}
