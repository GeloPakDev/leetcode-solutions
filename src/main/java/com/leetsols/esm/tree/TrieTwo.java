package com.leetsols.esm.tree;

public class TrieTwo {
    static class TrieNode {
        private final TrieNode[] links;
        private boolean isEnd;

        public TrieNode() {
            int r = 26;
            links = new TrieNode[r];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    private TrieNode root;

    public TrieTwo() {
        this.root = new TrieNode();
    }

    /*
     * - Start at the root and search for a link corresponding to the first character of the key
     * - If the key exist, move to the following node and repeat for the next character
     * - If the link doesn't exist, create a new node and link it to the parent node
     */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!curr.containsKey(currentChar)) {
                curr.put(currentChar, new TrieNode());
            }
            curr = curr.get(currentChar);
        }
        curr.setEnd();
    }

    /*
     * - Starts at the root
     *  - Link exists -> move to that node
     *  - Link doesn't exist -> There are remaining characters and current node marked isEnd           -> true
     *                       -> There are remaining characters, but no valid paths                     -> false
     *                       -> There are no remaining characters, but the node is not marked as isEnd -> false
     */
    public TrieNode searchPrefix(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char currLetter = word.charAt(i);
            if (curr.containsKey(currLetter)) {
                curr = curr.get(currLetter);
            } else {
                return null;
            }
        }
        return curr;
    }

    public boolean search(String prefix) {
        TrieNode curr = searchPrefix(prefix);
        return curr != null && curr.isEnd();
    }

    public boolean startsWith(String word) {
        TrieNode node = searchPrefix(word);
        return node != null;
    }
}