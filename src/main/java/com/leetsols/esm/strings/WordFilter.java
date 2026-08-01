package com.leetsols.esm.strings;

import java.util.HashMap;
import java.util.Map;

class WordFilter {
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int weight = -1;
    }

    private final TrieNode trie = new TrieNode();

    public WordFilter(String[] words) {
        for (int index = 0; index < words.length; ++index) {
            String word = words[index];
            int len = word.length();

            // Insert "suffix + { + word" for every suffix of this word
            for (int i = 0; i <= len; i++) {
                String suffixWrapped = word.substring(i) + "{" + word;
                insert(suffixWrapped, index);
            }
        }
    }

    private void insert(String s, int weight) {
        TrieNode curr = trie;
        curr.weight = weight;

        for (char ch : s.toCharArray()) {
            curr = curr.children.computeIfAbsent(ch, c -> new TrieNode());
            curr.weight = weight; // overwrite along the path -> last write wins
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode current = trie;
        String query = suffix + "{" + prefix;

        for (char ch : query.toCharArray()) {
            if (!current.children.containsKey(ch)) return -1;
            current = current.children.get(ch);
        }

        return current.weight;
    }
}

