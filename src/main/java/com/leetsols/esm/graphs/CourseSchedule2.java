package com.leetsols.esm.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        var graph = makeGraph(numCourses, prerequisites);
        var visiting = new HashSet<Integer>();
        var visited = new HashSet<Integer>();
        var stack = new ArrayDeque<Integer>();

        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (!dfs(node, visiting, visited, stack, graph)) return new int[]{};
            }
        }

        int[] res = new int[numCourses];
        int i = 0;
        while (!stack.isEmpty()) res[i++] = stack.pop();
        return res;
    }

    public boolean dfs(int node, Set<Integer> visiting, Set<Integer> visited, Deque<Integer> stack, Map<Integer, List<Integer>> graph) {
        visiting.add(node);

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (visiting.contains(neighbor)) return false;

            if (!visited.contains(neighbor)) {
                if (!dfs(neighbor, visiting, visited, stack, graph)) return false;
            }
        }
        visiting.remove(node);
        visited.add(node);
        stack.push(node);
        return true;
    }

    public Map<Integer, List<Integer>> makeGraph(int numCourses, int[][] prerequisites) {
        var map = new LinkedHashMap<Integer, List<Integer>>();
        for (int i = 0; i < numCourses; i++) map.put(i, new ArrayList<>());

        for (int[] pre : prerequisites) map.get(pre[1]).add(pre[0]);
        return map;
    }
}
