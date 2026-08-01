package com.leetsols.esm.graphs.spanningtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class CriticalAndPseudoCritical {
    /*
     * Critical Edge:
     * - If we delete this edge from the graph, new computed MST will be greater
     *   than original MST, or the graph will be completely disconnected
     *
     * Pseudo-Critical Edge:
     * - Edge belong to some MST, not to all. If we forcibly include this edge into
     *   the MST, resulting MST cost will be exactly equal to original MST
     *
     * Algorithm:
     * - Prepare: As we have to return the original distance of the edges, but
     *   Kruskal's requires to sort the edges by weight, we first wrap the edge in
     *   the new array: [u, v, weight, orig_idx].
     * - Get MST weight: Run Kruskal's to get the weight of the MST.
     * - Evaluate every edge loop:
     *  - Run Kruskal's without the edge, If the cost increases or the graph breaks,
     *    this is the critical edge
     *  - Else, run Kruskal's force the edge to be chosen first, If the cost equals
     *    the cost of the MST, it is Pseudo-Critical
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // Add index to edges for tracking
        int numOfEdges = edges.length;
        int[][] sortedEdges = new int[numOfEdges][4];
        for (int i = 0; i < numOfEdges; i++) {
            sortedEdges[i][0] = edges[i][0];// u
            sortedEdges[i][1] = edges[i][1];// v
            sortedEdges[i][2] = edges[i][2];// weight
            sortedEdges[i][3] = i; // Store original index
        }

        // Sorted edges by weight
        Arrays.sort(sortedEdges, Comparator.comparingInt(a -> a[2]));

        // Get the weight of the MST
        int mstWeight = builtMST(n, sortedEdges, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        // Test each edge case
        for (int i = 0; i < numOfEdges; i++) {
            int originalIdx = sortedEdges[i][3];

            // Test for Critical: Exclude current edge:
            int weightWithout = builtMST(n, sortedEdges, i, -1);

            if (weightWithout > mstWeight) {
                critical.add(originalIdx);
            } else {
                // Test for the pseudo-critical: Force include current edge
                int weightWith = builtMST(n, sortedEdges, -1, i);
                if (weightWith == mstWeight) pseudo.add(originalIdx);
            }
        }
        return Arrays.asList(critical, pseudo);
    }

    public int builtMST(int n, int[][] sortedEdges, int ignoreIndex, int forceIndex) {
        var uf = new UnionFind(n);
        int weight = 0;

        // If we are forcing the edge, process it first
        if (forceIndex != 1) {
            int[] edge = sortedEdges[forceIndex];
            uf.union(edge[0], edge[1]);
            weight += edge[2];
        }

        // Kruskal's loop 
        for (int i = 0; i < sortedEdges.length; i++) {
            if (i == ignoreIndex) continue;// Skip ignored edge

            int[] edge = sortedEdges[i];

            // If 2 nodes are not connected yet, connect them and add weight
            if (uf.union(edge[0], edge[1])) weight += edge[2];
        }

        // If the graph is fully connected, return the weight
        return uf.isConnected() ? weight : Integer.MAX_VALUE;
    }


    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        private int components;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            components = n;
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);

            if (rootI != rootJ) {
                // Union by Rank: attach the shorter tree to the taller tree
                if (rank[rootI] < rank[rootJ]) {
                    parent[rootI] = rootJ;
                } else if (rank[rootI] > rank[rootJ]) {
                    parent[rootJ] = rootI;
                } else {
                    // If ranks are equal, attach one to the other and increase the rank
                    parent[rootJ] = rootI;
                    rank[rootI]++;
                }
                components--;
                return true;
            }
            return false;
        }

        public boolean isConnected() {
            return components == 1;
        }
    }
}