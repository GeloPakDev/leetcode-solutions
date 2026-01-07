package com.leetsols.esm.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Problem type: Array, Hash Table, String
 * Number: 249. Group Shifted Strings
 */
public class GroupShiftedStrings {
    /*
     * Right shift -> successive letter of the English alphabet ("abc" -> "bcd", "ace" -> "bdf")
     * Left shift  -> preceding letter of the English alphabet ("bcd" -> "abc", "yza" -> "xyz")
     * - Group together strings from the same shifting sequence
     *
     * Example:
     * ["abc","bcd","acef","xyz","az","ba","a","z"]
     * [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
     *
     * Algorithm:
     * - As a KEY is sequence of differences between adjacent characters
     *   with %26 to handle the circular shift (wrapping) problem
     */
    public List<List<String>> groupStrings(String[] strings) {
        var map = new HashMap<String, List<String>>();
        for (String string : strings) {
            String key = getKey(string);
            map.computeIfAbsent(key, _ -> new ArrayList<>()).add(string);
        }
        return new ArrayList<>(map.values());
    }

    /*
     * In order to handle the letters in the circle specific formula is:
     * - (diff + 26) % 26
     *
     * Handling the "Backward" Wrap (z to a)
     *  "az": 'z' - 'a' = 122 − 97 = 25.
     *  "ba": 'a' - 'b' = 97  − 98 = −1.
     *
     * Mathematically, a shift of −1 is the same as a shift of +25 in a 26-letter alphabet.
     * If you just used the raw difference, your code would think these are different patterns.
     * Adding 26 and taking the modulo converts that negative distance into its positive "circular"
     * equivalent.
     * (−1 + 26)(mod26) = 25 (mod26) = 25
     *
     * The % operator can return a negative result if the input is negative (-1 % 26 is -1).
     * By adding 26 first, we ensure the number is positive before we apply the modulo.
     * This "normalizes" the distance so that it always falls between 0 and 25.
     */
    public String getKey(String one) {
        if (one.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < one.length(); i++) {
            int diff = (one.charAt(i) - one.charAt(i - 1) + 26) % 26;
            sb.append(diff).append(",");
        }
        return sb.toString();
    }
}
