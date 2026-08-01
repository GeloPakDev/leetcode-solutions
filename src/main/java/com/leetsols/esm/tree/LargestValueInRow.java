package com.leetsols.esm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LargestValueInRow {
    /*
     * Algorithm:
     * - Apply the BFS
     */
    public static List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();

        var queue = new LinkedList<TreeNode>();
        queue.add(root);

        var res = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            int nodesInCurrLevel = queue.size();

            var max = Integer.MIN_VALUE;
            for (int i = 0; i < nodesInCurrLevel; i++) {
                var node = queue.remove();

                max = Math.max(max, node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(max);
        }
        return res;
    }
}
