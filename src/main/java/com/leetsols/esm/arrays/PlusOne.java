package com.leetsols.esm.arrays;

import java.math.BigInteger;

/*
 * Problem type: Array, Math
 * Number: 66. Plus One
 */
public class PlusOne {
    /*
     * - length of an array can exceed 100
     * - no any leading 0's
     * - increment an integer by one and return the result array
     * - 123 + 1 = 124
     * - 9 + 1 = 10
     * - 19 + 1 = 20
     * - 999 + 1 = 1000
     * - 72299 + 1 = 72300
     */
    public int[] plusOne(int[] digits) {
        StringBuilder temp = new StringBuilder();
        for (int digit : digits) {
            temp.append(digit);
        }
        BigInteger number = new BigInteger(temp.toString());

        return String.valueOf(number.add(BigInteger.ONE))
                .chars().map(c -> c - '0')
                .toArray();
    }

    /*
     * - Move along the array starting from the end of the array
     * - Set all nines at the end of the array to 0
     * - If the non-nine digit is met, increase it by one, return digits
     *
     * - In case if all the digits are 9, append digit 1 in front of the digits
     */
    public int[] plusOneTwo(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }

        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}
