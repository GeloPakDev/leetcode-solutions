package com.leetsols.esm.hashing;

import java.util.HashMap;
import java.util.HashSet;

/*
 * Problem type: Hashtable, String, Counting
 * Number: 1941 Check if all characters have equal number of occurrences
 */
public class EqualOccurrences {
    public static boolean areOccurrencesEqual(String s) {
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            var curr = s.charAt(i);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }

        var set = new HashSet<>(map.values());
        return set.size() <= 1;
    }
}
