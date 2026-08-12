package com.leetsols.esm.dp;

import java.util.Arrays;
import java.util.HashMap;

public class PartitionToKSubsets {
    /*
     * Algorithm:
     * - Task is to split the array into [k] different parts such that all
     *   the subsets have equal sum, each part should have (sumArray / k).
     * - Pick one element and try to include it in the subset, if after
     *   including it in the current subset, it can't make a proper valid
     *   combination, discard the last picked element.
     *
     * - Calculate the totalArraySum to check if the sum is divisible by [k]
     *   if so, we can divide the array into the [k] subsets.
     * - For each element that has not been picked yet, [taken[i] == false]
     *  - Include it into our subset [currSum = currSum + arr[j]] and mark it
     *    as taken, by marking taken[i] = true.
     *  - Make a recursive call to find the next element to include
     *   - If the recursive call returns true, we have found a valid combination
     *     to break an array into [k] subsets, thus we can return [true].
     *   - Else, discard the current element and set taken[j] = false, and
     *     subtract the current element from currSum, and then they try the
     *     next element that is not taken.
     * - When the [currSum == targetSum] we have made 1 set. So reset currSum to 0
     *   to start a new subset and increment count by one.
     * - When [count == k - 1], we have made [k - 1] subsets, each with sum equal
     *   to [targetSum]. Therefore, the last subset also have the sum equal to the
     *   [targetSum].
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalArraySum = 0;
        int n = nums.length;

        for (int i : nums) totalArraySum += i;

        if (totalArraySum % k != 0) return false;

        Arrays.sort(nums);
        reverse(nums);

        int targetSum = totalArraySum / k;

        char[] taken = new char[n];
        Arrays.fill(taken, '0');

        var memo = new HashMap<String, Boolean>();
        return backtrack(nums, 0, 0, 0, k, targetSum, taken, memo);
    }

    /*
     * Backtracking:
     * - Calculate the totalArraySum and check if the sum is evenly divisible by [k], If
     *   so we can break array into [k] subsets and the sum must be equal [targetSum = totalArraySum / k]
     * - For each element that hasn't been picked yet, [taken[i] == false]
     *   We include it into our subset, [currSum = currSum + array[i]] and mark it as seen
     *   by [taken[i] = true].
     * - Make next recursive call to find the next element to include.
     *  - If the call returns true, valid combination has been found -> return true
     *  - If the call returns false, discard the current element [taken[i] = false]
     *    and substract the current element from [currSum], try the next element
     *    that is not taken
     * - If the [currSum == targetSum], we have made a subset. Reset the currSum = 0
     *   to start the new subset and increment count by 1
     * - When [count == k - 1], we have made [k - 1] subsets, each with the sum equals
     *   targetSum
     */
    public boolean backtrack(int[] arr, int count, int currSum, int k, int targetSum, boolean[] taken) {
        int n = arr.length;
        // [k - 1] subsets with target sum and the last subset will also have target sum
        if (count == k - 1) return true;
        // Current subset sum exceeds target sum, no need to proceed further.
        if (currSum > targetSum) return false;
        /*
         * When the current subset sum reaches the target sum, then one subset is made
         * Increment count and reset current sum to 0.
         */
        if (currSum == targetSum) return backtrack(arr, count + 1, 0, k, targetSum, taken);

        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                // Include this element in current subset
                taken[i] = true;
                // If using current i[th] element in this subset leads to make all valid subsets
                if (backtrack(arr, count, currSum + arr[i], k, targetSum, taken)) return true;
                // Backtrack step
                taken[i] = false;
            }
        }
        return false;
    }

    /*
     * Algorithm (Backtracking with memoization):
     * - Memoize the answer based on elements included in any of the subsets
     * - 1(1,2) && 2(3,4) -> false -> 1(1,3) && 2(2,4) -> false
     * - String denote '0' is not picked, '1' is picked, we can use a map
     *   of a string as a key, and boolean as value to memoize the result.
     * - For each element that is not picked yet, taken[i] is '0'
     *  - Include it subset, [currSum = currSum + arr[i]], [taken[i] = '1']
     *  - Recursive call to find the next element to include
     *   - if (true)  -> found a valid combination
     *   - if (false) -> discard the current element, [taken[i] = '0']
     *                   subtract the current element from currSum
     * - if (currSum == targetSum) -> reset [currSum = 0], count++
     * - if (count == k - 1) -> return true
     */
    private boolean backtrack(int[] arr, int index, int count, int currSum, int k, int targetSum, char[] taken, HashMap<String, Boolean> memo) {
        int n = arr.length;

        if (count == k - 1) return true;
        if (currSum > targetSum) return false;

        String takenStr = new String(taken);

        if (memo.containsKey(takenStr)) return memo.get(takenStr);

        if (currSum == targetSum) {
            boolean ans = backtrack(arr, 0, count + 1, 0, k, targetSum, taken, memo);
            memo.put(takenStr, ans);
            return ans;
        }

        for (int i = index; i < n; i++) {
            if (taken[i] == '0') {
                taken[i] = '1';

                if (backtrack(arr, i + 1, count, currSum + arr[i], k, targetSum, taken, memo)) return true;

                taken[i] = '0';
            }
        }

        memo.put(takenStr, false);
        return false;
    }

    void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}