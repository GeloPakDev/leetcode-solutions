package com.leetsols.esm.tree;

public class UniqueBST {
    /*
     * Description:
     * - Given [n], return the number of structurally unique BST, which has exactly [n]
     *   nodes of unique values from 1 to n.
     * - Structurally unique, means it consists of [n] nodes, and the positioning
     *   should satisfy the main property of BST, where nodes to the left are smaller
     *   and nodes to the right are larger.
     * - How many ways can left be arranged? * How many ways can right be arranged?
     * - Start building from the smallest building blocks
     *  - 0 nodes -> 1 way (empty tree)
     *  - 1 node  -> 1 way (only one edge)
     * - Start from 2, as a tree should contain more than 1 node
     *  - Pick 1 as a root, left has 0 nodes, right has 1 node -> 1 * 1 = 1
     *  - Pick 2 as a root, left has 1 node, right has 0 node -> 1 * 1 = 1
     * - Total = 2
     *
     * Approach:
     * - Iterate from 1 -> n to generate all possible valid BST
     * - Consider each [i] as the root:
     *  - For each [i], valid BSTs to the left can be generated only by taking
     *    the numbers from [1] -> [i - 1]
     *  - For each [i], valid BSTs to the right can be generated only by taking
     *    the numbers from [i + 1] -> n
     * - Recurrence relation:
     *  - Base Case: There is only 1 combination to construct a BST out of
     *    sequence of length 1 (only the root) or nothing (empty tree)
     *  - F(0) = 1, F(1) = 1
     * - G(n) = ∑ (n, i = 1) G(i - 1) * G(n - i)
     *
     * Example:
     * i=2, j=1: res[2] += res[0] * res[1] = 1×1 = 1
     * i=2, j=2: res[2] += res[1] * res[0] = 1×1 = 1
     * res[2] = 2 ✓
     *
     * i=3, j=1: res[3] += res[0] * res[2] = 1×2 = 2
     * i=3, j=2: res[3] += res[1] * res[1] = 1×1 = 1
     * i=3, j=3: res[3] += res[2] * res[0] = 2×1 = 2
     * res[3] = 5 ✓
     */
    public int numTrees(int n) {
        // Store the number of unique BST using the [i] nodes
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        // Outer loop - How many nodes I am solving right now?
        for (int i = 2; i <= n; i++) {
            // Inner loop - Which node will be picked as a root?
            for (int j = 1; j <= i; j++) {
                /*
                 * left  has [j - 1] nodes
                 * right has [i - j] nodes
                 */
                res[i] += res[j - 1] * res[i - j];
            }
        }
        return res[n];
    }
}
