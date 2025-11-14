package com.leetsols.esm.graphs;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphDFS {
    public static void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> seen) {
        // Mark input node as visited
        seen.add(node);
        System.out.println("Visited: " + node);

        // Visit all unvisited nodes
        for (int neighbor : graph.get(node)) {
            if (!seen.contains(neighbor)) {
                dfs(neighbor, graph, seen);
            }
        }
    }
}
