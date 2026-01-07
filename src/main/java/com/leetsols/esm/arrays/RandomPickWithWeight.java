package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Math, Binary Search, Prefix Sum, Randomized
 * Number: 528 Random Pick with Weight
 */
public class RandomPickWithWeight {
    private final int[] prefixSum;
    private final int totalSum;

    /*
     * Problem Description:
     *  Prefix Sum
     *  - We are using it to create a range proportional to the weights
     *  - The indexes don't have a size on its own
     *  - If your weights are: Index 0: 1, Index 1: 9
     *   - The prefix sum array is [1, 10].
     *   - Index 0 now "owns" the range [0,1].
     *   - Index 1 now "owns" the range (1,10].
     *  - Prefix Sum defines the border where one index ends and next one begins
     *
     *  Sampling to weights (Randomization)
     *  - To properly generate a random in a valid range, it should be based on the
     *    weights, not the number of elements in the array.
     *
     *    Example: [1, 99] if you generate the random based on the size (2) it will
     *             not be able to cover the 99
     */
    public RandomPickWithWeight(int[] w) {
        this.prefixSum = new int[w.length];
        int prefix = 0;
        for (int i = 0; i < w.length; i++) {
            prefix += w[i];
            this.prefixSum[i] = prefix;
        }
        this.totalSum = prefix;
    }

    public int pickIndex() {
        double target = this.totalSum * Math.random();
        int low = 0, high = this.prefixSum.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target > this.prefixSum[mid]) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
