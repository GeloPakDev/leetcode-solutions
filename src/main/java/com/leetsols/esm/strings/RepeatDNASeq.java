package com.leetsols.esm.strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatDNASeq {
    // Since there are only 4 possible characters, BASE = 5 is perfectly sufficient.
    // Length is always 10, so 5^10 fits easily into a standard 32-bit integer.
    // Because it fits in an integer, we don't even need a MODULO, avoiding hash collisions!
    private static final int BASE = 5;

    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        // If the string is shorter than 10 characters, no 10-letter sequence can repeat.
        if (len < 10) {
            return new ArrayList<>();
        }

        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        //   Map <Character><integer> encoding new HashMap
        // Map DNA characters to simple unique digits 1-4
        int[] charMap = new int[256];
        charMap['A'] = 1; //encoding.put['A'] -  2 bit
        charMap['C'] = 2; //encoding.put['C']
        charMap['G'] = 3;
        charMap['T'] = 4;

        // Calculate the highest power place-value: BASE^(m-1) where m = 10.
        // This represents the weight of the leftmost character in our window.
        int highestPower = 1;
        for (int i = 0; i < 9; i++) {
            highestPower *= BASE;
        }

        // 1. Compute the hash of the very first window (first 10 characters)
        int currentHash = 0;
        for (int i = 0; i < 10; i++) {
            currentHash = currentHash * BASE + charMap[s.charAt(i)];
        }

        // Add the first substring to our tracker
        seen.add(s.substring(0, 10));

        // 2. Slide the window across the rest of the string
        for (int i = 1; i <= len - 10; i++) {
            char outChar = s.charAt(i - 1);      // Character leaving the window
            char inChar = s.charAt(i + 10 - 1);  // Character entering the window

            // Rabin-Karp Rolling Hash Step:
            // - Remove the leading character's contribution
            // - Multiply by BASE to shift all remaining characters left
            // - Add the new trailing character
            currentHash = (currentHash - charMap[outChar] * highestPower) * BASE + charMap[inChar];

            String currentSeq = s.substring(i, i + 10);

            // If we've seen this exact sequence before, it's a duplicate.
            // Using a Set for 'repeated' automatically prevents adding duplicates of duplicates.
            if (!seen.add(currentSeq)) {
                repeated.add(currentSeq);
            }
        }

        return new ArrayList<>(repeated);
    }

    /*
     * Algorithm:
     * - Iterate over the string and check each substring of length 10
     *   as sliding window in the seen set. Seen set is used to store
     *   all the possible substrings from input string, if the substring
     *   is already contained in the [seen] set, add it to the [out] set
     *   , it indicates the final result where DNA subsequence appeared
     *   more than once.
     */
    public List<String> findRepeatedDnaSequencesBrute(String s) {
        var len = 10;
        var n = s.length();
        var seen = new HashSet<String>();
        var out = new HashSet<String>();

        for (int i = 0; i < n - len - 1; i++) {
            var temp = s.substring(i, n + len);
            if (seen.contains(temp)) out.add(temp);
            seen.add(temp);
        }
        return new ArrayList<>(out);
    }
}
