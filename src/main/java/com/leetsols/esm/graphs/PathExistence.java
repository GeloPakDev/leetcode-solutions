package com.leetsols.esm.graphs;

public class PathExistence {
    static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
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

    /*
     * Approach:
     * - Edge exist between the nodes [i, j] if the absolute difference
     *   between nums[i] and nums[j] <= diff. All edges in the graph
     *   will have the weight <= diff
     * - nums array is given in the non-decreasing order, there is no meaning
     *   try to create/calculate an edge for the next nodes as soon as diff
     *   encountered larger than the maxDiff
     * - Transitivity:
     *  - Because the array nums is sorted in non-decreasing order, we know that for any 3
     *    indices where:
     *    - [i < j < k]: nums[i] <= nums[j] <= nums[k]
     *
     *    This means the distance between [nums[i] and nums[k]] is always the sum of the
     *    smaller distances between them:
     *    - [nums[k] - nums[i]] = (nums[k] - nums[j]) + (nums[j] - nums[i])
     *
     *    If [nums[k] - nums[i]] <= maxDiff, then the smaller individual steps (nums[k] - nums[j]
     *    and nums[j] - nums[i]) must also be less than or equal to maxDiff.
     *  - Conversely, if there is any single adjacent gap that is greater than maxDiff
     *    (nums[j] - nums[i] > maxDiff), then it is physically impossible for [nums[i]] to bridge
     *    over to [nums[k]]. That large gap acts as an unpassable wall, splitting the graph into
     *    two completely isolated islands.
     */
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        var disjointSet = new UnionFind(n);

        // Linear pass: Only evaluate adjacent nodes
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) disjointSet.union(i, i - 1);
        }

        var res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int nodeX = queries[i][0];
            int nodeY = queries[i][1];
            res[i] = disjointSet.isConnected(nodeX, nodeY);
        }
        return res;
    }
}
