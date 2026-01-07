package com.leetsols.esm.tree;

import com.leetsols.esm.hashing.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * Problem type: Tree, Depth-First Search, Binary Tree
 * Number: 129. Sum Root to Leaf Nodes
 */
public class VerticalOrderTraversal {
    /*
     * Goal:
     * - Return the vertical order traversal, from top to bottom
     * - If the nodes are in the same row and column, order should be from left to right
     *
     * Approach:
     * How to identify that nodes are in the same column?
     * - Nodes are in the same column if their columnIndex are equals
     *|--------------|
     *|   0  1  2  3 |
     *| 0    3       |
     *| 1 9     20   |
     *| 2    15    7 |
     *|--------------|
     * - During the BFS or DFS approach we can store the columnIndex with TreeNode
     * - Nodes to the LEFT side  -> -1
     *   Nodes to the RIGHT side -> +1
     * - Each time existing column is found, add the corresponding TreeNode value to it
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, ArrayList<Integer>> columnTable = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        int column = 0;
        queue.offer(new Pair<>(root, column));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            root = pair.getKey();
            column = pair.getValue();

            if (root != null) {
                if (!columnTable.containsKey(column)) {
                    columnTable.put(column, new ArrayList<>());
                }
                columnTable.get(column).add(root.val);

                queue.offer(new Pair<>(root.left, column - 1));
                queue.offer(new Pair<>(root.right, column - 1));
            }
        }
        List<Integer> sortedKeys = new ArrayList<>(columnTable.keySet());
        Collections.sort(sortedKeys);
        for (int k : sortedKeys) {
            res.add(columnTable.get(k));
        }
        return res;
    }
}
