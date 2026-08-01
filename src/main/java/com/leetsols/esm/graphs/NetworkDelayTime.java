package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    static class Edge {
        int targetNode;
        int weight;

        Edge(int targetNode, int weight) {
            this.targetNode = targetNode;
            this.weight = weight;
        }
    }

    static class NodePair implements Comparable<NodePair> {
        int node;
        int distance;

        NodePair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodePair o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    /*
     * n - nodes.
     * k - start node.
     *
     * Goal: Return the minimum time it takes for all [n] nodes, to receive the signal.
     *       If it is impossible for all nodes to receieve the signal, return -1
     *
     * Algorithm:
     * - Dijkstra:
     * - Prepare 3 DS to maintain the state:
     *  - distances array -> stores the distance it takes to reach the node [i]
     *  - visited array   -> maintain the state of visited node from the graph
     *  - min-heap        -> stores NodePair(node, distance) object which indicates
     *                       the minimal distance to reach the [node]
     *
     * - Initially fill weight of all nodes to infinity.
     * - Fill the [k]th node with 0 value.
     * - Insert the first element in the min-heap the start NodePair.
     * - Pop elements from the min-heap until it becomes empty
     *  - If the current node exists in the boolean array -> skip it
     *  - Else mark the current element in the array
     *
     *  - For each unvisited edge outgoing from the node, start relaxation:
     *   - distances[currentNode] + edge.weight < distances[targetNode]
     *   - distances[targetNode] = distances[currentNode] + edge.weight
     *   - Push new relaxed distance with node to the min-heap. This is done
     *     as this node is the next candidate to build the shortest path in the
     *     graph.
     * - As distances now contains all the distances to the nodes, we need to
     *   get the node with the max distance to reach, it will be the min distance
     *   to reach all nodes we have seen so far
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        var graph = makeGraph(times);

        int[] distances = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        var minHeap = new PriorityQueue<NodePair>();
        minHeap.add(new NodePair(k, 0));

        while (!minHeap.isEmpty()) {
            var node = minHeap.poll();
            int currentNode = node.node;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (Edge edge : graph.getOrDefault(currentNode, Collections.emptyList())) {
                if (!visited[edge.targetNode]) {
                    int newDist = distances[currentNode] + edge.weight;

                    if (newDist < distances[edge.targetNode]) {
                        distances[edge.targetNode] = newDist;
                        minHeap.add(new NodePair(edge.targetNode, newDist));
                    }
                }
            }
        }

        int maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (distances[i] == Integer.MAX_VALUE) return -1;
            maxDelay = Math.max(maxDelay, distances[i]);
        }
        return maxDelay;
    }

    public Map<Integer, List<Edge>> makeGraph(int[][] times) {
        var res = new HashMap<Integer, List<Edge>>();
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            if (!res.containsKey(from)) res.put(from, new ArrayList<>());
            res.get(from).add(new Edge(to, weight));
        }
        return res;
    }
}
