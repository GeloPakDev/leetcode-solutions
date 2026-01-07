package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Problem type: Array, Hash Table, Depth-First Search
 * Number: 1743. Restore the Array From Adjacent Pairs
 */
public class RestoreArrayFromPair {
    /*
     * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs,
     * either as ([nums[i], nums[i+1]] || [nums[i+1], nums[i]]). The pairs can appear in any order.
     * - Edges are undirected
     *
     * Algorithm:
     * - To re-create the original array, we need perform traversal of the graph, starting
     *   from one end of the doubly-linked-list
     * - The root node has only one edge, once you have a root node, you can start DFS from it
     *
     * - Firstly iterate over the adjacentPairs to create adjacency list that represents
     *   undirected acyclic graph
     * - Iterate over the graph to identify the root node or the node with 1 edge
     */
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : adjacentPairs) {
            int x = edge[0];
            int y = edge[1];
            graph.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            graph.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        int start = 0;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }

        List<Integer> resultList = new ArrayList<>();
        dfs(start, graph, resultList, new HashSet<>());
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void dfs(int node, Map<Integer, List<Integer>> graph, List<Integer> result, Set<Integer> visited) {
        // Mark the node as visited
        visited.add(node);
        result.add(node);

        // Visit all unvisited neighbors
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, result, visited);
            }
        }
    }

    /*
     * Algorithm:
     * - As the current graph is double linked list, we don't even need to
     *   apply the DFS or BFS, simple iterative look up over the Linked List can be done
     * - Create adjacency list and find root as before
     */
    public int[] restoreArrayTwo(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : adjacentPairs) {
            int x = edge[0];
            int y = edge[1];
            graph.computeIfAbsent(x, _ -> new ArrayList<>()).add(y);
            graph.computeIfAbsent(y, _ -> new ArrayList<>()).add(x);
        }

        int start = 0;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }

        /*
         * curr - start of traversal
         * - First node in the resulting array is the start node of the iteration
         * - Check the neighbor: Is this neighbor has been visited? (neighbor != prev)
         *  - Y: skip it
         *  - N: this is a next node to visit
         * - Add the new node into the resulting array
         * - Update [prev] to the node you are leaving
         * - Update [curr] to the neighbor we are entering
         * - Break inner loop to check other neighbors at this moment
         */
        int curr = start;
        int[] ans = new int[graph.size()];
        ans[0] = start;
        int i = 1;
        int prev = Integer.MAX_VALUE;

        while (i < graph.size()) {
            for (int neighbor : graph.get(curr)) {
                if (neighbor != prev) {
                    ans[i] = neighbor;
                    i++;
                    prev = curr;
                    curr = neighbor;
                    break;
                }
            }
        }
        return ans;
    }
}
