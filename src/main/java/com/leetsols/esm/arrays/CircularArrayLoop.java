package com.leetsols.esm.arrays;

public class CircularArrayLoop {
    /*
     * Description:
     * - nums[i] denotes a number of indices forward/backward you must move
     *   if you are located at idx [i] (not the quantity of numbers needed to
     *   pass).
     * - nums[i] positive -> move forward, opposite abs(backward)
     *  - [2,-1,1,2,2] -> [0,1,2,3,4]
     *  - 1) move from 0 to 2
     *    2) move from 2 to 3
     *    3) move from 3 to 0 -> loop detected
     *
     * - Valid Cycle
     *  - Same direction throughout - every element in the cycle must all be positive (forward) or
     *    all be negative (backward). Mixing is not allowed.
     *  - Length greater than 1 - a single element that points back to itself (self-loop) does not
     *    count as a valid cycle.
     *  - Must exist somewhere in the array - you try every index as a possible cycle starting point,
     *    and return true as soon as you find one.
     *
     * Algorithm:
     * - [next] index calculation for both negative and positive -> ((x % n) + n) % n
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        // Try every idx [i] as a starting point,
        for (int i = 0; i < n; i++) {
            // Lock direction for the current iteration
            boolean forward = nums[i] > 0;
            int slow = i, fast = i;

            while (true) {
                slow = advance(nums, slow, forward);
                fast = advance(nums, fast, forward);

                if (fast != -1) fast = advance(nums, fast, forward);

                if (slow == -1 || fast == -1) break; // Dead end
                if (slow == fast) return true; // Cycle
            }
        }
        return false;
    }

    public int advance(int[] nums, int idx, boolean forward) {
        // Direction must stay consistent
        if ((nums[idx] > 0) != forward) return -1;

        // Calculate the next idx
        int n = nums.length;
        int next = ((idx + nums[idx]) % n + n) % n;

        // No self loop, cycle must be > 1
        return next == idx ? -1 : next;
    }
}