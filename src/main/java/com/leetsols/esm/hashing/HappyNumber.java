package com.leetsols.esm.hashing;

import java.util.HashSet;
import java.util.Set;

/*
 * Problem type: Hash Table, Math, Two Pointers
 * Number: 202 Happy Number
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        return isHappyChecker(n, new HashSet<>());
    }

    public boolean isHappyChecker(int n, Set<Integer> seen) {
        if (n == 1) return true;
        if (seen.contains(n)) return false;

        seen.add(n);
        int res = getDigits(n);

        return isHappyChecker(res, seen);
    }

    public int getDigits(int n) {
        int res = 0;
        while (n > 0) {
            int digit = n % 10;
            res += (int) Math.pow(digit, 2);
            n = n / 10;
        }
        return res;
    }
}
