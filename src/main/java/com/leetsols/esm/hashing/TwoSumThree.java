package com.leetsols.esm.hashing;

import java.util.HashMap;
import java.util.Map;

public class TwoSumThree {
    private final Map<Integer, Integer> map;

    public TwoSumThree() {
        map = new HashMap<>();
    }

    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int complement = value - num;
            if (map.containsKey(complement)) {
                if (num != complement) return true;

                if (entry.getValue() > 1) return true;
            }
        }
        return false;
    }
}
