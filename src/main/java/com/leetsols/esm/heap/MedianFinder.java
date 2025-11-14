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
        maxHeap.add(num);
        minHeap.add(maxHeap.remove());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}
