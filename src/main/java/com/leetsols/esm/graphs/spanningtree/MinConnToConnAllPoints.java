package com.leetsols.esm.graphs.spanningtree;

import com.leetsols.esm.hashing.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinConnToConnAllPoints {
    /*
     * Description:
     * - Given an array of points.
     * - For each point, generate edges to all other points, by calculating the Manhattan
     *   distance |x[src] - x[dst]| + |y[src] - y[dst]|
     * - Run the Kruskal to make MST,
     *   The Kruskal works, by identifying the lowest-weighted edge which is not part of
     *   the MST, if the nodes that belong to the edge are not connected, the edge is added
     *   to the MST, process repeated until all nodes are connected
     *
     * Algorithm:
     * - Actually we are considering all possible edges that is going from point
     *   x to all other points on the plane, in total N * N edges are going to
     *   be generated.
     * - To greedy choose the lowest-weighted edge, during generation we add an edge
     *   to the min-heap where the edge with the lowest cost is stored on the top
     *   of the heap, in other words it means we are sorting them
     * - To determine that MST doesn't have a cycle and will not have one, we will
     *   utilize the DSU, where during the union of nodes between each other, we
     *   can immediately identify formation of the cycle or not.
     * - A little optimization property of the MST is, when the number of edges
     *   will reach (n - 1) where [n] is the number of nodes in the MST, we can
     *   stop the process as MST cannot consist of the more edges.
     * -
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        var allEdges = new ArrayList<int[]>();

        for (int currNext = 0; currNext < n; currNext++) {
            for (int nextNext = currNext + 1; nextNext < n; nextNext++) {
                int weight = Math.abs(points[currNext][0] - points[nextNext][0]) +
                        Math.abs(points[currNext][1] - points[nextNext][1]);
                int[] currEdge = {weight, currNext, nextNext};
                allEdges.add(currEdge);
            }
        }

        allEdges.sort(Comparator.comparingInt(a -> a[0]));

        var du = new DisjointSet(n);
        int mstCost = 0;
        int edgesUsed = 0;

        for (int i = 0; i < allEdges.size() && edgesUsed < n - 1; i++) {
            int node1 = allEdges.get(i)[1];
            int node2 = allEdges.get(i)[2];
            int weight = allEdges.get(i)[0];

            if (du.union(node1, node2)) {
                mstCost += weight;
                edgesUsed++;
            }
        }
        return mstCost;
    }

    public int minCostConnectPointsPrim(int[][] points) {
        int n = points.length;
        boolean[] inMST = new boolean[n];

        var pq = new PriorityQueue<Pair<Integer, Integer>>(Comparator.comparingInt(Pair::getKey));
        pq.add(new Pair<>(0, 0));

        int mstCost = 0;
        int edgesUsed = 0;

        while (edgesUsed < n) {
            var curr = pq.poll();

            int weight = curr.getKey();
            int currNode = curr.getValue();

            // If node was already used in MST, move on
            if (inMST[currNode]) continue;

            inMST[currNode] = true;
            mstCost += weight;
            edgesUsed++;

            for (int nextNode = 0; nextNode < n; nextNode++) {
                if (!inMST[nextNode]) {
                    int nextWeight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                            Math.abs(points[currNode][1] - points[nextNode][1]);
                    pq.add(new Pair<>(nextNode, nextWeight));
                }
            }
        }
        return mstCost;
    }

    public class DisjointSet {
        int[] parent;
        int[] rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return false;

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                rank[rootX] += 1;
            }
            return true;
        }
    }
}
