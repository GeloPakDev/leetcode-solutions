package com.leetsols.esm.caching;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LFUCache {
    static class Node implements Comparable<Node> {
        int key;
        int value;
        int freq;
        long timestamp;

        public Node(int key, int value, int freq, long timestamp) {
            this.key = key;
            this.value = value;
            this.freq = freq;
            this.timestamp = timestamp;
        }


        @Override
        public int compareTo(Node o) {
            if (this.freq == o.freq) return Long.compare(this.timestamp, o.timestamp);
            return Integer.compare(this.freq, o.freq);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key == node.key;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(key);
        }
    }

    private int capacity;
    private HashMap<Integer, Node> cache;
    private PriorityQueue<Node> minHeap;
    private long timestamp;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.minHeap = new PriorityQueue<>();
        this.timestamp = 0;
    }

    /*
     * - If the current element does not exist in the map return -1;
     * - In other case, update frequency and timestamp of the node
     */
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        minHeap.remove(node);
        node.freq++;
        node.timestamp = timestamp++;
        minHeap.add(node);
        return node.value;
    }

    /*
     * If node exist in the cache, update its frequency in the heap
     * - Remove the node from the heap.
     * - Update the frequency value.
     * - Update the timestamp value.
     * - Put back the updated value into the heap.
     *
     * Else, check the size of the cache, if it exceeds the capacity
     * remove the LEAST FREQUENT ELEMENT from the minHeap. Otherwise,
     * add a new element to the heap.
     */
    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            minHeap.remove(node);
            node.value = value;
            node.freq++;
            node.timestamp = timestamp++;
            minHeap.add(node);
        } else {
            if (cache.size() >= capacity) {
                Node node = minHeap.poll();
                cache.remove(node.key);
            }
            Node node = new Node(key, value, 0, timestamp++);
            cache.put(key, node);
            minHeap.add(node);
        }
    }
}
