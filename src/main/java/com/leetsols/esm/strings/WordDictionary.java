package com.leetsols.esm.strings;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) curr.children.put(ch, new TrieNode());
            curr = curr.children.get(ch);
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        return searchInNode(word, root);
    }

    public boolean searchInNode(String word, TrieNode node) {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (!node.children.containsKey(ch)) {
                // If the current node is '.', explore all nodes at this level
                if (ch == '.') {
                    for (char x : node.children.keySet()) {
                        TrieNode child = node.children.get(x);
                        if (searchInNode(word.substring(i + 1), child)) return true;
                    }
                }
                // No nodes lead to answer or current character is not '.'
                return false;
            } else {
                node = node.children.get(ch);
            }
        }
        return node.isWord;
    }
}
