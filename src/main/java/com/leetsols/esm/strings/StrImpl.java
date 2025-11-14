package com.leetsols.esm.strings;

public class StrImpl {
    /*
     * - Return the index of the first occurrence of substring in the string
     * - Return -1 if substring is not part of the string
     *
     * - If haystack and needle letter matches move both pointers
     * - If the haystack letter doesn't match the needle letter, make the needlePointer = 0
     * - If the length of the needle letter exceeds -> substring matches with string -> return the index of the first occurrence
     */
    public static int strStr(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();
        for (int windowStart = 0; windowStart <= n - m; windowStart++) {
            for (int i = 0; i < m; i++) {
                if (needle.charAt(i) != haystack.charAt(windowStart + i)) {
                    break;
                }
                if (i == m - 1) {
                    return windowStart;
                }
            }
        }
        return -1;
    }
}