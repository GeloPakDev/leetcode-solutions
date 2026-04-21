package com.leetsols.esm.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class UglyNumber {
    /*
     * Naive approach:
     * - Go over possible numbers and check for the isUgly until we reach the n(th) number
     *
     * Set approach:
     * - If the number multiplied by the 2, 3, 5, it means that is also an ugly
     *   by keeping generating and getting the smallest one from the set, we
     *   can get n-th target value. To ensure that we are getting the smallest
     *   element after each iteration, we use the TreeSet that keep elements in the
     *   sorted order and removes duplicates.
     * - currentUgly is the lowest element that is getting from the TreeSet.
     *
     * Min-Heap approach
     * - Priority Queue gives provides us efficiency in terms of getting the smallest
     *   element from the heap at constant time
     * - After popping the smallest element from the heap, the newElements will be
     *   multiplied by the 2, 3 and 5. They are pushed back in the heap, to avoid the duplicates,
     *   hashset is used to track the numbers that we have already used.
     */
    public static int nthUglyNumber(int n) {
        TreeSet<Long> uglyNumberSet = new TreeSet<>();
        uglyNumberSet.add(1L);

        Long currentlyUgly = 1L;
        for (int i = 0; i < n; i++) {
            currentlyUgly = uglyNumberSet.pollFirst();
            uglyNumberSet.add(currentlyUgly * 2);
            uglyNumberSet.add(currentlyUgly * 3);
            uglyNumberSet.add(currentlyUgly * 5);
        }
        return currentlyUgly.intValue();
    }

    public int nthUglyNumberTwo(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> seenNumbers = new HashSet<>(); // Set to avoid duplicates
        int[] primeFactors = { 2, 3, 5 }; // Factors for generating new ugly numbers
        minHeap.offer(1L);
        seenNumbers.add(1L);
        long currentUgly = 1L;
        for (int i = 0; i < n; i++) {
            currentUgly = minHeap.poll(); // Get the smallest number
            // Generate and push the next ugly numbers
            for (int prime : primeFactors) {
                long nextUgly = currentUgly * prime;
                if (seenNumbers.add(nextUgly)) { // Avoid duplicates
                    minHeap.offer(nextUgly);
                }
            }
        }
        return (int) currentUgly;
    }

    public static boolean isUgly(int n) {
        if (n <= 0) return false;

        while (n % 2 == 0) n = n / 2;
        while (n % 3 == 0) n = n / 3;
        while (n % 5 == 0) n = n / 5;

        return n == 1;
    }
}
