package com.leetsols.esm.stack;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElement {
    /*
     * - Next Greater Element of some element [x] is the first greater element
     *  to the right of [x] in the same array
     * - For each element in nums1 find out its next greater element in nums2
     *
     * Algorithm:
     * - Make a Monotonically Decreasing Stack with results saved for each element
     *   in the HashMap.
     * - During
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        var stack = new Stack<Integer>();
        var map = new HashMap<Integer, Integer>();

        /*
         * Monotonically Decreasing Stack
         * - For each element check does the peek from the stack smaller
         *   than the current one, if it is, the next greater element is found.
         *   Persists in the map the all elements smaller than current one, and
         *   it's found greater element
         *
         * - 6,3,1,9 -> for the 6,3,1 -> 9 is the next greater one.
         */
        for (int j : nums2) {
            while (!stack.isEmpty() && stack.peek() < j)
                map.put(stack.pop(), j);

            stack.push(j);
        }

        while (!stack.isEmpty()) map.put(stack.pop(), -1);

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
