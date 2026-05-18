package com.leetsols.esm.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    /*
     * 1, 3, 7, 13, 36, 100
     * - MIN-Heap stores the GREATER half of the data, top element -> middle | 13 36 100
     * - MAX-Heap stores the LESSER half of the data, top element -> middle  | 7  3  1
     * - Keep the heaps same size
     *  - even number of elements
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    /*
     * - Maintain all elements in the MIN-HEAP larger than all elements in the MAX-HEAP
     */
    public void add(int num) {
        // 1. Always push to maxHeap first
        maxHeap.add(num);

        // 2. Balancing step: Move the largest of maxHeap to minHeap
        // This ensures all elements in minHeap > maxHeap
        minHeap.add(maxHeap.poll());

        // 3. Size constraint: maxHeap is allowed to be the larger one
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.isEmpty()) return 0.0;

        if (maxHeap.size() > minHeap.size()) {
            return (double) maxHeap.peek();
        }

        // Safe addition to prevent the overflow
        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}
