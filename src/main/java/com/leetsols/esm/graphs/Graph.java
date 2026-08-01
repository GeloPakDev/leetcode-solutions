package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

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