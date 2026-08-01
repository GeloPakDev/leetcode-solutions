package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfProvinces {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    boolean[] seen;

    public int findCircleNum(int[][] isConnected) {
        /*
         * - Build the Adjacent List from Adjacent Matrix
         * - Adjacent List represents the Node with all its Neighbors
         * - Connections (edges) are given us in the input (P.S. this is not a graph)
         */
        int n = isConnected.length;
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                graph.put(i, new ArrayList<>());
            }
            for (int j = i + 1; j < n; j++) {
                if (!graph.containsKey(j)) {
                    graph.put(j, new ArrayList<>());
                }
                if (isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        /*
         * Track the visited cities
         * - If the node is NOT SEEN, recursively call dfs(neighbor)
         * - ANSWER variable marks that the whole province will be covered
         */
        seen = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                ans++;
                seen[i] = true;
                dfs(i);
            }
        }
        return ans;
    }

    /*
     * - Traversal on the node will visit every node in connected component
     * - Take the neighbors for each node
     * - If neighbor is not in the [seen] list -> dfs(neighbor)
     */
    public void dfs(int node) {
        for (int neighbor : graph.get(node)) {
            if (!seen[neighbor]) {
                seen[neighbor] = true;
                dfs(neighbor);
            }
        }
    }

    // Disjoint Set implementation
    private int[] parent;
    private int[] rank;

    public int findCircleNumDisSet(int[][] isConnected) {
        int n = isConnected.length;

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i < n; i++) parent[i] = i;

        for (int row = 0; row < isConnected.length; row++) {
            for (int col = 0; col < isConnected[0].length; col++) {
                if (isConnected[row][col] == 1 && row != col) {
                    union(row, col);
                }
            }
        }

        // Go over the parent array to count the number of roots
        int res = 0;
        for (int i = 0; i < parent.length; i++) if (parent[i] == i) res++;
        return res;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
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
