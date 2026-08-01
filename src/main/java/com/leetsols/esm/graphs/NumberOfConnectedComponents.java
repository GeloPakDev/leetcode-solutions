package com.leetsols.esm.graphs;

public class NumberOfConnectedComponents {
    private int[] parent;
    private int[] rank;

    public int countComponents(int n, int[][] edges) {
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            union(u, v);
        }

        int res = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) res++;
        }
        return res;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rank[rootX] < rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootX] = rootY;
            rank[rootX]++;
        }
    }
}
