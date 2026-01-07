package com.leetsols.esm.strings;

/*
 * Problem type: Two Pointers, String
 * Number: 345. Reverse Vowels of String
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        String vowels = "aeiouAEIOU";
        char[] res = s.toCharArray();
        while (left < right) {
            if (vowels.indexOf(res[left]) != -1 &&
                    vowels.indexOf(res[right]) != -1) {
                swap(res, left, right);
                left++;
                right--;
            }
            if (vowels.indexOf(res[left]) == -1) left++;
            if (vowels.indexOf(res[right]) == -1) right--;
        }
        return String.valueOf(res);
    }

    public void swap(char[] input, int left, int right) {
        char ch = input[left];
        input[left] = input[right];
        input[right] = ch;
    }
}
