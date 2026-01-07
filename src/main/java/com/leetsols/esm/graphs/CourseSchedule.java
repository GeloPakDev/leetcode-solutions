package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Problem type: Depth-First Search, Breadth-First Search, Graph, Topological Sort
 * Number: 207. Course Schedule
 */
public class CourseSchedule {
    /*
     * - prerequisites[i] = [a[i], b[i]] indicates that you must take
     * course b[i] first if you want to take course a[i].
     * - pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * - to visit node a[i], you have to visit the node b[i] first
     * - [[1,0],[0,1]]
     * - To take course 1 you should have finished course 0, and to take course 0
     *   you should also have finished course 1. So it is impossible.
     * - Circular dependency
     * - Use of Topological Sort
     *
     * - Use the Kahn's algorithm:
     *  - Keep track of a number of incoming edges into each node (indegree)
     *  - Repeatedly visit the nodes with indegree of 0 and deleting all edges associated
     *    with them, thus leading to decrement the number of indegree for those nodes
     *    whose incoming edges are deleted. Continue this process until there will be
     *    no elements with indegree of 0.
     *  - If there is a cycle in the graph, the number of indegree nodes can never be 0
     *
     * Algorithm:
     * - Create array [indegree] to store the number of edges entering node x
     * - Create adjacency list by iterating over the prerequisites, where for
     *   every [prerequisite] in [prerequisites] we add [EDGE] from [prerequisite[1]]
     *   to [prerequisite[0]] and increment indegree of [prerequisite[1]] by 1
     * - Create a Queue of integers [q] and start BFS moving from leaf to parent nodes
     * - Iterate over all leaf nodes and push them into the queue
     * - Create a variable [nodesVisited]
     * - While the queue is not empty
     *  - Deque the first [node] from the queue
     *  - Increment [nodesVisited] by 1
     *  - For each neighbor of the node we decrement indegree[neighbor] by 1 to delete
     *    node -> neighbor edge
     *  - If indegree[neighbor] == 0, it means that neighbor behaves as a leaf node, so
     *    we push the neighbor on the queue
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        /*
         * - Create and adjacency list
         * - Count indegree for each node
         */
        for (int[] prerequisite : prerequisites) {
            adjacencyList.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        /*
         * - Find every node with In-Degree of 0 and put them into the Queue
         */
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        /*
         * Currently in the queue placed the nodes with In-Degree of 0, so it means that
         * edges that are coming from them in the direction of its neighbors should be
         * decreased
         */
        int visitedNodes = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visitedNodes++;

            for (int neighbor : adjacencyList.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }
        return visitedNodes == numCourses;
    }
}
