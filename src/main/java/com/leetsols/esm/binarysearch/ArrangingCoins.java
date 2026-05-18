package com.leetsols.esm.binarysearch;

public class ArrangingCoins {
    /*
     * Description:
     * - You have [n] coins, and you want to build a staircase with these coins.
     * - The staircase consists of the [k] rows, where the i[th] row has exactly
     *   [i] coins, the last row may be incomplete.
     * - With each row the number of elements in it increases, so each time we are
     *   adding a new element and progressively checking how much is left to build
     *   a full row.
     *
     * - Outer loop for iterating over the element
     * - Inner loop for building the row.
     *
     * Each row is in the strictly increasing order it means that the list should become
     * sorted in the end. We can apply the binary search on it to check when the list
     * will start to decrease? Firstly we have to make a list that is gonna make a proper
     * list.
     *
     * Binary search
     * - Search for the most ce
     *
     * - Hypotatical space is the one declared between the 0 and n \
     * - 8
     * - 1       -> 1
     * - 1 1     -> 3
     * - 1 1 1   -> 6
     * - 1 1 1 1 -> 10
     *
     * - S(n) = n(n + 1)/2
     * - 3 = 3 ( 3 + 1 ) / 2 = 3 * 2 = 6
     * - 4 = 4 ( 4 + 1 ) / 2 = 4 * 2.5 = 10
     *  - if canDo == false -> for particular i from the range of i to n
     *                         the sum of [i] is greater than n
     */
    public int arrangeCoins(int n) {
        return Math.toIntExact(binarySearch(1, n, n));
    }

    public long binarySearch(long low, long high, long target) {
        while (low < high) {
            long middle = low + (high - low + 1) / 2;
            if (arithmeticSum(middle) <= target) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        return low;
    }

    public double arithmeticSum(long n) {
        return (double) (n * (n + 1)) / 2;
    }
}