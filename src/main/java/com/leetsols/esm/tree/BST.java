package com.leetsols.esm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        if (low <= root.val && root.val <= high) {
            ans += root.val;
        }

        if (low < root.val) {
            ans += rangeSumBST(root.left, low, high);
        }
        if (root.val < high) {
            ans += rangeSumBST(root.right, low, high);
        }
        return ans;
    }

    public int rangeSumBSTIterative(TreeNode root, int low, int high) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        int ans = 0;

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (low <= node.val && node.val <= high) {
                ans += node.val;
            }

            if (node.left != null && low < node.val) {
                stack.push(node.left);
            }

            if (node.right != null && node.val < high) {
                stack.push(node.right);
            }
        }
        return ans;
    }

    public int getMinimumDifference(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < res.size(); i++) {
            ans = Math.min(ans, res.get(i) - res.get(i - 1));
        }
        return ans;
    }

    public void dfs(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        dfs(root.left, values);
        values.add(root.val);
        dfs(root.right, values);
    }

    public int getMinimumDifferenceInorder(TreeNode root) {
        List<Integer> res = iterativeInorder(root);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < res.size(); i++) {
            ans = Math.min(ans, res.get(i) - res.get(i - 1));
        }
        return ans;
    }

    public List<Integer> iterativeInorder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        TreeNode curr = node;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                list.add(curr.val);
                curr = curr.right;
            }
        }
        return list;
    }

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean dfs(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        if (min >= node.val || node.val >= max) {
            return false;
        }

        boolean left = dfs(node.left, min, node.val);
        boolean right = dfs(node.right, node.val, max);
        return left && right;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        TreeNode curr = root;

        while (curr != null) {
            if (val > curr.val) {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    return root;
                } else curr = curr.right;
            } else {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    return root;
                } else curr = curr.left;
            }
        }
        return new TreeNode(val);
    }

    public int closestValue(TreeNode root, double target) {
        int val, closest = root.val;
        while (root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target)
                    || (Math.abs(val - target) == Math.abs(closest - target) && val < closest)
                     ? val : closest;
            root = target < root.val ? root.left : root.right;
        }
        return closest;
    }
}
