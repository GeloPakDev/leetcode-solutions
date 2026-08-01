package com.leetsols.esm.arrays;

import java.util.HashMap;

public class PermutationInString {
    /*
     * Algorithm: Sliding Window
     * - Create a frequency map of the s1 to check for existence of letters
     *   during the iteration in the s2.
     * - As in the can be arbitrary number of the same letter, the currMap
     *   should also check for this frequency.
     */
    public boolean checkInclusion(String s1, String s2) {
        var map = new HashMap<Character, Integer>();
        for (char ch : s1.toCharArray()) map.put(ch, map.getOrDefault(ch, 0) + 1);

        var currMap = new HashMap<Character, Integer>();
        int k = s1.length();

        for (int i = 0; i < s2.length(); i++) {
            var currChar = s2.charAt(i);
            currMap.put(currChar, currMap.getOrDefault(currChar, 0) + 1);

            //Removing the character that's sliding out of the window
            if (i >= k) {
                char old = s2.charAt(i - k);
                currMap.put(old, currMap.getOrDefault(currChar, 0) - 1);
                if (currMap.get(old) == 0) currMap.remove(old);
            }

            if (map.equals(currMap)) return true;
        }
        return false;
    }
}
