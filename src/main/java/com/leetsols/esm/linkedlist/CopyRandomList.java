package com.leetsols.esm.linkedlist;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem type: Hash Table, Linked List
 * Number: 138. Copy List with Random Pointer
 */
public class CopyRandomList {
    private final Map<Node, Node> visited = new HashMap<>();

    /*
     * Each node contains random pointer to any node in the list or null;
     * - If the random pointer of any node points to the existing node in the list,
     *   you don't need to add the node in the list again.
     */
    public Node copyRandomList(Node head) {
        if (head == null) return head;

        if (visited.containsKey(head)) return visited.get(head);

        Node cloneNode = new Node(head.val);
        visited.put(head, cloneNode);
        cloneNode.next = copyRandomList(head.next);
        cloneNode.random = copyRandomList(head.random);

        return cloneNode;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
