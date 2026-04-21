package com.leetsols.esm.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
 * Problem type: Array, Two Pointers, Sorting
 * Number: 15. 3Sum
 */
public class ThreeSum {
    /*
     * [-1,0,1,2,-1,-4]
     * [-4,-1,-1,0,1,2]
     *
     * Algorithm:
     * - Sort an array
     * - Iterate over an array while the current element <= 0, otherwise there is no
     *   meaning to search for the triplets
     *   - To provide the triplets without duplicates check consecutive elements
     *     nums[i - 1] != nums[i]
     *   - As soon as starting element is found, pivot it, to apply TwoSum approach
     *     to the other 2 elements
     *   - In case if the triplet is found check the left elements in this range to avoid duplicates
     *     and continue search of other triplets
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        var res = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(i, nums, res);
            }
        }
        return res;
    }

    public void twoSum(int start, int[] nums, List<List<Integer>> res) {
        int left = start + 1;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[start] + nums[left] + nums[right];
            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                res.add(Arrays.asList(nums[start], nums[left++], nums[right--]));
                while (left < right && nums[left - 1] == nums[left]) left++;
            }
        }
    }

    public void twoSumSet(int start, int[] nums, List<List<Integer>> res) {
        var seen = new HashSet<Integer>();
        for (int i = start + 1; i < nums.length; i++) {
            int complement = -nums[start] - nums[i];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[start], nums[i], complement));
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
            }
            seen.add(nums[i]);
        }
    }

    /*
     * Hashset approach
     * - nums[i] <= 0                     -> choose pivot as negative, remaining elements cannot sum to 0
     * - nums[i - 1] != nums[i]           -> current value is the same as the one before, skip it
     * - complement = -nums[i] - nums[j]; ->
     *  to find the 3rd number you need to isolate it to the other side of the equation (simple equation)
     *  - nums[i] + nums[j] + nums[k] = 0
     *  - complement = 0 - nums[i] - nums[j]
     */
    public List<List<Integer>> threeSumHashSet(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) twoSum(nums, i, res);
        }
        return res;
    }

    public void twoSum(int[] nums, int i, List<List<Integer>> res) {
        var seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; j++) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) ++j;
            }
            seen.add(nums[j]);
        }
    }

    /*
     * - The problem is similar to the 3SUM, but have distinctions in the target value
     *   in the original problem we have been looking for the triplet that equal to 0,
     *   here we are looking for the one that are less than a targets, as a result should
     *   be given the quantity of them
     *
     * Algorithm:
     * - Sort an array
     * - Iterate over the array while the curr < target && i < arr.length
     * - Choose the pivot element <= target
     * -
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += twoSumSmaller(i, nums, target);
        }
        return res;
    }

    public int twoSumSmaller(int pivot, int[] nums, int target) {
        int left = pivot + 1;
        int right = nums.length - 1;
        int res = 0;
        while (left < right) {
            int sum = nums[pivot] + nums[left] + nums[right];
            if (sum < target) {
                res += (right - left);
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}