package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelCourses {
    /*
     * Algorithm:
     * - To get the fastest learning speed, strategy is:
     *  - Learn all courses available in each semester.
     *
     * - We can't start from courses with prerequisites.
     *  - We start from nodes with NO prerequisites.
     *
     * - If the number of nodes which we have visited is less than
     *   the number of total nodes, there is no way to learn all courses
     *
     * - Build a directed graph from relations.
     * - Record in-degree for each node (number of edges towards the node).
     * - Put all the nodes with an in-degree of 0 into queue.
     * - step = 0, visited_count = 0
     *
     * - Start BFS
     *  - Initialize the [next_queue] to record the nodes needed in the next iteration
     *    it is used to go over level by level, after passing all courses in one semester
     *  - Increment step
     *  - For each [node] in [queue]
     *   - Increment visistedCount
     *   - For each [end_node] reachable from [node]
     *    - Decrement the in-degree of [end_node]
     *    - If the in-degree of [end_node] == 0, push it into [next_queue]
     *   - Assign [queue] = [next_queue]
     */
    public int minimumSemesters(int n, int[][] relations) {
        int[] inCount = new int[n + 1];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());

        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            inCount[relation[1]]++;
        }

        int step = 0;
        int visitedCount = 0;

        var queue = new ArrayList<Integer>();
        for (int node = 1; node < n + 1; node++) {
            if (inCount[node] == 0) queue.add(node);
        }

        while (!queue.isEmpty()) {
            step++;

            var nextQueue = new ArrayList<Integer>();
            for (int node : queue) {
                visitedCount++;
                for (int endNode : graph.get(node)) {
                    inCount[endNode]--;

                    if (inCount[endNode] == 0) nextQueue.add(endNode);
                }
            }
            queue = nextQueue;
        }

        return visitedCount == n ? step : -1;
    }

    /*
     * Reference to the DAG Scheduling Problem
     */
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        // prereq[i] stores the bitmask of prerequisites required for course i
        int[] prereq = new int[n];
        for (int[] r : relations) {
            int prev = r[0] - 1; // 0-index conversion
            int next = r[1] - 1;
            prereq[next] |= (1 << prev);
        }

        int maxMask = 1 << n;
        int[] dp = new int[maxMask];
        Arrays.fill(dp, n + 1); // Initialize with infinity equivalent
        dp[0] = 0; // 0 semesters needed for 0 courses taken

        for (int mask = 0; mask < maxMask; mask++) {
            if (dp[mask] > n) continue;

            // Step 1: Find all courses currently ready to take
            int available = 0;
            for (int i = 0; i < n; i++) {
                // If course i is not taken AND all its prerequisites are satisfied in mask
                if ((mask & (1 << i)) == 0 && (prereq[i] & mask) == prereq[i]) {
                    available |= (1 << i);
                }
            }

            // Step 2: Iterate over all submasks of available courses
            for (int submask = available; submask > 0; submask = (submask - 1) & available) {
                // We can take at most k courses in a single semester
                if (Integer.bitCount(submask) <= k) {
                    dp[mask | submask] = Math.min(dp[mask | submask], dp[mask] + 1);
                }
            }
        }

        return dp[maxMask - 1];
    }
}
