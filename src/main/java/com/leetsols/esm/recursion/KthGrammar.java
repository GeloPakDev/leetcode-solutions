package com.leetsols.esm.recursion;

public class KthGrammar {
    /*
     * Goal:
     * - Given n - number of row
     *         k - number of symbol in the row
     * - Table is built of [n-th] rows, the first row of the table is 0.
     *   Every subsequent rows built by values of previous row where,
     *   each 0 replaced with 01 and 1 with 10
     *
     * Algorithm:
     * - By simulation of the table each row is going to increase by [2^n]
     *   (0)        - 1
     *   (0 1)         - 2
     *   (0 1) (1 0)       - 4
     *   (0 1 1 0) (1 0 0 1)  - 8
     * - Max number of rows = 30
     * - Simulation doesn't look like optimal solution
     *  - if (num = 0) return 01
     *  - if (num = 1) return 10
     * - Climbing up logic
     *  - Who is the parent, am I left or right child?
     *  - In every row, 2 children share 1 parent
     *  - Recurrence relation = (K + 1) / 2;
     * - Left Child (Odd K): Always has the same value as the parent.
     *  - If parent is 0, left child is 0.
     *  - If parent is 1, left child is 1.
     * - Right Child (Even K): Always has the opposite value of the parent.
     *  - If parent is 0, right child is 1.
     *  - If parent is 1, right child is 0.
     */
    public int kthGrammar(int n, int k) {
        if (n == 1) return 0;

        int parent = kthGrammar(n - 1, (k + 1) / 2);

        if (k % 2 != 0) return parent;
        else return parent == 0 ? 1 : 0;
    }

    /*
     * Binary Search Tree
     * - Moving to the left or right half the tree based on position of [k]
     *   in the [n] row, 2*n / 2 provides the middle of the tree, if the
     *   [k] <= middle, go to the left, otherwise go to the right.
     */
    public int dfs(int n, int k, int rootVal) {
        if (n == 1) return rootVal;

        int totalNodes = (int) Math.pow(2, n - 1);
        if (k <= totalNodes / 2) {
            int nextRootVal = (rootVal == 0) ? 0 : 1;
            return dfs(n - 1, k, nextRootVal);
        } else {
            int nextRootVal = (rootVal == 0) ? 1 : 0;
            return dfs(n - 1, k - (totalNodes / 2), nextRootVal);
        }
    }
}