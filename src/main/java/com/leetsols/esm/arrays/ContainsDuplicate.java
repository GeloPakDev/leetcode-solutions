package com.leetsols.esm.arrays;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem type: Array, Hashtable, Sorting
 * Number: 217 Contains Duplicate, 219 Contains Duplicate, 220 Contains Duplicate
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        var maps = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!maps.containsKey(num)) {
                maps.putIfAbsent(num, 0);
            } else {
                return true;
            }
        }
        return false;
    }

    /*
     * Problem paraphrasing:
     * - Find out the duplicate elements in the array such that their
     *   distance between each other is less than or equals K
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        var maps = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!maps.containsKey(nums[i])) {
                maps.putIfAbsent(nums[i], i);
            } else {
                if (Math.abs(maps.get(nums[i]) - i) <= k) return true;
                else maps.put(nums[i], i);
            }
        }
        return false;
    }

    /*
     * Description:
     * - Find a pair of indices:
     *  - i != j
     *  - abs(i - j) <= indexDiff
     *  - abs(nums[i] - nums[j]) <= valueDiff
     *
     * Algorithm:
     * - Each element is the person's birthday. Your birthday is some day in March,
     *   is the new element [x], suppose that each month has 30 days, and we want to
     *   know if there are anyone who has birthday within the 30 days as mine. We
     *   can immediately remove all other months except the April, March and February.
     *   A range covered by buckets are the same as distance [t] which simplifies the
     *   things a lot.
     *
     *
     * - In order to find out the 2 indices [i] and [j] such that:
     *  - Index distance: [i - j] <= k
     *  - Value distance: [nums[i] - nums[j]] <= t
     * - Each bucket is having the width of [t + 1]
     *  - Any 2 numbers in the same bucket already satisfy the condition of
     *    [nums[i] - nums[j]] <= t
     *  - If a number is in a neighboring bucket, we just need to check if
     *    the difference is within [t]
     * -
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length < 2 || indexDiff <= 0 || valueDiff < 0) return false;

        // Map to store one element per bucket
        Map<Long, Long> buckets = new HashMap<>();

        // We use long to avoid the integer buffer overflow
        long width = (long) valueDiff + 1;
        for (int i = 0; i < nums.length; i++) {
            long val = nums[i];
            long bucketId = getId(val, width);

            // Case 1: Bucket already contains a value, difference is definitely <= valueDiff
            if (buckets.containsKey(bucketId)) return true;

            // Case 2: Check left neighbor bucket
            if (buckets.containsKey(bucketId - 1) &&
                    Math.abs(val - buckets.get(bucketId - 1)) <= valueDiff) return true;

            // Case 2: Check right neighbor bucket
            if (buckets.containsKey(bucketId - 1) &&
                    Math.abs(val - buckets.get(bucketId + 1)) <= valueDiff) return true;

            // Add current value to the bucket
            buckets.put(bucketId, val);

            // Maintain the sliding window of size indexDiff
            if (i >= indexDiff) {
                long lastBucketId = getId(nums[i - indexDiff], width);
                buckets.remove(lastBucketId);
            }
        }
        return false;
    }

    public long getId(long x, long width) {
        return Math.floorDiv(x, width);
    }
}
