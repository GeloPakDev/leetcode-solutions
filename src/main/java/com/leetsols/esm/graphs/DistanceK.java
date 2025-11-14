package com.leetsols.esm.graphs;

import com.leetsols.esm.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DistanceK {
    //Used to store undirected graph
    Map<TreeNode, TreeNode> parents = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> seen = new HashSet<>();
        queue.add(target);
        seen.add(target);

        int distance = 0;
        while (!queue.isEmpty() && distance < k) {
            int currLength = queue.size();
            //Iterate on the level
            for (int i = 0; i < currLength; i++) {
                TreeNode node = queue.remove();
                //The actual level is all neighbors (left , right , parent)
                for (TreeNode neighbor : new TreeNode[]{node.left, node.right, parents.get(node)}) {
                    if (neighbor != null && !seen.contains(neighbor)) {
                        seen.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            distance++;
        }

        List<Integer> ans = new ArrayList<>();
        for (TreeNode node : queue) {
            ans.add(node.val);
        }
        return ans;
    }

    public void dfs(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        /*
         * - Perform DFS and mark each node with its parent
         */
        parents.put(node, parent);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}