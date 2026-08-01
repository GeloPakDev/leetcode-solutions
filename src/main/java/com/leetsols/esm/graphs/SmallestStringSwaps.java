package com.leetsols.esm.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SmallestStringSwaps {

    /*
     * Description
     * - Goal: Find Lexicographically the smallest string by swapping the characters
     *   denoted as indices from the input list of pairs.
     * - Note: The important point to note here is that if we have pairs like (a, b) and (b, c),
     *   [a -> b -> c] then we can swap characters at indices [a] and [c]. Although we don't have
     *   the pair (a, c), we can still swap them by first swapping them with the character at index [b].
     *   Thus, because we can swap the characters at these indices any number of times, we can rearrange
     *   the characters a, b, and c into any order.
     * - To find the lexicographically smallest string, we need to sort the characters that corresponds
     *   to these indices in asc order.
     *
     * Algorithm:
     * - Build the graph by connecting the nodes between each other.
     * - Create connected components.
     * - Sort the characters in each connected component.
     * - Build the smallest string by filling the characters from [s] by
     *   using the indices from each connected component (disjoined set of
     *   indices).
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        var unionFind = new UnionFind(s.length());

        // 1. Union all nodes into the component
        for (List<Integer> edge : pairs) {
            int u = edge.get(0);
            int v = edge.get(1);

            unionFind.union(u, v);
        }

        // 2. Make analog of the adjacency list, storing connected components
        var rootOfComponent = new HashMap<Integer, List<Integer>>();
        for (int node = 0; node < s.length(); node++) {
            int root = unionFind.find(node);
            rootOfComponent.putIfAbsent(root, new ArrayList<>());
            rootOfComponent.get(root).add(node);
        }

        /*
         * 3. For each connected component
         * - Create a list of characters by using the indices from current component
         * - Sort the list.
         * - Put the character from the list by taking an [idx] from the component
         *   and inserting sorted character from current list of characters
         */
        char[] smallestString = new char[s.length()];
        for (List<Integer> component : rootOfComponent.values()) {
            // Sort each component in the group
            var characters = new ArrayList<Character>();
            for (int idx : component) characters.add(s.charAt(idx));
            Collections.sort(characters);

            // Insert the characters from current component into the final string
            for (int i = 0; i < component.size(); i++) {
                smallestString[component.get(i)] = characters.get(i);
            }
        }
        return String.valueOf(smallestString);
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                rank[rootX]++;
            }
        }
    }
}
