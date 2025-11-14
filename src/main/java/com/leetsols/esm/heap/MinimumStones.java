package com.leetsols.esm.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumStones {
    public static int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : piles) {
            queue.add(num);
        }

        for (int i = 0; i < k; i++) {
            int stones = (int) Math.ceil((double) queue.remove() / 2);
            queue.add(stones);
        }

        int ans = 0;
        for (int n : queue) {
            ans += n;
        }
        return ans;
    }

    public static void main(String[] args) {
        var temp = new int[]{5, 4, 9};
        minStoneSum(temp, 2);
    }
}
