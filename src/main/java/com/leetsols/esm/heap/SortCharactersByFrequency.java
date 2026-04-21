package com.leetsols.esm.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {
    /*
     * Sort the string in the decreasing order based on the frequency of the characters
     *
     * - Make a frequency map using the hashtable
     * - Create a Max Heap with Entry<Number, Frequency>, comparing the pairs based on the frequency
     * - Iterate over the map and put the Entry to the Heap
     * - Extract the entries from the Heap using the
     */
    public static String frequencySort(String s) {
        StringBuilder res = new StringBuilder();
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

        var heap = new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> b.getValue() - a.getValue());
        heap.addAll(map.entrySet());

        while (!heap.isEmpty()) {
            var entry = heap.poll();
            int temp = 0;
            while (temp < entry.getValue()) {
                res.append(entry.getKey());
                temp++;
            }
        }
        return res.toString();
    }
}
