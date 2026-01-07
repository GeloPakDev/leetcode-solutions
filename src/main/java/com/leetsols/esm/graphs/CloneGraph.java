package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Problem type: Hash Table, Depth-First Search, Breadth-First Search, Graph
 * Number: 133. Clone Graph
 */
public class CloneGraph {
    /*
     * - Any undirected edge could be represented as 2 directional edges.
     * - To avoid being in the loop, keep track of the nodes that are placed in the
     *
     * Algorithm:
     * - Store references of all nodes that have been visited and cloned
     * - Key is a node of original graph, value is the node of cloned graph
     * - If a node already exists in the visited, return corresponding reference of the cloned node
     * - Else create a copy of it and put it in the hashmap
     * - Make a recursive call for the neighbors of the node
     * - Each recursive call will return a clone of a neighbor
     * - These clones of neighbors will be inserted into the cloned node
     */
    private final Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return node;

        // If node has been visited before
        if (visited.containsKey(node)) return visited.get(node);

        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
