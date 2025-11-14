package com.leetsols.esm.strings;

/*
 * Problem type: Two Pointers, String
 * Number: 344 Reverse String
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            var temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
