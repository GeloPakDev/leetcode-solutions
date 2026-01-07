package com.leetsols.esm.tree;

/*
 * Problem type: Tree, Depth-First Search, Binary Tree
 * Number: 236. Lowest Common Ancestor of a Binary Tree
 */
public class LCABTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: If we hit the end of the branch or if ROOT the p or q itself.
        if (root == null || root == p || root == q) {
            return root;
        }

        // Go over the left and right subtrees if they contain p or q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both sides return results -> the node is common ancestor
        if (right != null && left != null) {
            return root;
        }

        // Otherwise, return non-null side
        return (left != null) ? left : right;
    }
}
