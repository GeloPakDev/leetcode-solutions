package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Problem type: Hash Table, String, Backtracking
 * Number: 17 Letter Combinations of a Phone Number
 */
public class LetterCombinations {
    private final List<String> combinations = new ArrayList<>();
    private final Map<Character, String> letters = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
    );
    private String phoneDigits;

    public List<String> letterCombinations(String digits) {
        // If the input is empty -> return an empty answer
        if (digits.isEmpty()) {
            return combinations;
        }

        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return;
        }

        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            path.append(letter);
            backtrack(index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}