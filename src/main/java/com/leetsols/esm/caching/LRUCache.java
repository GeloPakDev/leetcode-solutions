package com.leetsols.esm.caching;

import java.util.HashMap;
import java.util.Map;

/*
 * Implementation of the LRU cache using the:
 * - Doubly Linked List and Hashmap.
 * - Node -> node in the doubly linked list
 * - addNode -> add a node right after the head node.
 * - removeNode -> removes a node from a linked list
 * - moveToHead -> moves a node to the head of a linked list
 * - popTail -> removes and returns the tail node
 * - get -> retrieves the node for a given key, moves the accessed node to
 *          the head and returns a value.
 * - put -> add a key-value pair to the cache. If the value already exists
 *          update a value and move the node to the head. If adding a new
 *          value exceeds the capacity, evicts the least recently used node
 */
public class LRUCache {
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    /*
     * - Assign the references of next and prev node
     *  - [prev] of [nodeToBeAdded] should be the current head
     *  - [next] of [nodeToBeAdded] should be the current next of head
     * - Change direction of [prev] node on the next node of head
     *  - [next] of [head.prev] will be the [nodeToBeAdded]
     * - Change [next] direction of the head
     *  - [next] of [head] will be the [nodeToBeAdded]
     */
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /*
     * - Preserve the [prev] and [next] references of the nod to be deleted
     */
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    public Node popTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    /*
     * Each time an element is accessed, it's recently status should be updated
     */
    public int getKey(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    /*
     * If the node doesn't exist
     * - Add it to the HashSet
     * - Add the node into doubly linked list
     * - If the node to be added exceeds its capacity, pop the last one from the
     *   tail and remove it from the hashmap
     */
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            if (cache.size() > capacity) {
                Node tail = popTail();
                cache.remove(tail.key);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
