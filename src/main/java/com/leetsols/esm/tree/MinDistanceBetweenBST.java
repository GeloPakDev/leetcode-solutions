package com.leetsols.esm.tree;

public class MinDistanceBetweenBST {
    int res = Integer.MAX_VALUE;
    Integer prev = null;

    public int minDiffInBST(TreeNode root) {
        inOrder(root);
        return res;
    }

    public void inOrder(TreeNode node) {
        if (node == null) return;

        inOrder(node.left);

        if (prev != null) res = Math.min(res, node.val - prev);
        prev = node.val;

        inOrder(node.right);
    }
}
