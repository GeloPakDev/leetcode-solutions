package com.leetsols.esm.hashing;


import java.util.LinkedList;

/*
 * Problem type: Array, Hash Table, Linked List, Design, Hash Function
 * Number: 705 Design HashSet
 * Implementation using the LinkedList
 */
public class HashSetDesign {
    private final Bucket[] bucketArray;
    private final int keyRange;

    public HashSetDesign() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < this.keyRange; i++) {
            this.bucketArray[i] = new Bucket();
        }
    }

    protected int _hash(int key) {
        return (key % this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    public boolean contains(int key) {
        int bucketIndex = this._hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }

    static class Bucket {
        private final LinkedList<Integer> container;

        public Bucket() {
            container = new LinkedList<>();
        }

        public void insert(Integer key) {
            int index = this.container.indexOf(key);
            if (index == -1) {
                this.container.addFirst(key);
            }
        }

        public void delete(Integer key) {
            this.container.remove(key);
        }

        public boolean exists(Integer key) {
            int index = this.container.indexOf(key);
            return (index != -1);
        }
    }
}
