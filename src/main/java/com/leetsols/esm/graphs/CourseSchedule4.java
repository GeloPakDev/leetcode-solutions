package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedule4 {
    /*
     * DFS Approach:
     * - Build the graph, as this is the directed graph, no bidirectional entry is required.
     * - Iterate over each of the queries:
     *  - for each query check does the path exists between the [u] and [v].
     *  - use the dfs to check does the path exits.
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        var graph = buildGraph(prerequisites);

        var res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            int src = query[0];
            int dst = query[1];
            res.add(dfs(src, dst, new HashSet<>(), graph));
        }
        return res;
    }

    public static List<Boolean> checkIfPrerequisiteBFS(int numCourses, int[][] prerequisites, int[][] queries) {
        var graph = buildGraph(prerequisites);
        System.out.println(graph);

        var res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];

            res.add(bfs(u, v, new HashSet<>(), new LinkedList<>(), graph));
        }
        return res;
    }

    /*
     * Topological Sort:
     * - To adapt the Kahn's algorithm, we need to identify the prerequisites for the
     *   node, for example to move from node [u] to node [v], all prerequisites of
     *   node [u] will be added to the node [v].
     * - By the end of the process each node will have complete list of all nodes that
     *   must be visited before, for example when we will query (c, f), it is enough to
     *   check does the [c] contains in the list of prerequisites of [f]
     */
    public static List<Boolean> checkIfPrerequisiteTopoSort(int numCourses, int[][] prerequisites, int[][] queries) {
        var graph = new HashMap<Integer, List<Integer>>();
        var indegree = new int[numCourses];

        /*
         * - Create an Adjacency List.
         * - Increment number of incoming edges of node [v]
         */
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, _ -> new ArrayList<>()).add(v);
            indegree[v]++;
        }

        // All nodes with in-degree of 0, can be processed first
        var queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        var nodePrerequisites = new HashMap<Integer, Set<Integer>>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            /*
             * For each neighbor of the node, add the current node as prerequisite
             */
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {

                nodePrerequisites.computeIfAbsent(neighbor, k -> new HashSet<>()).add(node);
                /*
                 * Look at the current node. Take every single prerequisite that was required to unlock node,
                 * and copy/add them into the prerequisite set for neighbor
                 */
                for (int prerequisite : nodePrerequisites.getOrDefault(node, new HashSet<>())) {
                    nodePrerequisites.get(neighbor).add(prerequisite);
                }
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        var res = new ArrayList<Boolean>();
        for (int[] query : queries) {
            res.add(nodePrerequisites.getOrDefault(query[1], new HashSet<>()).contains(query[0]));
        }
        return res;
    }

    private static boolean bfs(int u, int v, HashSet<
            Integer> visited, LinkedList<Integer> queue, HashMap<Integer, List<Integer>> graph) {
        queue.add(u);
        visited.add(u);

        while (!queue.isEmpty()) {
            var curr = queue.poll();

            for (int neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
                if (neighbor == v) return true;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    public boolean dfs(int node, int destination, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        if (node == destination) return true;
        visited.add(node);

        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, destination, visited, graph)) return true;
            }
        }
        return false;
    }

    private static HashMap<Integer, List<Integer>> buildGraph(int[][] prerequisites) {
        var graph = new HashMap<Integer, List<Integer>>();
        for (int[] prerequisite : prerequisites) {
            graph.putIfAbsent(prerequisite[0], new ArrayList<>());
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }
        return graph;
    }
}
