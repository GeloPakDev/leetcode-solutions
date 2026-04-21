package com.leetsols.esm.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSum {
    /*
     * Description:
     * - Given 2 arrays sorted in non-decreasing order and integer [k]
     * - Identify the pair that consists of one element from the first array
     *   and the second element from another array.
     * - Return [k] pairs (a(i), b(i)), ..., a(n), b(n)) with the smallest sum
     *
     * Naive approach:
     * - Create a min-heap to store the pairs with the smallest sum.
     * - Each sum-pair can be calculated by trying all possible pairs which is O(n^2)
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        var heap = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[0] + a[1]));
        for (int j : nums1) {
            for (int value : nums2) {
                heap.add(new int[]{j, value});
            }
        }

        while (!heap.isEmpty()) {
            var curr = heap.poll();
            res.add(List.of(curr[0], curr[1]));
        }
        return res;
    }

    public static List<List<Integer>> kSmallestPairsTwo(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        var minHeap = new PriorityQueue<int[]>(Comparator.comparingInt(a -> (a[0] + a[1])));

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            res.add(List.of(current[0], current[1]));

            int nextIdxInNums2 = current[2] + 1;
            if (nextIdxInNums2 < nums2.length) {
                minHeap.offer(new int[]{current[0], nums2[nextIdxInNums2], nextIdxInNums2});
            }
        }
        return res;
    }
}
