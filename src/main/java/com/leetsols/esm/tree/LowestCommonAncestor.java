package com.leetsols.esm.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestor {
    TreeNode ans = null;

    /*
     * LCA (Lowest Common Ancestor)
     * - Defined as a node between 2 nodes [p] and [q] as the lowest node
     *   in [T], that has both p and q as descendants.
     *
     * Approach:
     * - Traverse the tree in a depth.
     * - When you encounter some of the nodes (p or q), return boolean flag.
     *   It helps to determine if we found the required nodes in any of the
     *   paths, the LCA would be the one for which both subtree recursions
     *   return a [true] flag.
     * - If the current node itself is one of the [p] or [q], mark the variable
     *   [mid] as True, and continue search for the other node in the [left] or
     *   [right] branches.
     * - If either of the right or left branch return [True], it means one of the
     *   2 nodes found below.
     * - If at any point of the traversal, any 2 of the 3 flags become True, it
     *   means we have found the LCA for the [p] and [q]
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        recurseTree(root, p, q);
        return ans;
    }

    public boolean recurseTree(TreeNode currentNode, TreeNode q, TreeNode p) {
        // If we have reached the end of the branch return false
        if (currentNode == null) return false;

        // Left and Right Recursion. If left recursion returns True, set left = 1, else 0
        int left = recurseTree(currentNode.left, p, q) ? 1 : 0;
        int right = recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of the [p] or [q]
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;

        // If any 2 of the 3 flags met, it becomes True
        if (mid + left + right >= 2) ans = currentNode;

        // Return true if any one of the three bool values is True.
        return (mid + left + right) > 0;
    }

    /*
     * Algorithm:
     * - If we have the parent pointers to the left and right pointers for each
     *   [p] and [q], we can traverse back to their ancestors. The first common
     *   node we get during traversal would be the LCA node. We can save the parent
     *   pointers in dictionary as we traverse the tree
     *
     * - Start from the root node and traverse the tree
     * - Until we find the [p] and [q], store the parent pointers in the hashmap.
     * - Once both [p] and [q] is found, we get all ancestors for [p] using the hashmap
     *   and add to a set called [ancestors]
     * - Then we traverse through the ancestors for the node [q]. If the ancestor is
     *   present in the ancestors set for [p], it means this is the first common ancestor
     *   between p and q.
     */
    public TreeNode lowestCommonAncestorIter(TreeNode root, TreeNode p, TreeNode q) {
        // Stack for traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        /*
         * Hashmap for parent pointers
         * 3 → null
         * 5 → 3
         * 1 → 3
         * 6 → 5
         * 2 → 5
         */
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both nodes [p] and [q] (added to the map)
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();

            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        /*
         * Process all ancestors for node [p] using parent pointers.
         *
         * Say p = 6. We walk up the chain using the map:
         * 6 → add 6
         * 5 → add 5   (parent of 6)
         * 3 → add 3   (parent of 5)
         * null → stop
         * So ancestors = {6, 5, 3} — every node on the path from [p] up to root.
         */
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor
        while (!ancestors.contains(q)) q = parent.get(q);

        return q;
    }
}