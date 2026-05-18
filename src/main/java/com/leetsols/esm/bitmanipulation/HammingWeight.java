package com.leetsols.esm.bitmanipulation;

public class HammingWeight {
    /*
     * Hamming Weight:
     * - Count how many bits in a number is set 1
     * - If the last bit in a number == 1 count ++;
     *  - Apply & operator on the number if the bit of the number == 1, result === true
     *    otherwise false.
     * - After each iteration right shift the number
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) count++;
            n >>= 1;
        }
        return count;
    }
}
