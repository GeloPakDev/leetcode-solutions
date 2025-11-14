package com.leetsols.esm.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * Problem type: Hash Table, String, Sorting
 * Number: 242 Valid Anagram
 *
 * Problem type: Array, Hash Table, String, Sorting
 * Number: 49 Group Anagrams
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return false;
        }
        var map = new HashMap<Character, Integer>();
        /*
         * On the first iteration fill the Hashtable within the quantity of chars of the word
         */
        for (int i = 0; i < s.length(); i++) {
            var curr = s.charAt(i);
            if (!map.containsKey(curr)) {
                map.put(curr, 1);
            } else {
                map.replace(curr, map.get(curr) + 1);
            }
        }
        /*
         * On the second iteration check does this char contained in the HashMap
         */
        for (int i = 0; i < t.length(); i++) {
            var curr = t.charAt(i);
            if (map.containsKey(curr) && map.get(curr) > 0) {
                map.replace(curr, map.get(curr) - 1);
            } else {
                return false;
            }
        }
        int sum = map.values().stream().mapToInt(Integer::intValue).sum();
        return sum == 0;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();

        var map = new HashMap<String, List<String>>();

        /*
         * Fill hashtable with letters from string
         */
        int[] array = new int[26];
        for (String s : strs) {
            Arrays.fill(array, 0);
            for (char ch : s.toCharArray()) array[ch - 'a']++;

            /*
             * Create a key to insert into the HashMap
             */
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                temp.append("#");
                temp.append(array[i]);
            }

            String key = String.valueOf(temp);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
