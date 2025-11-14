package com.leetsols.esm.tree;

class PathSum {
    int target;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        target = targetSum;
        return dfs(root, 0);
    }

    public boolean dfs(TreeNode node, int curr) {
        /*
         * - Empty Tree
         */
        if (node == null) {
            return false;
        }
        /*
         * - At the leaf node, check the children
         * - Check the total sum of the path
         */
        if (node.left == null && node.right == null) {
            return (curr + node.val) == target;
        }
        /*
         * - Go over the 'left' subtree
         * - Go over the 'right' subtree
         * - If ANY path exists, return true
         */
        curr += node.val;
        boolean left = dfs(node.left, curr);
        boolean right = dfs(node.right, curr);
        return left || right;
    }
}