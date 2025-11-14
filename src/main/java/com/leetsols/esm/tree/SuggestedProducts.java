package com.leetsols.esm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuggestedProducts {

    class TrieNode {
        Map<Character, TrieNode> children;
        List<String> suggestions;

        TrieNode() {
            this.children = new HashMap<>();
            this.suggestions = new ArrayList<>();
        }
    }

    /*
     * - Prefix is formed during the typing
     * - For each prefix -> find 3 lexicographically smallest words from products that share the same prefix
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        /*
         * Building a Trie
         * - Iterate over the products
         *  - Iterate over each character in the word
         *  - If the character doesn't exist -> add to the children map
         *  - Get the new node
         *  - Add product to the suggestions of current CHARACTER
         *  - Sort and filter nodes up to 3 items
         */
        for (String product : products) {
            TrieNode node = root;
            for (char ch : product.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);

                node.suggestions.add(product);
                Collections.sort(node.suggestions);
                if (node.suggestions.size() > 3) {
                    node.suggestions.remove(node.suggestions.size() - 1);
                }
            }
        }

        List<List<String>> ans = new ArrayList<>();
        TrieNode node = root;

        for (char ch : searchWord.toCharArray()) {
            if (node.children.containsKey(ch)) {
                node = node.children.get(ch);
                ans.add(node.suggestions);
            } else {
                node.children = new HashMap<>();
                ans.add(new ArrayList<>());
            }
        }

        return ans;
    }
}
