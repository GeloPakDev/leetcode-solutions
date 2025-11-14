package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfProvinces {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    boolean[] seen;

    public int findCircleNum(int[][] isConnected) {
        /*
         * - Build the Adjacent List from Adjacent Matrix
         * - Adjacent List represents the Node with all its Neighbors
         * - Connections (edges) are given us in the input (P.S. this is not a graph)
         * -
         */
        int n = isConnected.length;
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            }
            for (int j = i + 1; j < n; j++) {
                if (!graph.containsKey(j)) {
                    graph.put(j, new ArrayList<>());
                }
                if (isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        /*
         * Track the visited cities
         * - If the node is NOT SEEN, recursively call dfs(neighbor)
         * - ANSWER variable marks that the whole province will be covered
         */
        seen = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                ans++;
                seen[i] = true;
                dfs(i);
            }
        }
        return ans;
    }
    /*
     * - Traversal on the node will visit every node in connected component
     * - Take the neighbors for each node
     * - If neighbor is not in the [seen] list -> dfs(neighbor)
     */
    public void dfs(int node) {
        for (int neighbor : graph.get(node)) {
            if (!seen[neighbor]) {
                seen[neighbor] = true;
                dfs(neighbor);
            }
        }
    }
}
