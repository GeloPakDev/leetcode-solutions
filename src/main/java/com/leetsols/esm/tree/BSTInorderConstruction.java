package com.leetsols.esm.tree;

import java.util.HashMap;
import java.util.Map;

public class BSTInorderConstruction {
    int preorderIdx;
    Map<Integer, Integer> map;

    /*
     * Algorithm:
     * - Preorder: Root -> Left -> Right
     *  - Each element in the [preorder] is the [root] element of the subtree.
     *    It means in every recursion function the next element from the
     *    [preorder] is taken, to construct the left and right subtrees it
     *    finds the root in [preorder] and everything to the left and right of it,
     *    will be left and right subtrees.
     *
     * Implementation:
     * - Build a hashmap to construct the relation of the [value -> index]
     *   so we can find a position in constant time;
     * - preorderIdx - keep track of the element that is used for the root.
     * - arrayToTree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIdx = 0;

        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // LEFT and RIGHT are boundaries in inorder space — they define which slice
        // of the inorder array belongs to this subtree. Empty subtree is the base case.
        if (left > right) return null;

        // Select the preorderIdx as the root and increment it.
        int rootVal = preorder[preorderIdx++];
        TreeNode root = new TreeNode(rootVal);

        /*
         * Build left and right subtree
         * excluding the map.get(rootVal) as it's the root.
         */
        root.left = arrayToTree(preorder, left, map.get(rootVal) - 1);
        root.right = arrayToTree(preorder, map.get(rootVal) + 1, right);
        return root;
    }
}
