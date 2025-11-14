package com.leetsols.esm.heap;

import java.util.PriorityQueue;

public class ConnectSticks {
    public static int connectSticks(int[] sticks) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : sticks) {
            heap.add(num);
        }

        int ans = 0;
        while (heap.size() > 1) {
            int x = heap.remove();
            int y = heap.remove();
            int res = x + y;
            heap.add(res);
            ans += res;
        }
        return ans;
    }
}
