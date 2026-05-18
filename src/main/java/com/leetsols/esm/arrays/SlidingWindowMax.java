package com.leetsols.esm.arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SlidingWindowMax {
    /*
     * Sliding Window has a size of [k] which is moving to the right
     *
     * - Elements that come before the largest element will never be selected
     *   as the largest element of any future windows.
     * - Whenever we encounter a new element [x], we want to discard all elements
     *   that are less than [x] before adding [x], to keep elements in the decreasing
     *   order, to perform these operations we have to keep the monotonic decreasing
     *   queue.
     *
     * Algorithm:
     * - Iterate over the first [k] elements from i = 0 to k - 1
     *  - While deque is not empty and the current element nums[i] >= nums[deque.peekLast()]
     *    continue to pop the elements.
     *  - Push [i] to the end of [deque]
     * - Push the largest element of the first window nums[deque.peekFirst] to the answer
     * - Iterate over all remaining elements from [i = k] to [n - 1] to move to the next
     *   windows:
     *  - Check if the element at the front of the [queue == k - 1]. If it is equal, it
     *    cannot be included in the current window. We pop this element
     *  - While deque is not empty and the current element nums[i] >= nums[deque.peek()]
     *    continue to pop the elements.
     *  - Push [i] at the end of the deque.
     *  - Push the largest element of the current window nums[deque.peekLast()] to the
     *    answer.
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        var deque = new ArrayDeque<Integer>();
        var list = new ArrayList<Integer>();

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        list.add(nums[deque.peekFirst()]);

        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() == i - k) deque.pollFirst();

            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            list.add(nums[deque.peekFirst()]);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
