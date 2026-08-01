package com.leetsols.esm.graphs;

import java.util.Arrays;

public class CityWithSmallestNumberOfNeighbors {
    /*
     * Definition:
     * n - number of vertices
     * edges[i] = from[i], to[i], weight[i] - bidirectional weighted edge
     * distanceThreshold - max distance you are allowed to travel
     *
     * Goal:
     * - Find the city that can reach the smallest number of cities within the
     *   distanceThreshold
     *
     * Algorithm:
     * - Build the distance matrix. Initialize distance[i][j] = Infinity
     *                                         distance[i][i] = 0
     *   and fill direct edges.
     * - Run Floyd Warshall. For every intermediate node [k], check if going
     *   through [k] makes path [i -> j] shorter
     *   dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
     * -
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // Step 1: Initialize distance matrix
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 10001);
            dist[i][i] = 0;
        }

        // Step 2: Populate initial edge weights (Bidirectional)
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            dist[u][v] = weight;
            dist[v][u] = weight;
        }

        /*
         * Step 3: Floyd Warshall DP
         * - Once it is finished, it contains the shortest possible path distance from
         *   city [i] to city [j], taking any number of intermediate stops.
         *
         */
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Step 4: Count neighbors within threshold and find the result
        int minCityCount = n;
        int resultCity = -1;

        for (int i = 0; i < n; i++) {
            int reachableCities = 0;
            /*
             * Iterate through every possible destination city [j] and check the following:
             * - i != j -> It ignores the distance to itself
             * - dist[i][j] <= distanceThreshold -> it looks the shortest path stored in the
             *   dist[i][j]. If the total weight is less than or equal to the allowed threshold
             *   , it means city[j] is reachable within the budget.
             *
             */
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) reachableCities++;
            }

            if (reachableCities <= minCityCount) {
                minCityCount = reachableCities;
                resultCity = i;
            }
        }
        return resultCity;
    }
}
