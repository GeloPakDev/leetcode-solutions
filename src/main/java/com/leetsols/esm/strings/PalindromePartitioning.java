package com.leetsols.esm.strings;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    private Integer[][] memoCuts;
    private Boolean[][] memoPalindrome;

    public int minCut(String s) {
        memoCuts = new Integer[s.length()][s.length()];
        memoPalindrome = new Boolean[s.length()][s.length()];
        return findMinCut(s, 0, s.length() - 1, s.length() - 1);
    }

    public int findMinCut(String s, int start, int end, int minCut) {
        // Base case: No cut is needed for an empty string or non-palindrome
        if (start == end || isPalindrome(s, start, end)) return 0;

        if (memoCuts[start][end] != null) return memoCuts[start][end];

        for (int currIdx = start; currIdx <= end; currIdx++) {
            // Find the result for substring (start, currIdx) if it is a palindrome
            if (isPalindrome(s, start, currIdx)) {
                minCut = Math.min(minCut, 1 + findMinCut(s, currIdx + 1, end, minCut));
            }
        }
        return memoCuts[start][end];
    }

    /*
     * Algorithm:
     * - Backtracking:
     *  - Choose: Choose the potential candidate, potential candidates
     *    are all substrings that can be generated from the given string
     *  - Constraint: String must be a palindrome.
     *  - Goal: End of the string has been reached
     *
     * - Recurrence relation:
     *  - Generate all possible substring beginning at index [start],
     *    index [end] increments from the start until the end of the string
     *  - For each generated substring, check if it is palindrome.
     *  - If the substring is palindrome, it is potential candidate, add it to
     *    the currentList, and perform DFS on the remaining substring. If the
     *    current substring start from the idx end, end + 1 will be the start idx
     *    for the next recursive call
     *  - Backtrack if the [start] idx is greater than or equal to the string
     *    and the result to the current list
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), res);
        return res;
    }

    public void backtracking(String s, int startIdx, List<String> currentPartition, List<List<String>> result) {
        // Base case: If we have proceeded entire string, we found a valid partition
        if (startIdx == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        // Explore all possible end positions for the current substring
        for (int endIdx = startIdx + 1; endIdx <= s.length(); endIdx++) {
            // Only proceed if the slice from the startIdx to endIdx - 1 is a palindrome
            if (isPalindrome(s, startIdx, endIdx - 1)) {
                // Choose: Extract the substring and add it to the path
                String substring = s.substring(startIdx, endIdx);
                currentPartition.add(substring);

                // Explore: Move deeper into the tree with remaining string
                backtracking(s, endIdx, currentPartition, result);

                // Backtrack: Remove the last added substring to try different size slice
                currentPartition.removeLast();
            }
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }

    private boolean isPalindromeForDP(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        // check for results in memoPalindrome
        if (memoPalindrome[start][end] != null) {
            return memoPalindrome[start][end];
        }
        return (
                memoPalindrome[start][end] = (s.charAt(start) == s.charAt(end)) &&
                        isPalindrome(s, start + 1, end - 1)
        );
    }
}
