package com.leetsols.esm.bitmanipulation;

public class ReverseInteger {
    /*
     * Description
     * - Given the 32-bit integer [x], return [x] with its digits reversed.
     * - If reversing makes the integer to overflow, return -1 as a result
     *
     * Algorithm:
     * - N = d(n) * 10^n + d(n - 1) * 10^(n - 1) + ... + d(0) * 10^0.
     * - N(reversed) = d(0) * 10 ^ (n) + d(1) * 10^(n - 1) + ... + d(n) * 10^0
     *
     */
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int curr = x % 10;
            res = (res * 10) + curr;
            x /= 10;
            System.out.println(x);
        }

        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;

        return (int) res;
    }
}