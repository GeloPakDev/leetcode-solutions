package com.leetsols.esm.tree;

import java.util.ArrayDeque;

public class GoodNodes {

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    public int dfs(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.val >= maxSoFar) {
            count = 1;
            maxSoFar = node.val;
        }

        count += dfs(node.left, maxSoFar);
        count += dfs(node.right, maxSoFar);
        return count;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTreeIter(TreeNode q, TreeNode p) {
        if (p == null && q == null) return true;
        if (!check(p, q)) return false;

        ArrayDeque<TreeNode> depP = new ArrayDeque<>();
        ArrayDeque<TreeNode> depQ = new ArrayDeque<>();
        depP.addLast(p);
        depQ.addLast(q);

        while (!depP.isEmpty()) {
            p = depP.removeFirst();
            q = depQ.removeFirst();

            if (!check(p, q)) return false;
            if (p != null) {
                if (!check(p.left, q.left)) return false;
                if (p.left != null) {
                    depP.addLast(p.left);
                    depQ.addLast(q.left);
                }

                if (!check(p.right, q.right)) return false;
                if (p.right != null) {
                    depP.addLast(p.right);
                    depQ.addLast(q.right);
                }
            }
        }
        return true;
    }

    public boolean check(TreeNode q, TreeNode p) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val;
    }
}
