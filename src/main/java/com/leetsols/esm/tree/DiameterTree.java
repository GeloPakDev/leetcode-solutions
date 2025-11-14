package com.leetsols.esm.tree;

public class DiameterTree {
    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }

    public int longestPath(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int left = longestPath(node.left);
        int right = longestPath(node.right);

        diameter = Math.max(diameter, left + right + 2);

        return Math.max(left, right) + 1;
    }

}
