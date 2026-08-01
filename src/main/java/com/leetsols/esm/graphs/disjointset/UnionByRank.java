package com.leetsols.esm.graphs.disjointset;

/*
 * Implementation: Union by Rank + Path compression
 */
public class UnionByRank {
    private int[] parent;
    private int[] rank;

    public UnionByRank(int size) {
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

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY; // X is shorter -> goes under Y
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX; // Y is shorter -> goes under X
        } else {
            parent[rootY] = rootX; // same height, pick either, increment rank
            rank[rootX]++;
        }
    }
}
