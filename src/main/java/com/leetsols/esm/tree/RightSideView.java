package com.leetsols.esm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<Integer> list = new ArrayList<>();
        /*
         * Go over the Tree
         */
        while (!queue.isEmpty()) {
            int levelLength = queue.size();
            int lastElementNumber = 0;
            /*
             * Go over the level
             */
            for (int i = 0; i < levelLength; i++) {
                TreeNode curr = queue.remove();
                lastElementNumber = curr.val;
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            list.add(lastElementNumber);
        }
        return list;
    }
}
