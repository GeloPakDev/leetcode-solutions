package com.leetsols.esm.hashing;

/*
 * Problem type: Array, Hashtable, Sorting, Math, BinarySearch
 * Number: 268 Missing Number
 */
public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int length = nums.length;
        int hashLength = length + 1;
        int[] hashtable = new int[hashLength];
        for (int index : nums) {
            hashtable[index] += 1;
        }

        for (int i = 0; i < hashLength; i++) {
            if (hashtable[i] == 0) {
                return i;
            }
        }
        return 0;
    }
}
