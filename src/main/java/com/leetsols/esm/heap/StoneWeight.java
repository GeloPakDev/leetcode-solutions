package com.leetsols.esm.heap;

import java.util.Collections;
import java.util.PriorityQueue;

class StoneWeight {
    public int lastStoneWeight(int[] stones) {
        /*
         * By default, the heap is min-heap -> reverse an order to get max-hip, or you can multiply each element by -1;
         */
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : stones) {
            heap.add(i);
        }

        while (heap.size() > 1) {
            int firstMax = heap.remove();
            int secondMax = heap.remove();
            if (firstMax != secondMax) {
                heap.add(firstMax - secondMax);
            }
        }
        return heap.isEmpty() ? 0 : heap.remove();
    }
}