package com.leetsols.esm.bitmanipulation;

public class HammingDistance {
    public static int totalHammingDistance(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                sum += hammingDistance(nums[i], nums[j]);
            }
        }
        return sum;
    }

    public static int hammingDistance(int numOne, int numTwo) {
        int xor = numOne ^ numTwo;
        return Integer.bitCount(xor);
    }

    /*
     * Column of Bits:
     * - Each integer in Java has 32 bit positions. For any specific bit position (e.g., the 5th bit):
     * - Suppose [k] numbers have a 1 at that position.Then (n - k) numbers must have a 0 at that position.
     *
     * Hamming Distance is a count of how many times bits are different between pairs.
     *
     * Total Hamming Distance is just the sum of the "different pairs" in the:
     * - 1st column + "different pairs"
     * - 2nd column + ... up to the 32nd column.
     */
    public int totalHammingDistance1(int[] nums) {
        int total = 0;
        int n = nums.length;

        // Iterate through each of the 32 bit positions
        for (int i = 0; i < 32; i++) {
            int countOnes = 0;

            // Count how many numbers have 1 at the i-th bit
            for (int num : nums) countOnes += (num >> i) & 1;

            // Count how many have 0 (Total size - ones)
            int countZeros = n - countOnes;

            // Each 1 pairs with each 0 to create a distance of 1
            total += countOnes * countZeros;
        }
        return total;
    }
}
