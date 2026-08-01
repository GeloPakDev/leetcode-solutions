package com.leetsols.esm.graphs;

import java.util.Arrays;
import java.util.Comparator;

public class EarliestMoments {
    private int[] parent;
    private int[] rank;

    public int earliestAcq(int[][] logs, int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i < n; i++) parent[i] = i;

        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));

        long res = 0;
        for (int[] edge : logs) {
            long timestamp = edge[0];
            int u = edge[1];
            int v = edge[2];

            if (union(u, v)) res = timestamp;
        }

        int check = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) check++;
        }

        return check == 1 ? Math.toIntExact(res) : -1;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
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
