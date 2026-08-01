package com.leetsols.esm.graphs;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    static class Edge {
        int targetNode;
        int weight;

        public Edge(int targetNode, int weight) {
            this.targetNode = targetNode;
            this.weight = weight;
        }
    }

    public static class NodePair implements Comparable<NodePair> {
        int node;
        int distance;

        public NodePair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        // Min-Priority Queue based on distance
        @Override
        public int compareTo(NodePair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    /*
     * Dijkstra:
     * - Greedy approach.
     * - Set distance to all nodes as Infinity and the source node as 0.
     * - Use Min-Heap to get the node with Lowest Current distance first.
     * - For the current node, check all unvisited neighbor, If the distance to the
     *   current node + weight of the edge to the neighbor is lower than the neighbors
     *   previously distance, update it.
     * - Mark the current node as visited, and take the next Edge from the Min-Heap.
     *   Repeat until the queue is empty
     */
    public static int[] dijkstra(List<List<Edge>> graph, int source) {
        int totalNodes = graph.size();
        int[] distances = new int[totalNodes];
        boolean[] visited = new boolean[totalNodes];

        // Step 1. Initialize all distances to Infinity
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Step 2. Initialize Min-Priority Queue
        PriorityQueue<NodePair> pq = new PriorityQueue<>();
        pq.add(new NodePair(source, 0));

        while (!pq.isEmpty()) {
            var current = pq.poll();
            var currentNode = current.node;

            // If we've already processed this node's shortest path, skip it
            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            /*
             * Step 3: Relaxation Phase
             * - Get all neighbors of the currentNode in the form:
             * Node 1: Node 2, 12
             *         Node 3, 31
             *         Node 4, 8
             *         Node 5, 1
             * - Compute the distance to each unvisited node --->>> if (!visited[edge.targetNode]) <<<---
             * - If a shorter path (summing the weight to reach current node AND neighbor)
             *   smaller than the cost to reach the neighbor, update it.
             * - Add a node with updated value to the Min-Heap.
             */
            for (Edge edge : graph.get(currentNode)) {
                if (!visited[edge.targetNode]) {
                    int newDist = distances[currentNode] + edge.weight;

                    // If a shorter path to the neighbor is found
                    if (newDist < distances[edge.targetNode]) {
                        distances[edge.targetNode] = newDist;
                        pq.add(new NodePair(edge.targetNode, newDist));
                    }
                }
            }
        }
        return distances;
    }
}
