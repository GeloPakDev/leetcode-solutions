package com.leetsols.esm.tree;

public class SymmetricTree {
    /*
     * Description:
     * - Given the root of the Binary Tree, check whether it is symmetric or not
     * - Symmetric property:
     *  - If root.left  == root.right  -> true
     *  - If left.left  == right.right -> true
     *  - If left.right == right.left  -> true
     */
    public boolean isSymmetric(TreeNode root) {
        return checkSubtree(root.left, root.right);
    }

    public boolean checkSubtree(TreeNode one, TreeNode two) {
        if (one == null && two == null) return true;
        if (one == null || two == null) return false;
        if (one.val == two.val) return false;
        return checkSubtree(one.left, two.right) && checkSubtree(one.right, two.left);
    }
}
