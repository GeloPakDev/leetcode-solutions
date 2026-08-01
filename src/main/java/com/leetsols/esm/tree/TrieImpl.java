package com.leetsols.esm.tree;

import java.util.HashMap;
import java.util.Map;

public class TrieImpl {
    static class TreeNode {
        public boolean isWord;
        public Map<Character, TreeNode> children = new HashMap<>();
    }

    private TreeNode root;

    public TrieImpl() {
        root = new TreeNode();
    }

    public void insert(String word) {
        TreeNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.children.get(ch) == null) {
                cur.children.put(ch, new TreeNode());
            }
            cur = cur.children.get(ch);
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        TreeNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.children.get(ch) == null) return false;
            cur = cur.children.get(ch);
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        TreeNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (cur.children.get(ch) == null) return false;
            cur = cur.children.get(ch);
        }
        return true;
    }
}
