package com.leetsols.esm.bitmanipulation;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int mask = 0;
        for (int num : nums) {
            mask ^= num;
        }
        return mask;
    }

    /*
     * Math approach:
     * - Make a set without duplicates from the original array
     * - There are exactly [k] integers that have 3 occurrences in the array
     * - A single integer can be [y]
     * - Array will be [x(1), x(1), x(2), x(2), x(3), ..., x(k), y]
     * - Set will be [x(1), x(2), x(3), ..., x(k), y]
     * - Sum of the set will be
     *  - S(set) = x(1) + x(2) + x(3) + ... + x(k) + y
     *  - S(set) - y = x(1) + x(2) + x(3) + ... + x(k)
     * - Sum of the nums will be
     *  - S(nums) = 3 * x(1) + 3 * x(2) + 3 * x(3) + ... + 3 * x(k) + y
     *  - S(nums) = 3 * (x(1) + x(2) + ... + x(k)) + y
     *  - S(nums) = 3 * (S(set) - y) + y
     *  - S(nums) = 3 * S(set) - 3 * y + y
     *  - S(nums) = 3 * S(set) - 2 * y
     *  - y = (3 * S(set) - S(nums)) / 2
     */
    public int singleNumberTwo(int[] nums) {
        Set<Long> numsSet = new HashSet<>();
        long sumNums = 0;
        for (int num : nums) {
            numsSet.add((long) num);
            sumNums += num;
        }

        long sumSet = 0;
        for (long num : numsSet) sumSet += num;

        return (int) ((3 * sumSet - sumNums) / 2);
    }

    /*
     * XORing Approach:
     * - XOR does modulo 2 addition, but addition is performed bit-by-bit, so
     *   adding all bits at index [i] and taking modulo 2 will give us the
     *   i[th] bit of the loner. Thus, we can find the loner bit by bit.
     * - To compute the i[th] bit of the loner, we can add the i[th] bit of all
     *   integers and take modulo 3, this will give us the i[th] bit of the loner
     *  - To get the i[th] bit of the number, right shift the integer by [i] bits
     *    and & with 1. It will provide you the i[th] bit of the integer.
     *    bit = (number >> shift) & 1;
     *   - For each shift, we iterate over all integers in array and sum them to
     *     get the i[th] bit of the loner
     * - bitSum is the sum of i[th] bits of all integers in nums
     *  - the i[th] bit of the loner will be bitSum % 3
     */
    public int singleNumberTwo2(int[] nums) {
        // Loner
        int loner = 0;

        //Iterate over all bits
        for (int shift = 0; shift < 32; shift++) {
            int bitSum = 0;
            // For this bit, iterate over all integers
            for (int num : nums) {
                // Compute the bit of the num and add it to the bitSum
                bitSum += (num >> shift) & 1;
            }
            // Compute the bit of the loner and place it
            int bitLoner = bitSum % 3;
            loner = loner | (bitLoner << shift);
        }
        return loner;
    }

    /*
     * Description:
     * - 2 elements appears once, all others appears twice
     *
     * Approach:
     * - As there are several unique numbers in the array, if we XOR
     *   everything, we will come up with A(first unique) ^ B(second unique),
     * - Because the A and B are different we can differentiate them only by bits
     *   , at least there should be one bit that differentiate them
     * - We choose the rightmost bit 1 from the [totalXOR] and then can differentiate
     *   them by it, because [totalXOR] represents the differential map (e.g. the 3rd
     *   bit is 1, where 1 can be for A or for the B)
     * - Using this divider we can sort all the bits into 2 groups where one have it
     *   while others not.
     * - Apply the XOR on both groups, resulting in the unique numbers for our answer.
     */
    public int[] singleNumberThree(int[] nums) {
        int totalXOR = 0;
        for (int num : nums) totalXOR ^= num;

        int diffBit = totalXOR & -totalXOR;

        int firstUnique = 0;
        int secondUnique = 0;
        for (int num : nums) {
            if ((num & diffBit) != 0) firstUnique ^= num;
            else secondUnique ^= num;
        }
        return new int[]{firstUnique, secondUnique};
    }
}
