package com.leetsols.esm.strings;

public class StrImpl {
    private static final int RADIX_1 = 26;
    private static final int MOD_1 = 1000000033;
    private static final int RADIX_2 = 27;
    private static final int MOD_2 = 2147483647;

    /*
     * - Return the index of the first occurrence of substring in the string
     * - Return -1 if substring is not part of the string
     *
     * - If haystack and needle letter matches move both pointers
     * - If the haystack letter doesn't match the needle letter, make the needlePointer = 0
     * - If the length of the needle letter exceeds -> substring matches with string -> return the index of the first occurrence
     */
    public static int strStr(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();
        for (int windowStart = 0; windowStart <= n - m; windowStart++) {
            for (int i = 0; i < m; i++) {
                if (needle.charAt(i) != haystack.charAt(windowStart + i)) {
                    break;
                }
                if (i == m - 1) {
                    return windowStart;
                }
            }
        }
        return -1;
    }

    /*
     * TODO: MISUNDERSTANDABLE
     */
    public static int strStrKMP(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();

        if (n < m) return -1;

        int[] longestBorder = new int[m];
        int prev = 0;
        int i = 1;

        while (i < m) {
            if (needle.charAt(i) == needle.charAt(prev)) {
                prev++;
                longestBorder[i] = prev;
                i++;
            } else {
                if (prev == 0) {
                    longestBorder[i] = 0;
                    i++;
                } else {
                    prev = longestBorder[prev - 1];
                }
            }
        }

        int haystackPtr = 0;
        int needlePtr = 0;

        while (haystackPtr < n) {
            if (haystack.charAt(haystackPtr) == needle.charAt(needlePtr)) {
                needlePtr++;
                haystackPtr++;

                if (needlePtr == m) return haystackPtr - m;
            } else {
                if (needlePtr == 0) haystackPtr++;
                else needlePtr = longestBorder[needlePtr - 1];
            }
        }

        return -1;
    }

    public int hashValue(String input, int RADIX, int MOD, int m) {
        long ans = 0;
        long factor = 1;
        for (int i = m - 1; i >= 0; i--) {
            ans = (ans + (input.charAt(i) - 'a') * factor) % MOD;
            factor = (factor * RADIX) % MOD;
        }
        return (int) ans;
    }

    public long[] hashPair(String input, int m) {
        long hash1 = 0, hash2 = 0;
        long factor1 = 1, factor2 = 1;

        for (int i = m - 1; i >= 0; i--) {
            hash1 += ((input.charAt(i) - 'a') * (factor1)) % MOD_1;
            factor1 = (factor1 * RADIX_1) % MOD_1;
            hash2 += ((input.charAt(i) - 'a') * (factor2)) % MOD_2;
            factor2 = (factor2 * RADIX_2) % MOD_2;
        }
        return new long[]{hash1 % MOD_1, hash2 % MOD_2};
    }

    public int strStrRabinKarpDoubleHash(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();

        if (n < m) return -1;

        long MAX_WEIGHT_1 = 1;
        long MAX_WEIGHT_2 = 1;
        for (int i = 0; i < m; i++) {
            MAX_WEIGHT_1 = (MAX_WEIGHT_1 * RADIX_1) % MOD_1;
            MAX_WEIGHT_2 = (MAX_WEIGHT_2 * RADIX_2) % MOD_2;
        }

        long[] hashNeedle = hashPair(needle, m);
        long[] hashHay = {0, 0};

        for (int winStart = 0; winStart <= n - m; winStart++) {
            if (winStart == 0) hashHay = hashPair(haystack, m);
            else {
                int curr = haystack.charAt(winStart + m - 1) - 'a';
                hashHay[0] = (((hashHay[0] * RADIX_1) % MOD_1) - (((haystack.charAt(winStart - 1) - 'a') * MAX_WEIGHT_1) % MOD_1) +
                        curr + MOD_1) % MOD_1;
                hashHay[1] = (((hashHay[1] * RADIX_2) % MOD_2) - (((haystack.charAt(winStart - 1) - 'a') * MAX_WEIGHT_2) % MOD_2) +
                        curr + MOD_2) % MOD_2;
            }

            if (hashNeedle[0] == hashHay[0] && hashNeedle[1] == hashHay[1]) return winStart;
        }
        return -1;
    }

    public int strStrRabinKarp(String haystack, String needle) {
        int m = needle.length();
        int n = haystack.length();
        if (n < m) return -1;

        // CONSTANTS
        int RADIX = 26;
        int MOD = 1000000033;
        long MAX_WEIGHT = 1;

        for (int i = 0; i < m; i++) MAX_WEIGHT = (MAX_WEIGHT * RADIX) % MOD;

        // Compute hash of needle
        long hashNeedle = hashValue(needle, RADIX, MOD, m), hashHay = 0;

        // Check for each m-substring of haystack, starting at index windowStart
        for (int windowStart = 0; windowStart <= n - m; windowStart++) {
            if (windowStart == 0)// Compute hash of the First Substring
                hashHay = hashValue(haystack, RADIX, MOD, m);
            else
                // Update Hash using Previous Hash Value in O(1)
                hashHay = (((hashHay * RADIX) % MOD) -
                        (((haystack.charAt(windowStart - 1) - 'a') * MAX_WEIGHT) % MOD) +
                        (haystack.charAt(windowStart + m - 1) - 'a') + MOD) % MOD;

            // If the hash matches, Check Character by Character.
            // Because of Mod, spurious hits can be there.
            if (hashNeedle == hashHay) {
                for (int i = 0; i < m; i++) {
                    if (needle.charAt(i) != haystack.charAt(i + windowStart)) break;
                    if (i == m - 1) return windowStart;
                }
            }
        }
        return -1;
    }

    public static int[] computePrefixFunction(String input) {
        int m = input.length();
        int[] pi = new int[m];
        pi[0] = 0;// single character can't have a proper prefix
        /*
         * - it holds the length of the longest prefix of pattern currently
         *   known to also be a suffix ending
         * - every successful comparison increments [k] by one, as the matched
         *   region just grew,
         * - every failed comparison fallbacks to k = pi[k - 1], and it doesn't
         *   throw the progress away, it jumps to the best overlap which already
         *   proven to exist.
         */
        int k = 0;

        // Outer loop, pointing at the new character being processed
        for (int i = 1; i < m; i++) {
            // Fallback chain, each time a comparison fails, [k = pi[k - 1]] shrinks [k] to the next-best candidate
            while (k > 0 && input.charAt(i) != input.charAt(k)) k = pi[k - 1];
            // If characters match, overlap grows by one
            if (input.charAt(i) == input.charAt(k)) k++;
            //Assign the current
            pi[i] = k;
        }
        return pi;
    }
}