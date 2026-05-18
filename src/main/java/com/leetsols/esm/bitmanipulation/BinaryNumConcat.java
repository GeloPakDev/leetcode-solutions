package com.leetsols.esm.bitmanipulation;

public class BinaryNumConcat {
    /*
     * Description:
     * - Return the decimal value of the binary string formed by concatenating
     *   the binary representation of [1] to [n] in order.
     *
     * Algorithm:
     * - Transform the numbers from 1 to n into binary
     * - Concatenate binaries
     * - Transform the concatenation into decimal
     *
     * - result = (result << 1 | (binary.charAt(j) - '0')) % MOD;
     *  - To move the current value over by one "slot" to make room for a new bit,
     *    you multiply the by the base 2
     *  - Shift by 1 the existing bits to the left.
     *  - Adding (|) the next bit (0 | 1) fills that new empty space.
     *  - % MOD -> As int is 32 bit and long is 64 bit, extra bits will disappear
     *    resulting in the Arithmetic Overflow, so based on the Modular Arithmetic
     *    property:
     *
     *    - (A + B) mod M = ((A mod M) + (B mod M)) mod M
     *    - (A * B) mod M = ((A mod M) * (B mod M)) mod M
     *
     *    Shrink number at every step, and the final remainder will be exactly the
     *    same, as we would calculate max number first and then divided it at the
     *    end
     */
    public static int concatenatedBinary1(int n) {
        final int MOD = 1000000007;
        int result = 0;
        for (int i = 0; i <= n; i++) {
            String binary = Integer.toBinaryString(i);
            for (int j = 0; j < binary.length(); j++) {
                result = (result << 1 | (binary.charAt(j) - '0')) % MOD;
            }
        }
        return result;
    }

    /*
     * Bit Manipulation:
     * - Iterate from 1 -> n. For each number [i]
     *  - Find the length of the binary representation of the number
     *  - Update the result to [result << length | i] (shift the current number to the
     *    left by the length to have the space for the current number)
     */
    public int concatenatedBinary2(int n) {
        final int MOD = 1000000007;
        int length = 0;
        long result = 0;

        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) length++;

            result = ((result << length) | i) % MOD;
        }

        return (int) result;
    }
}