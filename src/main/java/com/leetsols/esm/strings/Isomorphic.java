package com.leetsols.esm.strings;

public class Isomorphic {
    /*
     * Isomorphic - characters in S can be replaced to get T
     * - All occurrences of a character must be replaced with another character while preserving
     * the order of characters.
     * - No 2 characters may map to the same character.
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] sMap = new int[256];
        int[] tMap = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char one = s.charAt(i);
            char two = t.charAt(i);

            if (sMap[one] != tMap[two]) return false;

            sMap[one] = i + 1;
            tMap[two] = i + 1;
        }
        return true;
    }
}
