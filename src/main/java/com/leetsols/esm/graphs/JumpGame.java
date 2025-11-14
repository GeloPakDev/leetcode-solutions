package com.leetsols.esm.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JumpGame {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        queue.add(start);
        set.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            int nextIndex = curr + arr[curr];
            int prevIndex = curr - arr[curr];

            if (arr[curr] == 0) {
                return true;
            }

            if (nextIndex < n && !set.contains(nextIndex)) {
                if (arr[nextIndex] == 0) {
                    return true;
                } else {
                    queue.add(nextIndex);
                    set.add(nextIndex);
                }
            }
            if (prevIndex >= 0 && !set.contains(prevIndex)) {
                if (arr[prevIndex] == 0) {
                    return true;
                } else {
                    queue.add(prevIndex);
                    set.add(prevIndex);
                }
            }
        }
        return false;
    }
}
