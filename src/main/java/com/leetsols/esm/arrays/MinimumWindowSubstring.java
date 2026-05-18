package com.leetsols.esm.arrays;

import java.util.HashMap;

public class MinimumWindowSubstring {
    /*
     * Description:
     * - Given the following strings
     *  - [s] of length [m]
     *  - [t] of length [n]
     * - Return the minimum window substring of [s] such that every character in [t]
     *   including the duplicates is included in the window.
     *
     * Algorithm:
     * - Expand the right pointer of the window until all characters are covered.
     *  - To indicate that all letters are covered, every time the letter is seen
     *    , check that [counter <= length of the t].
     *  - If a letter doesn't exist in the [t] increase the window.
     *  - If a letter exist in the [t]
     *   - Decrease the counter of the letter and increase the total count which is
     *     used to indicate the length of the [t]
     *  - If a letter
     *   -
     * - Once all characters are covered, move the left pointer and ensure that all
     *   characters are still covered to minimize the subarray size.
     *  - If removing the left character makes substring invalid, we will expand
     *    the window until an extra valid character enters into the substring.
     */
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        // 1. Make a frequency Map
        var freqMap = new HashMap<Character, Integer>();
        for (char ch : t.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // 2. Number of unique characters that should be preserved in the window, preserved at [t]
        int required = freqMap.size();

        // 3. Left and Right pointers
        int left = 0;
        int right = 0;

        /*
         * 4. Formed
         * This counter is used to keep track of how many unique characters are currently
         * present in the window in its desired frequency. e.g. if [t] AABC, window
         * should have 2 A's, 1 B and 1 C.
         */
        int formed = 0;

        // 5. Keeps the count of all the unique characters in the window
        var currWindowFreqMap = new HashMap<Character, Integer>();

        // 6. Answer list in the form {window length, left, right}
        int[] ans = {-1, 0, 0};

        while (right < s.length()) {
            // Add one character from the right to the window
            char ch = s.charAt(right);
            currWindowFreqMap.put(ch, currWindowFreqMap.getOrDefault(ch, 0) + 1);

            /*
             * If the frequency of the current ADDED character equals to the desired count
             * in [t], then increment the formed count by 1
             */
            if (freqMap.containsKey(ch) &&
                    currWindowFreqMap.get(ch).intValue() == freqMap.get(ch).intValue()) formed++;

            /*
             * Try and contract the window till the point where it ceases to be 'desirable'
             */
            while (left <= right && formed == required) {
                ch = s.charAt(left);
                // Save the smallest window until now
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                // The character at the position pointed by the 'left' pointer
                // is no longer a part of the window.
                currWindowFreqMap.put(ch, currWindowFreqMap.get(ch) - 1);
                if (freqMap.containsKey(ch) &&
                        currWindowFreqMap.get(ch) < freqMap.get(ch)) formed--;
                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
