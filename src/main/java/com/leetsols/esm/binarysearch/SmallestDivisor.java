package com.leetsols.esm.binarysearch;

public class SmallestDivisor {
    /*
     * - Smallest possible divisor - 1
     * - Maximum element of the nums - upper bound
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int ans = -1;

        int low = 1;
        int high = nums[0];
        for (int num : nums) {
            high = Math.max(high, num);
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            int res = findDivisionSum(nums, mid);
            /*
             * - If the current divisor does not exceed threshold
             * it can be used as an answer, but try lower answers
             * so go to the left
             */
            if (res <= threshold) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    /*
     * - Sum of division results with 'divisor'
     */
    public int findDivisionSum(int[] nums, int divisor) {
        int result = 0;
        for (int num : nums) {
            result += (int) Math.ceil((1.0 * num) / divisor);
        }
        return result;
    }
}