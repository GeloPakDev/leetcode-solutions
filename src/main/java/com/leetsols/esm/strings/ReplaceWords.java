package com.leetsols.esm.strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceWords {
    /*
     * Approach: Trie
     * - Go over the dictionary, and form the Trie.
     * - For each word from the sentence, traverse through the trie to identify its shortest
     *   prefix, as far as the node with the [isEnd = true] has been found, return the string
     *   which we have built so far.
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        var trie = new Trie();
        for (String word : dictionary) trie.insert(word);

        String[] wordsFromSen = sentence.split(" ");
        var res = new StringBuilder();
        for (String curr : wordsFromSen) {
            var root = trie.findShortestRoot(curr);
            if (root.isEmpty()) res.append(curr).append(" ");
            else res.append(root).append(" ");
        }
        return res.toString().trim();
    }

    class TreeNode {
        boolean isEnd;
        Map<Character, TreeNode> children = new HashMap<>();
    }

    class Trie {
        TreeNode root = new TreeNode();

        public void insert(String word) {
            TreeNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (cur.children.get(ch) == null) {
                    cur.children.put(ch, new TreeNode());
                }
                cur = cur.children.get(ch);
            }
            cur.isEnd = true;
        }

        public String findShortestRoot(String word) {
            TreeNode curr = root;
            if (curr.children.get(word.charAt(0)) == null) return "";

            var res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (curr.children.get(ch) == null) return "";
                else if (curr.children.get(ch).isEnd) {
                    res.append(ch);
                    return res.toString();
                }
                else {
                    res.append(ch);
                    curr = curr.children.get(ch);
                }
            }

            return res.toString();
        }
    }
}
