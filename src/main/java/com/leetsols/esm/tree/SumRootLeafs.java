package com.leetsols.esm.tree;

/*
 * Problem type: Tree, Depth-First Search, Binary Tree
 * Number: 129. Sum Root to Leaf Nodes
 */
public class SumRootLeafs {
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int currentSum) {
        if (root == null) {
            return 0;
        }

        currentSum = currentSum * 10 + root.val;

        if (root.left == null && root.right == null) {
            return currentSum;
        }

        return helper(root.left, currentSum) + helper(root.right, currentSum);
    }
}
