package com.leetsols.esm.graphs;

import com.leetsols.esm.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        var queue = new ArrayDeque<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            var list = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                var current = queue.remove();
                list.add(current.val);
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.push(current.right);
            }
            res.add(list);
        }
        return res;
    }
}
