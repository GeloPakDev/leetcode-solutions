package com.leetsols.esm.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class HalveArray {
    /*
     * - At each operation, choose the TOP element from max-heap and reduce by half.
     * - Return the minimum number of operations to reduce the SUM of nums by half.
     * -
     */
    public int halveArray(int[] nums) {
        double target = 0;
        PriorityQueue<Double> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (double num : nums) {
            target += num;
            heap.add(num);
        }

        int ans = 0;
        target /= 2;
        while (target > 0) {
            ans++;
            double num = heap.remove();
            target -= (num / 2);
            heap.add(num / 2);
        }
        return ans;
    }
}
