package com.leetsols.esm.graphs.disjointset;

/*
 * Implementation: QuickFind
 */
public class UnionFind {

    int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i; // every element starts as its own root
        }
    }

    public int find(int x) {
        return root[x]; // directly returns the root of the element
    }

    /*
     * Time complexity: O(n)
     * - To merge 2 groups, it must loop through the entire array and
     *   return every member of Y's group to X's group
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            for (int i = 0; i < root.length; i++) {
                // Re-reference every element of Y's group -> X's group
                if (root[i] == rootY) root[i] = rootX;
            }
        }
    }
}
