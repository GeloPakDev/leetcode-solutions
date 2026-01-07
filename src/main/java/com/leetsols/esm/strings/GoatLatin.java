package com.leetsols.esm.strings;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem type: String
 * Number: 824. Goat Latin
 */
public class GoatLatin {
    private static final Set<Character> vowels =
            new HashSet<>() {
                {
                    add('a');
                    add('e');
                    add('i');
                    add('o');
                    add('u');
                    add('A');
                    add('E');
                    add('I');
                    add('O');
                    add('U');
                }
            };

    /*
     * Description:
     * - If a word starts with a vowel, append 'ma' to the end of the word
     * - If a word starts with consonant, remove the first letter and append it to the end,
     *   then add 'ma'
     * - Add one letter 'a' to the end of each word based on its index
     *
     * Algorithm:
     * - Instead of iteration based on the index of each string to add number of 'a'
     *   create 'suff' string and on each iteration add one 'a' character to it, then
     *   each time append the string to the end of each word
     * - Instead of creating an array of vowels to increase a retrieval speed to O(1),
     *   then check the if the words starts with consonant or not, if starts with consonant,
     *   take the substring starting from the second character and appending the first one to
     *   the end.
     */
    public String toGoatLatin(String sentence) {
        String suff = "";
        StringBuilder sb = new StringBuilder();

        for (String tok : sentence.split(" ")) {
            suff += "a";
            if (!vowels.contains(tok.charAt(0))) {
                tok = tok.substring(1) + tok.charAt(0);
            }
            sb.append(tok).append("ma").append(suff).append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
