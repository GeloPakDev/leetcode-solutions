package com.leetsols.esm.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class JumpGame {
    enum Index {
        GOOD, BAD, UNKNOWN
    }

    Index[] memo;

    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;

        var queue = new LinkedList<Integer>();
        queue.add(0);

        var visited = new boolean[nums.length];
        visited[0] = true;

        int level = 0;
        while (!queue.isEmpty()) {
            var currSize = queue.size();
            // Process all nodes on the current level
            for (int i = 0; i < currSize; i++) {
                var currIdx = queue.poll();
                // If the last idx can be reached from here, return current level + 1
                if (currIdx + nums[currIdx] >= nums.length - 1) return level + 1;

                // Check all the neighbors for the current node
                for (int j = 1; j <= nums[currIdx]; j++) {
                    var neighbor = currIdx + j;
                    // Prevent going out of bounds and visit the same nodes
                    if (neighbor < nums.length && !visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            level++;
        }
        return level;
    }

    public boolean canJump(int[] nums) {
        int n = nums.length - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i + nums[i] >= n) {
                n = i;
            }
        }
        return n == 0;
    }

    public boolean canJumpDPBottomTop(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD;
    }

    /*
     * Backtracking:
     * - Value at each index indicates the number of nodes which are going to
     *   successor nodes, and corresponding weights of the edges with each
     *   adjacent element after the current one.
     * - We can greedily choose the element which edge has the largest weight
     *   (i.e. the farthest element from the current one, which doesn't equal
     *   to 0).
     * - we move the current index until we didn't reach the last index
     * - Try every single jump that takes from the first position to the last one
     *   , jump to every index that is reachable, repeat this process until last
     *   index is reached.
     */
    public boolean canJumpFromPosition(int[] nums, int position) {
        if (position == nums.length - 1) return true;

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPos = furthestJump; nextPos > position; nextPos--) {
            if (canJumpFromPosition(nums, nextPos)) return true;
        }
        return false;
    }

    public boolean canJumpFromPositionDP(int[] nums, int position) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPos = furthestJump; nextPos > position; nextPos--) {
            if (canJumpFromPosition(nums, nextPos)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

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
