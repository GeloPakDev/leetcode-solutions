package com.leetsols.esm.tree;

public class ValidateBST {
    /*
     * Algorithm:
     * - Validating the Binary Tree by defining the lower and upper
     *   boundaries on the values as we move down to the right and
     *   left subtrees of the binary tree.
     */
    public boolean isValidBST(TreeNode root) {
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean check(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;

        boolean left = check(root.left, min, root.val);
        boolean right = check(root.right, root.val, max);
        return left && right;
    }

    Integer prev;

    public boolean isValidBSTInOrder(TreeNode root) {
        prev = null;
        return checkInorder(root);
    }

    public boolean checkInorder(TreeNode root) {
        if (root == null) return true;
        if (!checkInorder(root.left)) return false;
        if (prev != null && root.val <= prev) return false;
        prev = root.val;
        return checkInorder(root.right);
    }
}
