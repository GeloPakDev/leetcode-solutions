package com.leetsols.esm.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AutocompleteSystem {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        /*
         * Count the number of times each sentence is typed
         * It holds all sentences that have the current path as prefix, as we need to return
         * sentences that have been typed the most, frequency associated with them.
         */
        Map<String, Integer> sentences = new HashMap<>();
    }

    TrieNode root;
    // It represents the current node in the trie we are located in
    TrieNode currNode;
    TrieNode dead;
    // It represents the current sentence we are typing
    StringBuilder currSentence;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) addToTree(sentences[i], times[i]);

        currSentence = new StringBuilder();
        currNode = root;
        dead = new TrieNode();
    }

    /*
     * The function itself represents typing of the character by
     * character during a search.
     *
     * [c] == '#': We have finished typing the current sentence. Add [currentSentence] to
     *           the trie using the [addToTrie] and reset class variables(currSentence & currNode)
     * [c] != '#' && [c] descendant of currNode:
     *           There are some existing sentences that have the current sentence we are
     *           typing as prefix:
     *           - Add [c] to the currSentence
     *           - move to the node [c]
     *           - get sentences that have the current sentence as prefix
     *           - sort them by count
     *           - return 3 of them
     * [c] != '#' && [c] is not desc of currNode:
     *           There are NO existing sentences that have the current sentence we are
     *           typing as a prefix, add [c] to the currSentence and return an empty list
     */
    public List<String> input(char c) {
        if (c == '#') {
            addToTree(currSentence.toString(), 1);
            currSentence.setLength(0);
            currNode = root;
            return new ArrayList<>();
        }

        currSentence.append(c);
        if (!currNode.children.containsKey(c)) {
            currNode = dead;
            return new ArrayList<>();
        }

        currNode = currNode.children.get(c);
        var heap = new PriorityQueue<String>((a, b) -> {
            int hotA = currNode.sentences.get(a);
            int hotB = currNode.sentences.get(b);
            if (hotA == hotB) return b.compareTo(a);
            return hotA - hotB;
        });

        for (String sentence : currNode.sentences.keySet()) {
            heap.add(sentence);
            if (heap.size() > 3) heap.remove();
        }

        var list = new ArrayList<String>();
        while (!heap.isEmpty()) list.add(heap.remove());
        Collections.reverse(list);
        return list;
    }

    public void addToTree(String sentence, int count) {
        TrieNode node = root;
        for (char ch : sentence.toCharArray()) {
            if (!node.children.containsKey(ch)) node.children.put(ch, new TrieNode());
            node = node.children.get(ch);
            node.sentences.put(sentence, node.sentences.getOrDefault(sentence, 0) + count);
        }
    }
}
