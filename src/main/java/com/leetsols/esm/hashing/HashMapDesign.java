package com.leetsols.esm.hashing;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Array, Hash Table, Linked List, Design, Hash Function
 * Number: 706 Design HashSet
 */
public class HashMapDesign {
    private final List<List<Pair<Integer, Integer>>> map;
    private final int keyRange;

    public HashMapDesign() {
        keyRange = 769;
        map = new ArrayList<>(keyRange);
        for (int i = 0; i < keyRange; i++) {
            map.add(new ArrayList<>());
        }
    }

    public int _hash(int key) {
        return key % keyRange;
    }

    public void put(int key, int value) {
        int hashIndex = _hash(key);
        List<Pair<Integer, Integer>> list = map.get(hashIndex);
        for (Pair<Integer, Integer> integerIntegerPair : list) {
            if (integerIntegerPair.key == key) {
                integerIntegerPair.value = value;
            }
        }
        list.add(new Pair<>(key, value));
    }

    public int get(int key) {
        int hashIndex = _hash(key);
        var list = map.get(hashIndex);
        for (Pair<Integer, Integer> integerIntegerPair : list) {
            if (integerIntegerPair.key == key) {
                return integerIntegerPair.value;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int hashIndex = _hash(key);
        var list = map.get(hashIndex);
        list.removeIf(pair -> pair.key == key);
    }

    static class Pair<E, V> {
        E key;
        V value;

        public Pair(E key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
