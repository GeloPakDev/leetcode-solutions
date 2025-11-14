package com.leetsols.esm.heap;

import java.util.PriorityQueue;

public class TestHeap {
    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int[] nums = {67, 341, 234, -67, 12, -976};

        for (int i : nums) {
            heap.add(i);
        }

        heap.add(7451);
        heap.add(-5352);

        while (!heap.isEmpty()) {
            System.out.println(heap.remove());
        }
    }
}
