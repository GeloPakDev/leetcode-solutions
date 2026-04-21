package com.leetsols.esm.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * Problem type: Array, Hash Table, Math, Design, Randomized
 * Number: 380. Insert Delete GetRandom O(1)
 */
public class RandomizedSet {
    private final Map<Integer, Integer> set;
    private final List<Integer> indexes;
    Random random = new Random();

    public RandomizedSet() {
        set = new HashMap<>();
        indexes = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (set.containsKey(val)) return false;

        set.put(val, indexes.size());
        indexes.add(indexes.size(), val);
        return true;
    }

    public boolean remove(int val) {
        if (set.containsKey(val)) return false;

        int lastElement = indexes.getLast();
        int idx = set.get(val);
        indexes.set(idx, lastElement);
        set.put(lastElement, idx);

        indexes.removeLast();
        set.remove(val);
        return true;
    }

    public int getRandom() {
        return indexes.get(random.nextInt(indexes.size()));
    }
}
