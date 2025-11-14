package com.leetsols.esm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeProblems {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root.left, root.right);
    }

    public boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        boolean outer = checkSymmetric(left.left, right.right);
        boolean inner = checkSymmetric(left.right, right.left);
        return outer && inner;
    }
}
