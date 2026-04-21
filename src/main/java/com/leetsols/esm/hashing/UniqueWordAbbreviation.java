package com.leetsols.esm.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UniqueWordAbbreviation {
    private final Map<String, HashSet<String>> map;

    /*
     * Abbreviation of a word is a concatenation of its first letter, the number of characters between
     * the first and last letter, and its last letter. If a word has only two characters, then it is
     * an abbreviation of itself.
     */
    public UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            var abbr = makeAbbr(s);
            if (!map.containsKey(abbr)) {
                map.put(abbr, new HashSet<>());
            }
            map.get(abbr).add(s);
        }
    }

    /*
     * There is no word in dictionary whose abbreviation is equal to word's abbreviation. -> match
     * For any word in dictionary whose abbreviation is equal to input word's abbreviation, that word and word are the same.
     * If (map.contains(input.abbr)) -> input.equals(any word from the associated list for abbr) -> true
     * input array: deer, door, cake, card
     * - d2r -> deer, door
     * - c2e -> cake
     * - c2d -> card
     *
     * ["dear"], ["cart"], ["cane"], ["make"], ["cake"]
     * - dear -> d2r -> deer != dear, door != dear -> false
     * - cart -> c2t -> entry doesn't exist        -> true
     * - cane -> c2e -> cake != cane               -> false
     * - make -> m2e -> entry doesn't exist        -> true
     * - cake -> c2e -> cake == cake               -> true
     *
     * input array: deer, door, cake, card
     * - d2r -> deer, door
     * - c2e -> cake
     * - c2d -> card
     *
     * ["dear"],["door"],["cart"],["cake"]
     * - dear -> d2r -> dear != deer, dear != door -> false
     * - door -> dr4 -> door != deer, door == door -> true
     * - cart -> c2t -> entry doesn't exist        -> true
     * - cake -> c2e -> cake == cake               -> true
     *
     * If above is yes, then it can only be unique if the grouping of the abbreviation
     * contains NO OTHER WORD EXCEPT THE word.
     */
    public boolean isUnique(String word) {
        var curr = makeAbbr(word);
        var set = map.get(curr);
        return set == null || (set.size() == 1 && set.contains(word));
    }

    public String makeAbbr(String input) {
        if (input.length() <= 2) return input;
        return input.substring(0, 1) + (input.length() - 2) + input.substring(input.length() - 1);
    }
}
