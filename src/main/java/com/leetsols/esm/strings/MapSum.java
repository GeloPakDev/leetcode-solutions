package com.leetsols.esm.strings;

import java.util.HashMap;
import java.util.Map;

public class MapSum {
    static class TreeNode {
        public int sum;
        public Map<Character, TreeNode> children = new HashMap<>();

        public TreeNode(int sum) {
            this.sum = sum;
        }
    }

    TreeNode root;
    Map<String, Integer> map;

    public MapSum() {
        root = new TreeNode(0);
        map = new HashMap<>();
    }

    /*
     * If the (key, value) mapping does exist, change the sum of each TreeNode:
     * - curr.sum = curr.sum - map.get(key) + val
     * Else, add new val to the existing TreeNodes and update
     */
    public void insert(String key, int val) {
        TreeNode curr = root;
        if (map.get(key) != null) {
            int oldVal = map.get(key);
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                // If the node doesn't exist
                if (curr.children.get(ch) == null) {
                    curr.children.put(ch, new TreeNode(0));
                }

                int currSum = curr.children.get(ch).sum;
                curr.children.get(ch).sum = currSum - oldVal + val;
                curr = curr.children.get(ch);
            }
        } else {
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                if (curr.children.get(ch) == null) {
                    curr.children.put(ch, new TreeNode(val));
                } else {
                    curr.children.get(ch).sum += val;
                }
                curr = curr.children.get(ch);
            }
        }
        map.put(key, val);
    }

    public int sum(String prefix) {
        TreeNode cur = root;
        int sum = 0;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            if (cur.children.get(ch) == null) return 0;

            sum = cur.children.get(ch).sum;
            cur = cur.children.get(ch);
        }
        return sum;
    }
}
