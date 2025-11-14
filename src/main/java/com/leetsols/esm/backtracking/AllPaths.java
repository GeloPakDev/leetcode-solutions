package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Problem type: Backtracking, Depth-First Search, Breadth-First Search, Graph
 * Number: 797 All Paths From Source to Target
 */
public class AllPaths {
    private int target;
    private int[][] graph;
    private List<List<Integer>> results;

    public void backtrack(int currNode, LinkedList<Integer> path) {
        if (currNode == this.target) {
            this.results.add(new ArrayList<>(path));
            return;
        }
        /*
         * - Enumerate through ALL the NEIGHBOR nodes of the current node
         * - Appending the neighbor node to the path
         * - Call backtrack to explore in depth
         * - Remove previous choice, try the next choice
         */
        for (int nextNode : this.graph[currNode]) {
            path.addLast(nextNode);
            this.backtrack(nextNode, path);
            path.removeLast();
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.target = graph.length - 1;
        this.graph = graph;
        this.results = new ArrayList<>();

        LinkedList<Integer> path = new LinkedList<>();
        path.addLast(0);

        this.backtrack(0, path);
        return this.results;
    }
}
