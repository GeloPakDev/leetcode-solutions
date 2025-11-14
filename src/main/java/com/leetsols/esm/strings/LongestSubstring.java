package com.leetsols.esm.strings;

import java.util.HashMap;
import java.util.HashSet;

/*
 * Problem type: Hashtable, String, Sliding Window
 * Number: 3 Longest Substring Without Repeating Characters
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        var set = new HashSet<Character>();
        int left = 0, res = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            while (set.contains(current)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(current);
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int findLongestSubstring(String s, int k) {
        var map = new HashMap<Character, Integer>();
        int left = 0;
        int answer = 0;
        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
            /*
             * As a constraint will be used a map.size as it indicates the number of unique characters
             */
            while (map.size() > k) {
                char remove = s.charAt(left);
                map.put(remove, map.getOrDefault(curr, 0) - 1);
                /*
                 * If the current frequency is 0, we should remove the pointer
                 */
                if (map.get(remove) == 0) {
                    map.remove(curr);
                }
                /*
                 * Seize the window
                 */
                left++;
            }
            /*
             * As an answer we should provide the length of the longest substring
             */
            answer = Math.max(answer, right - left + 1);
        }
        return answer;
    }
}
