package com.leetsols.esm.tree;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    String name;
    Map<Character, TrieNode> children;

    TrieNode() {
        this.children = new HashMap<>();
    }

    public static TrieNode buildTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                if (!curr.children.containsKey(ch)) {
                    curr.children.put(ch, new TrieNode());
                }
                curr = curr.children.get(ch);
            }
        }
        return root;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrieNode{");
        sb.append("name='").append(name).append('\'');
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }

    static void main(String[] args) {
        String[] arr = new String[]{"axh", "ag", "asx"};
        buildTree(arr);
    }
}
