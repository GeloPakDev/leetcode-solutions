package com.leetsols.esm.hashing;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
 * Problem type: Hash Table, String, Sliding Window
 * Number: 424 Longest Repeating Character Replacement
 */
public class LongestRepeatingCharacter {
    /*
     * Let's revisit the condition of validity - a substring is valid if,
     * after at most k character replacements, all the letters in the
     * substring become the same.
     *
     * Valid substring of length [i] exists, then we can indeed say that
     * a valid substring of length [i−1] would also exist.
     *
     * Because of the presence of monotonic values, we should be able to
     * use binary search over the length of substrings
     *
     * Valid substring of given length exists or not?
     * We would need the element that appears the MAXIMUM NUMBER OF TIMES.
     * A frequency map can be helpful here. A frequency map stores a list of characters
     * with the frequency they appear in a particular window. Starting from the left
     * edge of the string, we build a frequency map of the window. As we move the window
     * of fixed length toward the right, the frequency of the new character added to the
     * window increases, and the frequency of the departing character decreases.
     *
     * Algorithm:
     * - Binary search
     *  - Single character would always be a valid substring -> low
     *  - Complete string can also be a valid substring      -> max
     *
     * ABABBA K = 2
     * - Replace the character that is less frequent in the window
     * - Aim is to match all characters in the window to the most common
     *   character in the window
     * - Maintain the Frequency Map of characters in the window
     * - To check that the window is valid:
     *  - Length of the window - count of the most frequent character in the window
     *  - Resulting value is the current amount of characters needed to replace
     *  - Window is valid when resulting value is <= K
     * - How to know which char is the most frequent?
     *  - Brute Force: As there are 26 chars in the array, time complexity will be O(26)
     * - At last wrap the checking equation into the Sliding Window technique
     * - Iterate from left -> right && equation == True
     *  - If !equation -> left++
     * -
     */
    public int characterReplacement(String s, int k) {
        var map = new HashMap<Character, Integer>();
        int res = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), 1 + map.getOrDefault(s.charAt(i), 0));
            while (((i - left + 1) - mostFrequentCharacter(map)) > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    public int mostFrequentCharacter(Map<Character, Integer> map) {
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getValue();
    }

    /*
     * How to check if a valid substring of a given length exists or not?
     * - Binary search + Sliding Window + Frequency Map.
     *   Because of existence of valid substring anywhere in the string, if a valid substring
     *   does exist, one of the sliding window positions will show it to us.
     * - Frequency Map in the Sliding Window
     *   We need an element that appears maximum number of times in the window,
     *   as the window moves to the right, frequency of a new character increases,
     *   and frequency of departing character decreases.
     *   Valid string is the one with number of the invalid characters <= k
     *
     * Algorithm:
     * - Binary Search (BS)
     *  - Initialize [lo] to 1 and [hi] to 1 more than length of string
     *  - Do the following until [left] and [right] meet each other
     *   - Find [mid]. Check if a valid string of length [mid] possible or not
     *   - If valid substring is found  -> move the BS to the right side -> lo = mid
     *   - If valid string is not found -> move the BS to the left side  -> hi = mid
     *  - In the end [lo] contains the max length that satisfies condition.
     *
     * - Valid String of given length
     *  - Frequency Map created to track max frequency of a char in the sliding window.
     *  - Pointer [right] add character frequency of the map.
     *  - If the size of the window exceeds the length it is time to move start one
     *    step further. Before start moves, decrease the frequency of the character
     *    pointed by it.
     *  - Store maximum frequency seen so far in maxFrequency variable
     *  - If the valid string is not found in the given range return false
     */
    public int characterReplacementTwo(String s, int k) {
        int lo = 1;
        int high = s.length() + 1;

        while (lo + 1 < high) {
            int mid = lo + (high - lo) / 2;

            if (canMakeValidSubstring(s, mid, k)) {
                lo = mid;
            } else {
                high = mid;
            }
        }
        return lo;
    }

    private boolean canMakeValidSubstring(String s, int mid, int k) {
        int[] frequencyMap = new int[26];
        int maxFrequency = 0;
        int start = 0;
        for (int right = 0; right < s.length(); right++) {
            frequencyMap[s.charAt(right) - 'A'] += 1;

            if (right + 1 - start > mid) {
                frequencyMap[s.charAt(start) - 'A'] -= 1;
                start += 1;
            }

            maxFrequency = Math.max(maxFrequency, frequencyMap[s.charAt(right) - 'A']);
            if (mid - maxFrequency <= k) return true;
        }
        return false;
    }

    public int characterReplacementThree(String s, int k) {
        HashSet<Character> allLetters = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            allLetters.add(s.charAt(i));
        }

        int maxLength = 0;
        for (Character ch : allLetters) {
            int start = 0;
            int count = 0;
            for (int right = 0; right < s.length(); right++) {
                if (s.charAt(right) == ch) {
                    count++;
                }

                while (!isWindowValid(start, right, count, k)) {
                    if (s.charAt(start) == ch) {
                        count--;
                    }
                    start++;
                }

                maxLength = Math.max(maxLength, right + 1 - start);
            }
        }
        return maxLength;
    }

    public boolean isWindowValid(int start, int end, int count, int k) {
        return end + 1 - start - count <= k;
    }

    /*
     * Initialize start = 0 and end = -1 -> Windows characters
     * Initialize frequencyMap
     * Initialize the size of the window as 0
     * Update the maxFrequency to indicate the maximum have seen so far
     * Validity formula: (end + 1 - start + maxFrequency) <= k
     * If the window is invalid, move start pointer
     *
     */
    public int characterReplacementFour(String s, int k) {
        int start = 0;
        int[] frequencyMap = new int[26];
        int maxFrequency = 0;
        int longestSubstringLength = 0;
        for (int right = 0; right < s.length(); right++) {
            int currentChar = s.charAt(right) - 'A';
            frequencyMap[currentChar] += 1;
            maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);

            boolean isValid = (right + 1 - start - maxFrequency) <= k;
            if (!isValid) {
                int outgoingChar = s.charAt(start) - 'A';
                frequencyMap[outgoingChar] -= 1;
                start += 1;
            }
            longestSubstringLength = right + 1 - start;
        }
        return longestSubstringLength;
    }

    public int characterReplacementFinal(String s, int k) {
        int[] count = new int[26];
        int start = 0;
        int maxCount = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            maxCount = Math.max(maxCount, count[s.charAt(i) - 'A'] += 1);
            while (i - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }
}
