package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ReconstructItinerary {
    /*
     * Algorithm:
     * - Given an array of edges
     * - Form an adjacency list where:
     *  - key: String
     *  - value: Map<String, Boolean> indicates that edge to this neighbor is visited or not
     *    store the values in the lexical order (TreeMap).
     * - Start the DFS traversal from the JFK node.
     * - Move to the neighbor which is stored as the first in the tree, it will give us
     *   automatically the lowest lexical neighbor.
     * - Remove this entry from the tree as it is already visited
     * - On the next iteration we recurse into the chosen entry.
     * - Continue traversal until the key which will not exceed from the neighbors.
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        var res = new ArrayList<String>();
        var graph = new HashMap<String, PriorityQueue<String>>();

        for (var list : tickets) {
            graph.computeIfAbsent(list.get(0), _ -> new PriorityQueue<>()).add(list.get(1));
        }

        dfs("JFK", res, graph);
        return res;
    }

    public void dfs(String node, List<String> res, Map<String, PriorityQueue<String>> graph) {
        var destinations = graph.get(node);

        // Move to the neighbor which is the lowest lexical order
        while (destinations != null && !destinations.isEmpty()) {
            String nextDestination = destinations.poll();
            dfs(nextDestination, res, graph);
        }
        res.addFirst(node);
    }
}
