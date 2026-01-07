package com.leetsols.esm.tree;

import java.util.Stack;

public class BSTIterator {
    /*
     * Used to simulate the inorder traversal for the binary search tree,
     * we can use it to pause and resume recursion at our will
     */
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        leftmostInorder(root);
    }

    /*
     * Used to insert the nodes in the leftmost branch
     * Next smallest element will be the leftmost element in the tree.
     * Keep iterating until there is node without left child
     */
    private void leftmostInorder(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /*
     * Returns the next smallest element in the array
     * The first call to the function returns the smallest element in the stack
     * During popping the smallest node from the top of the stack there are 2
     * possibilities:
     *  - Node at the top is the leaf node
     *  - Node has the right child, call helper function for to check left nodes
     *    in the current subtree
     * Upon 2 properties mentioned above we ensure to retrieve the smallest element
     */
    public int next() {
        TreeNode topMostNode = stack.pop();
        if (topMostNode.right != null) {
            leftmostInorder(topMostNode.right);
        }
        return topMostNode.val;
    }

    /*
     * Check existence of the next element in the array
     */
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }
}