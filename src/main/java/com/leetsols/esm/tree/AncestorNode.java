package com.leetsols.esm.tree;

public class AncestorNode {
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, root.val, root.val);
    }

    private int helper(TreeNode root, int curMax, int curMin) {
        if (root == null) {
            return curMax - curMin;
        }

        curMax = Math.max(curMax, root.val);
        curMin = Math.min(curMin, root.val);
        int left = helper(root.left, curMax, curMin);
        int right = helper(root.right, curMax, curMin);
        return Math.max(left, right);
    }
}
