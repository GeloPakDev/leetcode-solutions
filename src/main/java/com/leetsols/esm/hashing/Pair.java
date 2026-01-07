package com.leetsols.esm.hashing;

public class Pair<E, V> {
    E key;
    V value;

    public Pair(E key, V value) {
        this.key = key;
        this.value = value;
    }

    public E getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}