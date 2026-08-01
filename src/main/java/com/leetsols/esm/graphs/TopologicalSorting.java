package com.leetsols.esm.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopologicalSorting {
    /*
     * Definition:
     * - Linear ordering of the nodes such that there is an edge [u -> v]
     *   where [u] appears before [v] in the ordering.
     * - For Undirected graphs, only [u -> v] is not applicable, it cannot be sure whether the
     *   edge is between [u -> v] or [v -> u].
     * - In cyclic graph there will always be a dependency factor. You cannot make sure that
     *   you can have a linear ordering of nodes.
     *
     * Approach:
     * - Node should come before all the nodes it points to. So in DFS, you don't add a node
     *   to the result when you first visit it — you add it after you've fully explored all
     *   its descendants. The last node to finish is the first in topological order.
     *
     * - 1. Pick any unvisited node and call dfs(node).
     *   Mark it as "visiting" (in-progress). This lets you detect cycles later — if you reach
     *   a node already marked "visiting", you've found a cycle.
     *
     * - 2. Recursively visit all neighbors.
     *   For each neighbor that hasn't been visited yet, call dfs(neighbor). You go as deep
     *   as possible before coming back.
     *
     * - 3. When a node has no more unvisited neighbors — push it onto the stack
     *   This is the crucial moment. Only after all reachable descendants are fully processed
     *   do you push the current node. This guarantees: every node you point to is already in
     *   the stack below you.
     *
     * - 4. Repeat for every unvisited node in the graph
     *   If the graph has multiple connected components or multiple starting points, you loop
     *   through all nodes and call DFS on any that haven't been visited yet.
     *
     * - 5. Pop the stack to read the topological order
     *   Since deeper nodes (with no outgoing edges) were pushed first, popping gives you a valid
     *   ordering where every node appears before its dependents.
     */

    // Graph: Adjacency List
    private final Map<String, List<String>> graph = new HashMap<>();

    public List<String> sort() {
        var visited = new HashSet<String>();
        var visiting = new HashSet<String>();
        var stack = new ArrayDeque<String>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (!dfs(node, visited, visiting, stack)) return Collections.emptyList();
            }
        }

        var result = new ArrayList<String>();
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    /*
     * Algorithm:
     * - Input graph contains a node as a key and list of its neighbors (directed edges
     *   going to them from node)
     * - The whole algorithm based on the idea of incoming nodes, if a node has in-degree of
     *   0, it means it has absolutely no prerequisites, and can be processed immediately
     *
     * - Count the number of incoming edges for each node, if the input is adjacency list
     *   we do it by passing through each node in the graph and incrementing the quantity
     *   for its neighbors in the [inDegree] array.
     * - Initialize the queue, iterate over the inDegree array and offer the nodes which
     *   have value of 0, there are the nodes which can be processed firstly.
     * - Initialize resulting array.
     * - Poll each node from the queue and for all its neighbors in the graph decrease
     *   quantity of incoming edges by 1 as this node has been processed already.
     * - If after decreasing number of incoming edges, quantity become 0, put this node to the queue
     */
    public List<Integer> kahnAlgorithm(int numOfNodes, Map<Integer, List<Integer>> graph) {
        // Count the number of incoming edges for each node
        int[] inDegree = new int[numOfNodes];
        for (int i = 0; i < numOfNodes; i++) {
            for (int neighbor : graph.get(i)) inDegree[neighbor]++;
        }

        // Init a queue and enqueue all nodes with indegree 0
        var queue = new LinkedList<Integer>();
        for (int i = 0; i < numOfNodes; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        var topoOrder = new ArrayList<Integer>();
        // Process nodes from the queue
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            topoOrder.add(curr);

            // Reduce the in-degree of all its neighbors
            for (int neighbor : graph.get(curr)) {
                inDegree[neighbor]--;

                // If indegree becomes 0, add it to the queue
                if (inDegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        // Check for the cycle
        if (topoOrder.size() != numOfNodes) return new ArrayList<>();
        return topoOrder;
    }

    public boolean dfs(String node, Set<String> visited, Set<String> visiting, Deque<String> stack) {
        // Mark as in-progress
        visiting.add(node);

        for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            // Back to the edge, cycle detected
            if (visiting.contains(neighbor)) return false;

            if (!visited.contains(neighbor)) {
                if (!dfs(neighbor, visited, visiting, stack)) return false;
            }
        }

        visiting.remove(node);
        visited.add(node);
        stack.push(node);
        return true;
    }
}
