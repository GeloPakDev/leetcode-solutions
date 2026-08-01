package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CompleteComponents {
    /*
     * Algorithm:
     * - Property of Completed Connected Graph: number of edges: (k * (k - 1)) / 2
     */
    public int countCompleteComponents(int n, int[][] edges) {
        var graph = makeGraph(edges);
        var queue = new LinkedList<Integer>();
        var visited = new HashSet<Integer>();
        int res = 0;

        for (int i = 0; i < n; i++) {
            // Avoid checking already processed node, which checked during component calculation
            if (visited.contains(i)) continue;
            queue.add(i);
            visited.add(i);

            // Count nodes processed, also avoid the loop
            var currCompNodes = new ArrayList<Integer>();
            while (!queue.isEmpty()) {
                var curr = queue.poll();
                currCompNodes.add(curr);

                for (int elm : graph.getOrDefault(curr, new ArrayList<>())) {
                    if (!visited.contains(elm)) {
                        visited.add(elm);
                        queue.add(elm);
                    }
                }
            }

            int numOfNodes = currCompNodes.size();
            boolean isComplete = true;

            for (int node : currCompNodes) {
                int degree = graph.getOrDefault(node, new ArrayList<>()).size();
                if (degree != numOfNodes - 1) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete) res++;
        }
        return res;
    }

    /*
     * Union Find Approach:
     * - Completed connected component -> every node in the component
     *   has an edge to every other node in the component, it means that
     *   every node has (n - 1) outgoing edges, where [n] is the number
     *   of edges in the graph.
     * - Completed Connected Component -> Disjoint Unit of a graph which doesn't
     *   share connections with other parts of the graph
     * - Union Find implementation using the rank, let us count size of each connected
     *   component, plus it describes the count of vertices in it. So to check whether
     *   the disjoint unit is completed connected component, we check its edge count
     *   (k * (k - 1)) / 2, where [k] is the number of nodes.
     */
    public int countCompleteComponentsDSU(int n, int[][] edges) {
        // Initialize
        var unionFind = new UnionFind(n);
        var edgeCount = new HashMap<Integer, Integer>();

        // Count connected components
        for (int[] edge : edges) unionFind.union(edge[0], edge[1]);

        /*
         * Count edges in a connected component
         * - Since the path-compression is applied during find operation and union
         *   operation applied to bind the nodes in single connected component,
         *   parent for all of them will be the same root.
         * - For each root of the connected component, map will store the number
         *   of edges, as each node whose root is seen in the rank, has an edge
         *   to this root.
         * - For the connected component of the single node, quantity == 0.
         */
        for (int[] edge : edges) {
            int root = unionFind.find(edge[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }

        /* Check if each component is complete
         * - Iterate through each root node and check its validity, group is
         *   complete if edge count == (k * (k - 1)) / 2
         */
        int res = 0;
        for (int node = 0; node < n; node++) {
            if (unionFind.find(node) == node) {
                int nodeCnt = unionFind.rank[node];
                int expectedEdges = (nodeCnt * (nodeCnt - 1)) / 2;
                if (edgeCount.getOrDefault(node, 0) == expectedEdges) res++;
            }
        }
        return res;
    }

    private Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        var graph = new HashMap<Integer, List<Integer>>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, _ -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, _ -> new ArrayList<>()).add(u);
        }
        return graph;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];
            for (int i = 0; i < size; i++) parent[i] = -1;
            for (int i = 0; i < size; i++) rank[i] = 1;
        }

        public int find(int x) {
            if (parent[x] == -1) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int root1 = find(x);
            int root2 = find(y);

            if (root1 == root2) return;

            // Merge smaller component into larger one
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
                rank[root1] += rank[root2];
            } else {
                parent[root1] = root2;
                rank[root2] += rank[root1];
            }
        }
    }
}
