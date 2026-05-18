package com.leetsols.esm.binarysearch;

public class FindMinInSortedArray {
    /*
     * Description:
     * - sorted array can be rotated from 1 to n times
     * - find out the minimum element in this array
     *
     * - Binary Search on answer
     *  - find the [mid] element in the array
     *  - If [mid] > first element of the array, search on the right side
     *    of the array.
     *  - If [mid] < first element of the array, search on the left side
     *    of the array.
     *
     * Stop search when we find the infection point:
     * - nums[mid] > nums[mid + 1], mid + 1 is the smallest
     * - nums[mid - 1] > nums[mid], mid is the smallest
     *
     */
    public int findMin(int[] nums) {

        if (nums.length == 1) return nums[0];

        int left = 0;
        int right = nums.length - 1;

        /*
         * If the last element is greater than the first element, than there is
         * no rotation
         */
        if (nums[right] > nums[0]) return nums[0];

        while (right >= left) {
            int mid = left + (right - left) / 2;

            /*
             * If the middle element is smaller than its successor
             * then the next element is the smallest
             */
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1];

            /*
             * if the middle element is lesser than its predecessor
             * then the middle element is the smallest
             */
            if (nums[mid - 1] > nums[mid]) return nums[mid];

            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Integer.MAX_VALUE;
    }
}
