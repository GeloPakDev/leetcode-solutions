package com.leetsols.esm.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Problem type: Depth-First Search, Breadth-First Search, Graph, Topological Sort
 * Number: 1008. Construct Binary Search Tree from Preorder Traversal
 */
public class BSTConstruction {
    int preIdx = 0;
    int[] preorder;
    Map<Integer, Integer> idxMap = new HashMap<>();

    int n;

    /*
     * Algorithm:
     * - Construct [inorder] traversal by sorting the [preorder] array
     * - Construct binary tree from [preorder] and [inorder] traversal
     *  - Peek one by one from preorder array and try to put them
     *    as left or as right as possible
     *  - The possibility to use an element as a child is checked by
     *    inorder array:
     *   - If it contains no elements for this subtree, then the element
     *     couldn't be used here, and one should use null as child instead.
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        preIdx++;
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);

        int idx = 0;
        for (Integer element : inorder) idxMap.put(element, idx++);
        return helper(0, inorder.length);
    }

    public TreeNode helper(int inLeft, int inRight) {
        // There is no elements to construct the tree
        if (inLeft == inRight) return null;

        int rootVal = preorder[preIdx];
        TreeNode root = new TreeNode(rootVal);

        // root splits inorder list into left and right subtrees
        int idx = idxMap.get(rootVal);

        preIdx++;
        root.left = helper(inLeft, idx);
        root.right = helper(idx + 1, inRight);
        return root;
    }

    public TreeNode bstFromPreorderIter(int[] preorder) {
        this.preorder = preorder;
        n = preorder.length;
        return recursiveHelper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /*
     * Algorithm:
     * - By applying the property of the BST where all elements to the
     *   left should be lower than root and all elements to the right
     *   should be larger than root, we can inherit an approach proposed
     *   in the [Validate BST], to check for the maximum and minimum we
     *   have seen so far while going to the right and left subtree:
     *  - On the left side the lower bound stays the same
     */
    public TreeNode recursiveHelper(int lower, int upper) {
        // If all elements from the preorder are used, tree constructed
        if (preIdx == n) return null;

        int val = preorder[preIdx];
        // If the current element cannot be placed to meet the BST req-s
        if (val < lower || val > upper) return null;

        preIdx++;

        TreeNode root = new TreeNode(val);
        root.left = helper(lower, val);
        root.right = helper(val, upper);
        return root;
    }
}
