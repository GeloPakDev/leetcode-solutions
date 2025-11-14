package com.leetsols.esm.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Problem type: Hashtable, Sorting, Counting
 * Number: 1189 Maximum Number of Balloons
 */
public class MaxNumberOfBalloons {
    public static int maxNumberOfBalloons(String text) {
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < text.length(); i++) {
            var curr = text.charAt(i);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }

        List<Character> targetKeys = Arrays.asList('a', 'b', 'n', 'l', 'o');
        if (targetKeys.stream().
                allMatch(k -> map.containsKey(k) && map.get(k) > 0)) {
            if (map.get('l') < 2 && map.get('o') < 2) {
                return 0;
            }
        } else {
            return 0;
        }

        int minValue = map.entrySet().stream()
                .filter(entry ->
                        entry.getKey() == 'a' ||
                                entry.getKey() == 'b' ||
                                entry.getKey() == 'n')
                .map(Map.Entry::getValue)
                .min(Integer::compareTo)
                .orElse(0);

        int oLetter = map.entrySet().stream()
                .filter(entry -> entry.getKey() == 'o')
                .map(Map.Entry::getValue)
                .min(Integer::compareTo)
                .orElse(0);

        int lLetter = map.entrySet().stream()
                .filter(entry -> entry.getKey() == 'l')
                .map(Map.Entry::getValue)
                .min(Integer::compareTo)
                .orElse(0);

        if (oLetter % 2 != 0) {
            oLetter = oLetter - 1;
        }
        if (lLetter % 2 != 0) {
            lLetter = lLetter - 1;
        }

        var minTwo = Math.min(oLetter / 2, lLetter / 2);

        return Math.min(minValue, minTwo);
    }
}
