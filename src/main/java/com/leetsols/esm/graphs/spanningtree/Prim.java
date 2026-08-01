package com.leetsols.esm.graphs.spanningtree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {
    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    /*
     * Algorithm:
     * - [visited] to track the vertex which already belongs to connected
     *   component, if it is false, it belongs to the remaining nodes.
     * - [cost] stores the current cheapest cost to connect the vertex
     *   [v] which is the neighbor of the current processing node [u], (u, v).
     * - [parent] stores actual structural link, parent[v] = u, for edge (u, v)
     *   means the cheapest way to include [v] into the tree via node [u].
     * - Min-Heap stores the absolute minimum edge crossing the CUT.
     */
    public static void primMST(List<List<Edge>> graph, int startNode) {
        int numOfNodes = graph.size();
        boolean[] visited = new boolean[numOfNodes];
        int[] parent = new int[numOfNodes];
        int[] cost = new int[numOfNodes];

        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        var pq = new PriorityQueue<Edge>(Comparator.comparing(e -> e.weight));
        cost[startNode] = 0;
        pq.add(new Edge(startNode, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().target;

            // Check this node is already part of the MST or not
            if (visited[u]) continue;
            visited[u] = true;

            // Iterate over the neighbors
            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weight = edge.weight;

                /*
                 * weight < cost[v]: is the weight of edge (u -> v) is cheaper
                 * than the previous best-known (cost[v]) to connect [v] to the
                 * tree
                 *
                 * True: Overwrite cost[v] with the lower weight, set parent[v] = u
                 * to capture the path, and push updated connection to the min-heap
                 */
                if (!visited[v] && weight < cost[v]) {
                    cost[v] = weight;
                    parent[v] = u;
                    pq.add(new Edge(v, cost[v]));
                }
            }
        }
    }
}