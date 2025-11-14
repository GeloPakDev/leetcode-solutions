package com.leetsols.esm.queue;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Stack;

public class QueueProblems {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            /*
             * - Iterate over the stack and check the top element
             * - If it is smaller than the curr -> pop -> capture the difference between indicies
             */
            while (!stack.empty() && temperatures[stack.peek()] < temperatures[i]) {
                int j = stack.pop();
                ans[j] = i - j;
            }
            /*
             * If the curr is larger than top of the stack -> it is warmer
             */
            stack.push(i);
        }
        return ans;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        /* General approach
         * - Use Deque to maintain the Monotonic decreasing queue
         * - Store indices of the element in order to calculate the OutOfBound of the sliding window
         *  - The first index in the queue -> The largest element in the sliding window
         *  - Logic: first index in the queue + size of the window == current index
         *           0 + 3 = 1
         *           1 + 3 = 2
         *           2 + 3 = 3
         */

        /*
         * size is equal to the length of the original array - size of the sliding window
         */
        int[] answer = new int[nums.length - k + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {

            while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);

            if (queue.getFirst() + k == i) {
                queue.removeFirst();
            }

            if (i >= k - 1) {
                answer[i - k + 1] = nums[queue.getFirst()];
            }
        }
        return answer;
    }

    public int longestSubarray(int[] nums, int limit) {
        var maxDeque = new ArrayDeque<Integer>();
        var minDeque = new ArrayDeque<Integer>();
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < nums.length; right++) {
            while (!maxDeque.isEmpty() && maxDeque.getLast() < nums[right]) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(nums[right]);

            while (!minDeque.isEmpty() && minDeque.getLast() > nums[right]) {
                minDeque.removeLast();
            }
            minDeque.addLast(nums[right]);

            while (Math.abs(maxDeque.getFirst() - minDeque.getFirst()) > limit) {
                if (nums[left] == maxDeque.getFirst()) {
                    maxDeque.removeFirst();
                }
                if (minDeque.getFirst() == nums[left]) {
                    minDeque.removeFirst();
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        var stack = new Stack<Integer>();
        var map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && nums2[i] > stack.peek())
                map.put(stack.pop(), nums2[i]);

            stack.push(nums2[i]);
        }

        while (!stack.empty())
            map.put(stack.pop(), -1);

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}