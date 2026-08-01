package com.leetsols.esm.graphs;

import java.util.LinkedList;

public class PopulateNextRight {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /*
     * Description:
     * - Populate each node's next pointer to point to the right node on the same level.
     *   If there is no next right node, the next pointer should be the null.
     *
     * Algorithm:
     * Naive approach (Level Order Traversal):
     * - Traverse the nodes in the BFS order.
     * - For each node on the same level assign it's next pointer to the next pointer
     *   on the same level, if the last node on the level has been reached, make assign
     *   NULL to it.
     */
    public Node connect(Node root) {
        if (root == null) return null;

        var queue = new LinkedList<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                var current = queue.remove();

                current.next = (i < n - 1) ? queue.peek() : null;

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return root;
    }

    /*
     * Approach:
     * - Using the previously established next pointers
     * - 2 types of the next pointers:
     *  - Establish the next pointers between the 2 children of a given node,
     *    since both children are accessible via the same node
     *  - node.left.next = node.right
     *
     *  - Establish the pointers between nodes which have a different
     *    parent. To solve this problem we have the following idea:
     *    Move to the [N + 1] level when we are done establishing the next
     *    pointers for the level [N]. Since we have access to all nodes
     *    on the current level via [next] pointers, we can use them to
     *    establish the connection for the next level or the level containing
     *    their children.
     *  - So we establish the next pointers for a level [N] while we are still
     *    on level [N - 1].
     *  -
     *
     */
    public Node connectOne(Node root) {
        if (root == null) return root;

        Node leftMost = root;

        while (leftMost.left != null) {
            Node head = leftMost;

            // Move along the linked list on the current level as on during the previous
            // level the next links are already established
            while (head != null) {
                // Connection 1
                head.left.next = head.right;

                // Connection 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // Traverse along the next node in the list
                head = head.next;
            }
            // Move to the next level
            leftMost = leftMost.left;
        }
        return root;
    }
}
