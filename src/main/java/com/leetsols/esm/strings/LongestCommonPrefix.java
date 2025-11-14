package com.leetsols.esm.strings;

public class LongestCommonPrefix {
    /*
     * Longest Common Prefix
     * - Binary Search method
     *  Choose the prefix with the least amount of characters
     *  Prefixes cannot be the shortest word
     *
     *  Put the low and high pointer
     *  Check the low -> middle and middle -> high with every word in the list
     *  If the middle matches -> increase low pointer++
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        int minLength = Integer.MAX_VALUE;
        //Find the string with min amount of chars
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }

        //Binary search starts here
        int low = 1;
        int high = minLength;

        while (low <= high) {
            int middle = (low + high) / 2;

            if (commonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean commonPrefix(String[] array, int middle) {
        String temp = array[0].substring(0, middle);
        for (int i = 1; i < array.length; i++) {
            if (!array[i].startsWith(temp)) return false;
        }
        return true;
    }
}
