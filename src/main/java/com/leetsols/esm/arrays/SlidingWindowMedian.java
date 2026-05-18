package com.leetsols.esm.arrays;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    /*
     * Description:
     * - Median - middle value in the ordered integer list.
     * - If the list is even, there is no middle value. So the median is the
     *   mean of the 2 middle values.
     *
     * - Given an array of unsorted integer values and an integer [k], which
     *   dictates the size of the window [k], return the median array for
     *   each window in the original array.
     *
     * Approach:
     * - As this problem differs from MedianFinder, here we need to find out the
     *   median element in the window, the problem becomes more difficult as removals from
     *   the top of the heap is only accessible. As the tops of the heaps are needed to
     *   find out the median and as long as we can keep them balanced, we could also
     *   keep some of the extraneous elements.
     * - We can use the hash-tables to keep the track of the invalidated elements.
     *   Once they reach the heap tops, we remove them from the heaps.
     * - How to balance the heaps while keeping the extreneous elements?
     *   This is done by moving some elements to the heap which has the extraneous elements
     *   from the other heap. This cancels out the effect of having the extraneous elements
     *   and maintains the invariant that the heaps are balanced.
     *
     * Algorithm:
     * - Two priority queues
     *  - Max Heap to store the smaller half of the elements
     *  - Min Heap to store the larger half of the elements
     * - Hashmap
     *  - Keep track of the invalid numbers. It holds the count of occurances
     *    of all such numbers that have been invalidated and yet remain in the
     *    heaps.
     * - The max heap is allowed to store at worst, one more element more than the
     *   min heap.
     * - Balancing:
     *  - The actual sizes of the heaps are irrelevant. Only the count of valid
     *    elements in both heaps matter
     *  - Keep a balance factor:
     *   - balance = 0. Both heaps are balanced or nearly balanced
     *   - balance < 0. [lo] need more valid elements. Elements from [hi] are moved
     *     to [lo]
     *   - balance > 0. [hi] need more valid elements. ELements from [lo] are moved
     *     to [hi]
     * - Inserting a new element
     *  - If [input_number] <= top of the [lo], it can be inserted into the [lo], however
     *    this inbalances [hi] (it has lower elements right now). Hence [balance] is incremented
     *  - Otherwise input_number is added into the [hi]. Now [lo] is unbalanced. Hence
     *    balance is incremented
     * - Lazy removal of outgoing number
     *  - If [outgoing_number] is present in [lo], then invalidating this occurance will
     *    unbalance [lo], balance must be incremented
     *  - If [outgoing_number] is present in [hi], then invalidating this occurance will
     *    unbalance [hi], balance must be decremented
     *  - Increment the counter of this element in the hashtable
     *  - Once an invalid element reaches either of the heap tops, we remove them and
     *    decrement their counts in the hashtable
     */
    private PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> hi = new PriorityQueue<>();
    private Map<Integer, Integer> delayed = new HashMap<>();
    private int loSize = 0;
    private int hiSize = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];

        // Initialize the first window
        for (int i = 0; i < k; i++) addNum(nums[i]);
        res[0] = getMedian(k);

        for (int i = k; i < nums.length; i++) {
            int outgoing = nums[i - k];
            int incoming = nums[i];

            // 1. Handling the Outgoing element (Lazy Removal)
            int balance = 0;
            if (outgoing <= lo.peek()) balance--; // Outgoing from the lower half
            else balance++; // Outgoing from the upper half
            delayed.put(outgoing, delayed.getOrDefault(outgoing, 0) + 1);

            // 2. Handling Incoming element
            if (!lo.isEmpty() && incoming <= lo.peek()) {
                lo.add(incoming);
                loSize++;
                balance++;
            } else {
                hi.add(incoming);
                hiSize++;
                balance--;
            }

            // 3. Rebalance Heaps
            // balance < 0 means [lo] needs elements from the [hi]
            if (balance < 0) {
                lo.add(hi.poll());
                loSize++;
                hiSize--;
                prune(hi);
            } else if (balance > 0) {
                hi.add(lo.poll());
                hiSize++;
                loSize--;
                prune(lo);
            }

            prune(lo);
            prune(hi);

            res[i - k + 1] = getMedian(k);
        }
        return res;
    }

    private void addNum(int num) {
        if (lo.isEmpty() || num <= lo.peek()) {
            lo.add(num);
            loSize++;
        } else {
            hi.add(num);
            hiSize++;
        }
        rebalanceInitial();
    }

    private void rebalanceInitial() {
        if (loSize > hiSize + 1) {
            hi.add(lo.poll());
            loSize--;
            hiSize++;
        } else if (hiSize > loSize) {
            lo.add(hi.poll());
            hiSize--;
            loSize++;
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int top = heap.peek();
            delayed.put(top, delayed.get(top) - 1);
            if (delayed.get(top) == 0) delayed.remove(top);
            heap.poll();
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return (double) lo.peek();
        } else {
            return ((double) lo.peek() + hi.peek()) / 2.0;
        }
    }
}
