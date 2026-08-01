package com.leetsols.esm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Subset2 {
    /*
     * Description:
     * - Need to find the power set (all possible subsets) excluding the duplicate subsets
     *
     * Algorithm:
     * - Sort an array to make a proper comparison of the duplicate elements, where
     *   comparison is happening by using the strings, so [2,1] and [1,2] should
     *   be considered as duplicates. In order to make it the subsets should be sorted.
     * - Initialize the maximum number of subsets that can be generated -> 2^n.
     * - Iterate from 0 to [maxNumberOfSubsets - 1]. The set bits in the binary representation
     *   of [mask] indicates the position of the elements in the nums array that are present
     *   in the current subset.
     * - Run an inner for loop from j = 0 to n - 1 to check the position of set bits in mask.
     *   If at the i[th] position bit is set, add nums[i] to the current subset, and append
     *   nums[i] to the [hashcode] string
     *
     * Time complexity:
     * - O(n * 2 ^ n).
     *
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;
        int total = 1 << n;

        var set = new HashSet<String>();
        for (int mask = 0; mask < total; mask++) {
            var hashcode = new StringBuilder();
            var curr = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    curr.add(nums[i]);
                    hashcode.append(nums[i]).append(",");
                }
            }
            if (!set.contains(hashcode.toString())) {
                set.add(hashcode.toString());
                res.add(curr);
            }
        }
        return res;
    }

    /*
     * Cascading:
     * - Sort an array
     * - Init the [subsetSize] = 0. [subsetSize] holds the index of the subset in the
     *   subsets from where we should start adding the current element if the
     *   current element is a [duplicate]. It holds the idx of the first subset
     *   generated in the previous step.
     * - Iterate over the list of the elements
     * - If we haven't seen the element before, add this element to ALL previously
     *   generated subsets. Set startingIdx = 0.
     * - If the current element is the [duplicate] element, add it only to the subsets
     *   created in the [previous] iteration. This means we will skip every subset that was
     *   created earlier than the previous iteration.
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        int subsetSize = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // If the previous element is duplicate, add it only to the ones created in the previou s step
            int startingIdx = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0;
            subsetSize = subsets.size();
            for (int j = startingIdx; j < subsetSize; j++) {
                List<Integer> currentSubset = new ArrayList<>(subsets.get(j));
                currentSubset.add(nums[i]);
                subsets.add(currentSubset);
            }
        }
        return subsets;
    }
}