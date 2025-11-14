package com.leetsols.esm.arrays;

/*
 * Problem type: Array, Math, Two Pointers
 * Number: 189. Rotate Array
 */
public class RotateArray {
    /*
     * 1 <= nums.length <= 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     * 0 <= k <= 10^5
     *
     * nums = [1,2,3,4,5,6,7], k = 3
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * [1,2,3,4,5,6,7]
     * [1,2,3,4,5,6, ]
     * [1,2,3,4,5, ,6]
     * [1,2,3,4, ,5,6]
     * [1,2,3, ,4,5,6]
     * [1,2, ,3,4,5,6]
     * [1, ,2,3,4,5,6]
     * [ ,1,2,3,4,5,6]
     * [7,1,2,3,4,5,6]
     * - save the last letter of the array to the temp variable
     * - start iteration from the last element
     * - inner loop starts from the end till 0
     *
     * Solution passes most of the base cases but fails with big K
     * Consider the following edge case:
     * [1,2,3,4,5,6,7] k = 7
     * [7,1,2,3,4,5,6] 1
     * [6,7,1,2,3,4,5] 2
     * [5,6,7,1,2,3,4] 3
     * [4,5,6,7,1,2,3] 4
     * [3,4,5,6,7,1,2] 5
     * [2,3,4,5,6,7,1] 6
     * [1,2,3,4,5,6,7] 7
     * If the length of array == k just return an array
     *
     * [1,2,3] k = 10
     * 10 % 3 = 1
     *
     * [1,2,3,4] k = 100000000
     */
    public static void rotate(int[] nums, int k) {
        if (nums.length == k) {
            return;
        }
        for (int right = 0; right < k % nums.length; right++) {
            int lastElement = nums[nums.length - 1];
            for (int left = nums.length - 1; left > 0; left--) {
                nums[left] = nums[left - 1];
            }
            nums[0] = lastElement;
        }
    }

    public void rotateTwo(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) return;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
