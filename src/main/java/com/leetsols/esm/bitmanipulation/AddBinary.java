package com.leetsols.esm.bitmanipulation;

import java.math.BigInteger;

public class AddBinary {
    /*
     * Bit Manipulation:
     * - XOR for 2 numbers without taking into account the carry
     * - Find the current carry, it's AND of 2 input numbers, shifted one bit
     *   to the left.
     * - Find the sum of answers with/out the carry.
     * - We check the carry != 0 to immitate the "carrying the one" accross
     *   multiple columns
     *   Example:
     *    - 999 + 1
     *    - First, the 9 becomes 0 and carries to the next 9.
     *    - Then that 9 becomes 0 and carries to the next 9.
     *    - Then that 9 becomes 0 and carries to a new column.
     * -
     */
    public String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;

        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }
}