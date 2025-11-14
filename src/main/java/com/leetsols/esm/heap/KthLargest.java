package com.leetsols.esm.heap;

import java.util.PriorityQueue;

public class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {
        heap = new PriorityQueue<>();
        this.k = k;

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        /*
         * - As only top k elements will be needed the LAST element will be eliminated from adding
         * - 4, 5, 8, 2
         * - K-th element will be the PEEK element from MIN-HEAP
         */
        if (heap.size() < k || heap.peek() < val) {
            heap.add(val);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.peek();
    }
}