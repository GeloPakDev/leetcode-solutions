package com.leetsols.esm.tree;

public class MaxXOR {
    private static class Node {
        Node[] children = new Node[2];
    }

    private final Node root = new Node();

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int maxXOR = 0;

        // Insert all numbers into the Bitwise Trie
        for (int num : nums) insert(num);

        // Query the Trie for each number to find its maximum XOR pair
        for (int num : nums) {
            Node curr = root;
            int currMaxXOR = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int toggleBit = 1 - bit;// It gives us the opposite bit

                // If the opposite path exists, go on it
                if (curr.children[toggleBit] != null) {
                    currMaxXOR |= (1 << i);
                    curr = curr.children[toggleBit];
                } else {
                    // Forced to take the same path
                    curr = curr.children[bit];
                }
            }
            maxXOR = Math.max(maxXOR, currMaxXOR);
        }
        return maxXOR;
    }

    public void insert(int num) {
        Node current = root;
        // Start from the 31st bit (MSB) down to the 0th bit (LSB)
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1; // Extract the i-th bit
            if (current.children[bit] == null) current.children[bit] = new Node();
            current = current.children[bit];
        }
    }
}
