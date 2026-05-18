package com.leetsols.esm.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class TopFrequentElements {
    /*
     * - Find the frequencies of all elements using HashMap
     * - Create Min-Heap and iterate over Hashtable
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        for (int num : nums) {
            hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(hashmap::get));
        for (int num : hashmap.keySet()) {
            heap.add(num);
            if (heap.size() > k) {
                heap.remove();
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = heap.remove();
        }
        return ans;
    }

    /*
     * QuickSelect
     * - apply the counting -> i.e. frequency map
     * - apply the quick select on the frequency of that values
     */
    public int[] topKFrequent2(int[] nums, int k) {
        var map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] uniqueKeys = map.keySet().stream().mapToInt(i -> i).toArray();
        quickSelect(uniqueKeys, 0, uniqueKeys.length - 1, k - 1, map);
        return Arrays.copyOf(uniqueKeys, k);
    }

    public void quickSelect(int[] nums, int left, int right, int k, Map<Integer, Integer> map) {
        // If the partition contains only one element, we've found our target
        if (left >= right) return;

        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex, map);

        // Partition the array around the pivot
        pivotIndex = partition(nums, left, right, pivotIndex, map);

        // Decide which side to discard
        if (k == pivotIndex) {
            return;
        } else if (k < pivotIndex) {
            // Target is in the left part
            quickSelect(nums, left, pivotIndex - 1, k, map);
        } else {
            // Target is in the right part
            quickSelect(nums, pivotIndex + 1, right, k, map);
        }
    }

    public int partition(int[] nums, int left, int right, int pivotIndex, Map<Integer, Integer> map) {
        int pivotValue = map.get(nums[pivotIndex]);

        // Move pivot to the end temporarily
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        // Move all elements smaller than pivotValue to the left
        for (int i = left; i < right; i++) {
            if (map.get(nums[i]) > pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // Move pivot to its final sorted place
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
