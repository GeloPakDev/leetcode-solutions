package com.leetsols.esm.tree;

public class BalancedBT {
    /*
     * Algorithm:
     * - Left and right subtrees of the node should be balanced
     * - Balanced subtree is the one where difference between the
     *   left and right > 1
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != 1;
    }

    private int checkHeight(TreeNode root) {
        // height of null -> 0
        if (root == null) return 0;

        // Short-circuiting, if the left subtree is -1 (unbalanced), return -1
        int left = checkHeight(root.left);
        if (left == -1) return -1;

        // Short-circuiting, if the right subtree is -1 (unbalanced), return -1
        int right = checkHeight(root.right);
        if (right == -1) return -1;

        // This node is unbalanced
        if (Math.abs(left - right) > 1) return -1;

        // Return actual height
        return Math.max(left, right) + 1;
    }
}
