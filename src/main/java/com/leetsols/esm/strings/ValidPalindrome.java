package com.leetsols.esm.strings;

/*
 * Problem type: Two Pointers, String, Dynamic Programming
 * Number: 125 Valid Palindrome, 680 Valid Palindrome
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }

        int start = 0;
        int last = s.length() - 1;
        while (start <= last) {
            char currFirst = s.charAt(start);
            char currLast = s.charAt(last);
            if (!Character.isLetterOrDigit(currFirst)) {
                start++;
            } else if (!Character.isLetterOrDigit(currLast)) {
                last--;
            } else {
                if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
                    return false;
                }
                start++;
                last--;
            }
        }
        return true;
    }

    /*
     * If string can be a palindrome after ond deletion, the deletion must be
     * one of these mismatched characters.
     *
     * Algorithm:
     * - Somewhere in the string, mismatch will be occurred, we must use ONE
     *   attempt for allowed deletion by checking which character can be deleted
     *   If none of the calls to checkPalindrome is true, we cannot replace the character
     *   2 options are given:
     *   - Delete character at the end and check the range   (start, end - 1)
     *   - Delete character at the start and check the range (start + 1, end)
     */
    public boolean validPalindrome(String s) {
        int low = 0;
        int end = s.length() - 1;
        while (low < end) {
            if (s.charAt(low) != s.charAt(end)) {
                return checkPalindrome(s, low, end - 1) || checkPalindrome(s, low + 1, end);
            }
            low++;
            end--;
        }
        return true;
    }

    public boolean checkPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
