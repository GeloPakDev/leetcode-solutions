package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class HierholzerAlgorithm {
    /*
     * Finds the Eulerian path/circuit in a directed graph
     */
    public static List<Integer> findEulerianPath(Map<Integer, List<Integer>> graph, int startNode) {
        var adj = new HashMap<Integer, List<Integer>>();
        for (var entry : graph.entrySet()) {
            adj.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        var stack = new Stack<Integer>();
        var res = new ArrayList<Integer>();

        // Step 1. Push initial node to the execution stack
        stack.push(startNode);

        // Step 2. Loop until we have fully backtracked and the stack is empty
        while (!stack.isEmpty()) {
            int currNode = stack.pop();

            // Case A: If there are still unused edges leaving the node
            if (adj.containsKey(currNode) && !adj.get(currNode).isEmpty()) {
                var edges = adj.get(currNode);
                // take the next node
                int nextNode = edges.removeLast();

                // Move forward
                stack.push(nextNode);
            }
            // Case B: Current node doesn't have any outgoing edges left.
            else {
                // Add it to the final list
                res.add(stack.pop());
            }
        }
        // Step 3: Because we have built the list backwards, reverse it
        Collections.reverse(res);
        return res;
    }
}

