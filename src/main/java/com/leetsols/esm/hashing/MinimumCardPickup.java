package com.leetsols.esm.hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Problem type: Array, Hashtable, Sliding Window
 * Number: 2260 Minimum Consecutive Cards to Pick Up
 */
public class MinimumCardPickup {
    public static int minCardPickup(int[] cards) {
        Map<Integer, Integer> dic = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < cards.length; i++) {
            int num = cards[i];
            if (dic.containsKey(num)) {
                ans = Math.min(ans, i - dic.get(num) + 1);
            }
            dic.put(num, i);
        }

        if (ans == Integer.MAX_VALUE) {
            return -1;
        }

        return ans;
    }

    public static int minimumCardPickup(int[] cards) {
        var map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < cards.length; i++) {
            var key = cards[i];
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(i);
        }

        List<Integer> result = new ArrayList<>();

        map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> {
                    List<Integer> list = entry.getValue();
                    Collections.sort(list);
                    int minDiff = Integer.MAX_VALUE;

                    for (int i = 1; i < list.size(); i++) {
                        int diff = list.get(i) - list.get(i - 1) + 1;
                        minDiff = Math.min(minDiff, diff);
                    }
                    result.add(minDiff);
                });

        return result.stream()
                .min(Integer::compareTo)
                .orElse(-1);
    }
}
