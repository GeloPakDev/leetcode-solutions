package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetonateBombs {
    /*
     * [x, y, z] coordinates
     * - Distance between the nodes -> d = sqrt((x1 - x2) ^ 2 * (y1 - y2) ^ 2)
     */
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = bombs.length;
        /*
         * - Go over the pairs of bombs to build the graph
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int xi = bombs[i][0];
                int yi = bombs[i][1];
                int ri = bombs[i][2];

                int xj = bombs[j][0];
                int yj = bombs[j][1];
                if ((long) ri * ri >= (long) (xi - xj) * (xi - xj) + (long) (yi - yj) * (yi - yj)) {
                    graph.computeIfAbsent(i, _ -> new ArrayList<>()).add(j);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int res = dfs(i, new HashSet<>(), graph);
            ans = Math.max(ans, res);
        }
        return ans;
    }

    public int dfs(int curr, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        visited.add(curr);
        int res = 1;
        for (int neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                res += dfs(neighbor, visited, graph);
            }
        }
        return res;
    }
}
