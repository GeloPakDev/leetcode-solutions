package com.leetsols.esm.graphs;

public class RedundantConnection {
    private int[] parent;
    private int[] rank;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        rank = new int[n + 1];

        // 1. Initialize: each node is its own parent, rank is 0
        for (int i = 1; i < n; i++) parent[i] = i;

        // Process each edge
        for (int[] edge : edges) {
            int row = edge[0];
            int col = edge[1];

            if (!union(row, col)) {
                return edge;
            }
        }
        return new int[0];
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        // Attach the smaller tree under the larger tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}
