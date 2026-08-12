package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveStones {

    /*
     * Algorithm:
     * - Make a use of UnionFind to identify the number of connected components.
     * - Treat each stone as a separate set, every stone starts with its own component
     *   , then we iterate over each pair of stones and merge them, [count] keeps track
     *   total number of connected components in the graph, each successful [union]
     *   operation means that 2 separate components have merged into one, decrement count.
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        var uf = new UnionFind(n);

        for (int row = 0; row < n; row++) {
            for (int col = row + 1; col < n; col++) {
                if (stones[row][0] == stones[col][0] || stones[row][1] == stones[col][1]) uf.union(row, col);
            }
        }

        return n - uf.count;
    }

    static class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
            count = n;
        }

        public int find(int node) {
            if (parent[node] == -1) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            count--;
            parent[rootX] = rootY;
        }
    }

    /*
     * Algorithm:
     * - Neighbors of the node, all nodes in the same column or row, it can be
     *   represented as a connected component, as the path can exist from the
     *   node A->B->C in the same row.
     * - We can remove all but one stone. The remaining stone cannot be removed
     *   as it is no longer shares coordinates with any other stone, which have
     *   been eliminated in the component.
     * - Max removable nodes can be calculated by substracting the total number of
     *   stones - number of connected components
     *
     * - Make an adjacency list to maintain the neighbors of all nodes in the same
     *   row or columnn
     * - Run the DFS, start from the unvisited node, marking all reachable nodes in
     *   the same visited and count is as the connected component. Repeat this process
     *   untill all stones are visited. The number of DFS executions will give us the
     *   total number of connected components in grid,
     */
    public int removeStonesDFS(int[][] stones) {
        int n = stones.length;

        // Adjacency list to store the graph connections
        List<Integer>[] adjacencyList = new List[n];
        for (int i = 0; i < n; i++) adjacencyList[i] = new ArrayList<>();

        /*
         * Build the graph: Connect the stones that share the same row and column
         * - stones[row][0] == stones[col][0] -> same row
         * - stones[row][1] == stones[col][1] -> same col
         */
        for (int row = 0; row < n; row++) {
            for (int col = row + 1; col < n; col++) {
                if (stones[row][0] == stones[col][0] || stones[row][1] == stones[col][1]) {
                    adjacencyList[row].add(col);
                    adjacencyList[col].add(row);
                }
            }
        }

        int connectedComponents = 0;
        boolean[] visited = new boolean[n];

        // Traverse all stones using DFS to count connected component
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adjacencyList, visited, i);
                connectedComponents++;
            }
        }
        return n - connectedComponents;
    }

    public void dfs(List<Integer>[] graph, boolean[] visited, int stone) {
        visited[stone] = true;

        for (int neighbor : graph[stone]) {
            if (!visited[neighbor]) dfs(graph, visited, neighbor);
        }
    }
}
