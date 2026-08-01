package com.leetsols.esm.graphs;

public class GraphValidTree {
    private int[] parent;
    private int[] rank;

    public boolean validTree(int n, int[][] edges) {
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i < n; i++) parent[i] = i;

        // General check for the cycle in the tree.
        for (int[] edge : edges) {
            int row = edge[0];
            int col = edge[1];

            if (!union(row, col)) return false;
        }

        // Check for the connected components, finally there should be only single root
        int res = 0;
        for (int i = 0; i < parent.length; i++) if (parent[i] == i) res++;
        return res == 1;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        if (rank[rootX] < rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootX] = rootY;
            rank[rootX]++;
        }
        return true;
    }
}
