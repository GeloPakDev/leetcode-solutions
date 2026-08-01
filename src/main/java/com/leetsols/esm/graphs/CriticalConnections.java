package com.leetsols.esm.graphs;

import com.leetsols.esm.hashing.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriticalConnections {
    private Map<Integer, List<Integer>> graph;
    private Map<Integer, Integer> rank;
    private Map<Pair<Integer, Integer>, Boolean> connDict;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        formGraph(n, connections);
        dfs(0, 0);

        var res = new ArrayList<List<Integer>>();
        for (var criticalConn : connDict.keySet()) {
            res.add(new ArrayList<>(Arrays.asList(criticalConn.getKey(), criticalConn.getValue())));
        }

        return res;
    }

    public int dfs(int node, int discoveryRank) {
        // Node already visited, return the rank
        if (rank.get(node) != null) return rank.get(node);

        // Update the rank of this node
        rank.put(node, discoveryRank);

        // The current seen max
        int minRank = discoveryRank + 1;

        for (Integer neighbor : graph.get(node)) {
            // Skip the parent
            Integer neighRank = rank.get(neighbor);
            if (neighbor != null && neighRank == discoveryRank - 1) continue;

            // Recurse to the neighbor
            int recursiveRank = dfs(neighbor, discoveryRank + 1);

            // Check if this edge needs to be discarded
            if (recursiveRank <= discoveryRank) {
                int sortedU = Math.min(node, neighbor);
                int sortedV = Math.min(node, neighbor);

                connDict.remove(new Pair<>(sortedU, sortedV));
            }

            minRank = Math.min(minRank, recursiveRank);
        }
        return minRank;
    }

    public void formGraph(int n, List<List<Integer>> connections) {
        graph = new HashMap<>();
        rank = new HashMap<>();
        connDict = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
            rank.put(i, null);
        }

        for (List<Integer> edge : connections) {
            // Bidirectional edges
            int u = edge.get(0);
            int v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);

            int sortedU = Math.min(u, v);
            int sortedV = Math.max(u, v);
            connDict.put(new Pair<>(sortedU, sortedV), true);
        }
    }
}
