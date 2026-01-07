package com.leetsols.esm.binarysearch;

/*
 * Problem type: Array, Binary Search
 * Number: 540. Single Element in Sorted Array
 */
public class SingleElementInSortedArray {
    /*
     * Goal:
     * - Given array contains duplicates, we should find the element that
     *   appears only once in O(Log(n)) time.
     *
     * Algorithm:
     * - Binary search on the solution space.
     * - Array is going to be odd all the time, as it contains pairs and only one target element.
     * - After removal of pair, either left or right subarray is going to be even, which means
     *   that subarray doesn't contain the target element.
     * - We have to identify at which half the middle element partner is placed:
     *  - last element in the left subarray
     *  - first element in the right subarray
     * - Then check which side is odd-remained and cover the part where single element is in.
     * -
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean halvesAreEven = (high - mid) % 2 == 0;
            /*
             * Mid’s partner is to the right
             * - halves were originally even -> low = mid + 2, as partner removed from that side
             * - halves were originally odd  -> high = mid - 1
             */
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) low = mid + 2;
                else high = mid - 1;
                /*
                 * Mid’s partner is to the left
                 * - halves were originally even -> high = mid - 2, as partner removed from that side
                 * - halves were originally odd  -> low = mid + 1
                 */
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) high = mid - 2;
                else low = mid + 1;
            } else {
                return nums[mid];
            }
        }
        return nums[low];
    }
}
