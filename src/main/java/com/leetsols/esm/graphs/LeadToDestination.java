package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeadToDestination {
    /*
     * Description:
     * - Given a directed graph.
     * - Given 2 nodes, [start] and [end], check does all paths starting from the [start]
     *   ends at the [end].
     * - If a path exists from the source node to a node with no outgoing edges, then that
     *   node is equal to destination (This is description of the destination node itself,
     *   not the node which might match to the criteria of destination)
     * - Return true only if the ALL paths leads to the destination node
     *
     * Approach:
     * - Directed graph that might contain a cycle
     */

    private enum State {UNVISITED, VISITING, VISITED}

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        var graph = makeGraph(edges);

        if (graph.containsKey(destination) && !graph.get(destination).isEmpty()) return false;

        // Initialize all nodes to unvisited
        State[] states = new State[n];
        Arrays.fill(states, State.UNVISITED);

        return dfs(source, destination, graph, states);
    }

    public boolean leadsToDestination2(int n, int[][] edges, int source, int destination) {
        var graph = new HashMap<Integer, List<Integer>>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }

        if (graph.containsKey(destination) && !graph.get(destination).isEmpty()) return false;

        var visiting = new HashSet<Integer>();
        var visited = new HashSet<Integer>();
        return dfsTwo(source, destination, graph, visiting, visited);
    }

    public boolean dfsTwo(int source, int destination, Map<Integer, List<Integer>> graph, Set<Integer> visiting, Set<Integer> visited) {
        if (visiting.contains(source)) return false;
        if (visited.contains(source)) return false;

        var neighbors = graph.get(source);
        if (neighbors == null || neighbors.isEmpty()) return source == destination;

        visiting.add(source);

        for (int neighbor : neighbors) {
            if (!dfsTwo(neighbor, destination, graph, visiting, visited)) return false;
        }

        // Done exploring, move it from visiting to visited.
        visiting.remove(source);
        visited.add(source);
        return true;
    }

    public boolean dfs(int source, int destination, Map<Integer, List<Integer>> graph, State[] states) {
        // Hit the node currently processing
        if (states[source] == State.VISITING) return false;
        // Fully processed node in the current path
        if (states[source] == State.VISITED) return true;

        var neighbors = graph.get(source);

        // Check the leaf node for outgoing edges
        if (neighbors == null || neighbors.isEmpty()) return source == destination;

        // Mark the current node as visited
        states[source] = State.VISITED;

        for (int neighbor : neighbors) {
            if (!dfs(neighbor, destination, graph, states)) return false;
        }

        // Fully processed this node and all its sub-paths safely
        states[source] = State.VISITED;
        return true;
    }

    public Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        var graph = new HashMap<Integer, List<Integer>>();
        for (int[] edge : edges) graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        return graph;
    }
}