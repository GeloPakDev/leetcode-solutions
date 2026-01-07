package com.leetsols.esm.binarysearch;

/*
 * Problem type: Array, Binary Search
 * Number: 33. Search in Rotated Sorted Array
 */
public class SearchSortedArray {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;

            // Check if the left side is sorted
            if (nums[low] <= nums[mid]) {
                // Target is within the sorted left half
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Otherwise, the right side must be sorted
            else {
                // Target is within the sorted right half
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /*
     * Algorithm:
     * - Find the pivot element (either using binary search or iteratively)
     * - Apply Binary Search on the left side
     * - Apply Binary Search on the right side
     */
    public int initialApproach(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[n - 1]) left = mid + 1;
            else right = mid - 1;
        }

        int answer = binarySearch(nums, target, 0, left - 1);
        if (answer != -1) return answer;
        return binarySearch(nums, target, left, nums.length - 1);
    }

    public int binarySearch(int[] array, int target, int start, int end) {
        int low = start;
        int high = end;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

}
