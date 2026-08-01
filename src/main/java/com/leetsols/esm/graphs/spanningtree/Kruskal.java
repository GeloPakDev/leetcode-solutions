package com.leetsols.esm.graphs.spanningtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    public record Edge(int source, int destination, int weight) implements Comparable<Edge> {
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return false;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootX;
                rank[rootX]++;
            }
            return true;
        }

    }

    public List<Edge> minimumSpanningTree(List<Edge> edges, int numOfNodes) {
        Collections.sort(edges);

        var disjointSet = new DisjointSet(numOfNodes);
        var res = new ArrayList<Edge>();

        // Iterate through sorted edges to make a tree
        for (Edge edge : edges) {
            // If true, no cycle was formed
            if (disjointSet.union(edge.source, edge.destination)) res.add(edge);

            // Early termination optimization: MST always has exactly (n - 1) edges
            if (res.size() == numOfNodes - 1) break;
        }
        return res;
    }
}
