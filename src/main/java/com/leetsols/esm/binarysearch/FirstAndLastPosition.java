package com.leetsols.esm.binarysearch;

/*
 * Problem type: Array, Binary Search
 * Number: 34. Find First And Last Position of Element in Sorted Array
 */
public class FirstAndLastPosition {

    /*
     * Goal:
     *  - Find out the first and last positions of target in the array
     *
     * Example:
     * [5,7,7,8,8,10] -> 3,4
     * [1,7,7,7,7,9]  -> 1,4
     *
     * Algorithm:
     * - Use 2 binary searches to find first and last occurrence of target
     * - When the mid is found, it requires to check if [mid] first or last index
     *   where target occurs.
     *  - First Position
     *   - If the [mid] is the same as [begin] that implies our [mid] is the first element
     *     in the remaining subarray
     *   - Element to the left of the mid, equal to the target -> means we should keep searching
     *     to the left.
     *  - Last position
     *   - If the [mid] is the same as [end] that implies our [mid] is the last element
     *     in the remaining subarray
     *   - Element to the right of the mid, equal to the target -> means we should keep searching
     *     to the right.
     */
    public int[] searchRange(int[] nums, int target) {
        int firstPosition = leftSearch(nums, target);
        if (firstPosition == -1) return new int[]{-1, -1};
        int secondPosition = rightSearch(nums, target);
        return new int[]{firstPosition, secondPosition};
    }

    public int leftSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                res = mid;
                right = mid - 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public int rightSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                res = mid;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
