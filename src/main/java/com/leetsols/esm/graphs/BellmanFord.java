package com.leetsols.esm.graphs;

import java.util.Arrays;
import java.util.List;

public class BellmanFord {
    static class Edge {
        int source, target, weight;

        Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }

    public boolean bellmanFord(List<Edge> edges, int numVertices, int source) {
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Relax all edges (V - 1) times
        for (int i = 0; i < numVertices; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] != Integer.MAX_VALUE &&
                        distances[edge.source] + edge.weight < distances[edge.target]) {
                    distances[edge.target] = distances[edge.source] + edge.weight;
                }
            }
        }

        // Last check for the negative-weight cycle
        for (Edge edge : edges) {
            if (distances[edge.source] != Integer.MAX_VALUE &&
                    distances[edge.source] + edge.weight < distances[edge.target]) {
                System.out.println("Graph contains a negative weight cycle");
                return false;
            }
        }

        return true;
    }
}
