package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SingleSourceShortestPath {
    /*
     * Algorithm:
     * - Single Source Shortest Path in the DAG (Directed Acyclic Graph)
     * - Topological Sorting:
     *  - Lines up the nodes in a straight line so that every directed edge points
     *    from the left to right.
     *  - Valid Invariant: When we have arrived at the node, we know every possible path
     *    that could ever lead to it.
     * - Initialize the Distances for each node to Infinity.
     * - Once the nodes in the topological ordering, we begin relaxing the edges, by
     *   processing each node in the Topological order:
     *  - If the current node's distance is Infinity, skip it (it means unreachable from source)
     *  - For each neighbor of the current node, relax the edge
     *   if (distance[current] + weight < distance[neighbor]):
     *       distance[neighbor] = distance[current] + weight
     */
    static final class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Find the shortest path from a source node to all other nodes
    public static Map<Integer, Integer> findShortestPath(Map<Integer, List<Edge>> graph, int source) {
        // Get Topological Sorting
        List<Integer> topoOrder = getTopologicalSort(graph);

        // Initialize distances
        Map<Integer, Integer> distances = new HashMap<>();
        for (Integer node : graph.keySet()) distances.put(node, Integer.MAX_VALUE);
        distances.put(source, 0);

        // Process nodes in the Topological Order
        for (int u : topoOrder) {
            // If the node haven't been reached yet, skip it
            if (distances.get(u) == Integer.MAX_VALUE) continue;

            // Relaxing all outgoing edges from node 'u'
            List<Edge> edges = graph.getOrDefault(u, new ArrayList<>());
            for (Edge edge : edges) {
                int v = edge.target;
                int weight = edge.weight;

                int newDist = distances.get(u) + weight;

                if (newDist < distances.get(v)) distances.put(v, newDist);
            }
        }
        return distances;
    }

    public static List<Integer> getTopologicalSort(Map<Integer, List<Edge>> graph) {
        LinkedList<Integer> order = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                topoDfs(node, graph, visited, order);
            }
        }
        return order;
    }

    public static void topoDfs(int u, Map<Integer, List<Edge>> graph, Set<Integer> visited, LinkedList<Integer> order) {
        visited.add(u);
        for (Edge edge : graph.getOrDefault(u, new ArrayList<>())) {
            if (!visited.contains(edge.target)) {
                topoDfs(edge.target, graph, visited, order);
            }
        }
        order.addFirst(u); // Push to the front of the list when fully processed
    }
}
