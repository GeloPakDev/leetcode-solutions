package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] seen = new boolean[n];
        /*
         * Convert array of edges -> Adjacency List
         */
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            graph.computeIfAbsent(x, _ -> new ArrayList<>()).add(y);
            graph.computeIfAbsent(y, _ -> new ArrayList<>()).add(x);
        }
        return dfs(graph, seen, source, destination);
    }

    public boolean dfs(Map<Integer, List<Integer>> graph, boolean[] seen, int source, int dest) {
        /*
         * - The path is found
         */
        if (source == dest) {
            return true;
        }
        /*
         * - Mark current node as visited
         */
        seen[source] = true;
        for (int neighbor : graph.get(source)) {
            if (!seen[neighbor]) {
                /*
                 * - Call DFS on neighbor
                 * - If any call returns true, propagate result upwards
                 */
                if (dfs(graph, seen, source, dest)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int countComponents(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] seen = new boolean[n];

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            graph.computeIfAbsent(x, _ -> new ArrayList<>()).add(y);
            graph.computeIfAbsent(y, _ -> new ArrayList<>()).add(x);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                ans += 1;
                seen[i] = true;
                dfs(i, graph, seen);
            }
        }

        return ans;
    }

    public void dfs(int node, HashMap<Integer, List<Integer>> graph, boolean[] seen) {
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!seen[neighbor]) {
                seen[neighbor] = true;
                dfs(neighbor, graph, seen);
            }
        }
    }
}