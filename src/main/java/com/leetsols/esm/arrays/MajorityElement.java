package com.leetsols.esm.arrays;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Problem type: Array, Hash Table, Divide and Conquer, Sorting, Counting
 * Number: 169 Majority Element
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        var hashmap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!hashmap.containsKey(num)) {
                hashmap.put(num, 1);
            } else {
                hashmap.put(num, hashmap.get(num) + 1);
            }
        }

        LinkedHashMap<Integer, Integer> sortedMap = hashmap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, _) -> e1, LinkedHashMap::new));
        return sortedMap.sequencedEntrySet().getLast().getKey();
    }
}
