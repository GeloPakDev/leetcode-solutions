package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidPath {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        var graph = buildGraph(edges);
        var set = new HashSet<Integer>();
        return dfs(source, destination, set, graph);
    }

    public boolean validPathDSU(int n, int[][] edges, int source, int destination) {
        var unionFind = new UnionFind(n);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            unionFind.union(u, v);
        }

        return unionFind.isConnected(source, destination);
    }


    public boolean dfs(int source, int destination, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        if (source == destination) return true;
        visited.add(source);

        for (int neighbor : graph.get(source)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, destination, visited, graph)) return true;
            }
        }
        return false;
    }

    public boolean bfs(int source, int destination, Map<Integer, List<Integer>> graph) {
        var queue = new LinkedList<Integer>();
        var visited = new HashSet<Integer>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) return true;

            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];
            for (int i = 0; i < size; i++) parent[i] = i;
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
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            if (!graph.containsKey(x)) graph.put(x, new ArrayList<>());
            graph.get(x).add(y);

            if (!graph.containsKey(y)) graph.put(y, new ArrayList<>());
            graph.get(y).add(x);
        }
        return graph;
    }
}
