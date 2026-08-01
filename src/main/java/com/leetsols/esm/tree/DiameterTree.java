package com.leetsols.esm.tree;

/*
 * Problem type: Tree, Depth-First Search, Binary Tree
 * Number: 543. Diameter of a Binary Tree
 */
public class DiameterTree {
    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }

    /*
     * Diameter:
     * - It is the length of the longest path between any 2 nodes in a tree.
     * - The longest path has to be between 2 leaf nodes. Also, it means
     *   that it consists of its longest left branch and longest right
     *   branch.
     * - Find the node whose sum of its longest left and longest right branches
     *   is maximized.
     *
     * Algorithm:
     * - Recursively go over with DFS on the left and right side of the node, the
     *   main goal for each node is: calculate the [diameter(left + right)].
     * - For each node in the tree (except the root), we have to return the height
     *   of its tree by taking the max(left_subtree, right_subtree) + 1.
     */
    public int longestPath(TreeNode node) {
        if (node == null) return -1;

        int left = longestPath(node.left);
        int right = longestPath(node.right);
        // Every time, calculate the max diameter found for the current node
        diameter = Math.max(diameter, left + right + 2);

        // Return the longest height for each subtree
        return Math.max(left, right) + 1;
    }
}
