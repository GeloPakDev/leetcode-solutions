package com.leetsols.esm.greedy;

/*
 * Problem type: Math, Greedy
 * Number: 670. Maximum Swap
 */
public class MaximumSwap {
    /*
     * The "Rightmost" Rule
     * In your inner loop, if you find a digit that is the "max," you record its index.
     * However, if there are multiple instances of that same maximum digit further down the string,
     * you must swap with the last (rightmost) one to get the largest possible number.
     *
     * Example: 199
     * - If you swap the 1 with the first 9, you get 919.
     * - If you swap the 1 with the last 9, you get 991.
     * - 991 > 919.
     *
     * Find the Largest Digit: Your inner loop should search for the absolute maximum digit
     * available to the right. If that maximum digit appears multiple times, you must pick
     * the last index to ensure the swap results in the highest value.
     */
    public int maximumSwap(int num) {
        var arr = String.valueOf(num).toCharArray();

        for (int i = 0; i < arr.length; i++) {
            int maxIndex = i;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            if (maxIndex != i) {
                swap(arr, i, maxIndex);
                return Integer.parseInt(String.valueOf(arr));
            }
        }
        return num;
    }

    public void swap(char[] arr, int start, int end) {
        char temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    /*
     * Make 2 pass over the number
     * - In the first pass scan from right to left to identify and store the largest digit
     * - In the second pass, move from left to right,
     */
    public int maximumSwapTwo(int num) {
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;

        /*
         * For every position, it tells the index of the "best"
         * (largest and rightmost) digit available from that point forward.
         *
         * Example: 2736
         * At i=2 (Digit '3')
         * Compare numArr[2] (3) with the "best so far" at maxRightIndex[3] (which is '6').
         * Current maxRightIndex: [?, ?, 3, 3]
         *
         * At i=1 (Digit '7')
         * Compare numArr[1] (7) with the "best so far" at maxRightIndex[2] (which is '6').
         * Current maxRightIndex: [?, 1, 3, 3]
         *
         * At i=0 (Digit '2')
         * Compare numArr[0] (2) with the "best so far" at maxRightIndex[1] (which is '7').
         * Final maxRightIndex: [1, 1, 3, 3]
         */
        int[] maxRightIndex = new int[n];
        maxRightIndex[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            maxRightIndex[i] = (numArr[i] > numArr[maxRightIndex[i + 1]]) ? i : maxRightIndex[i + 1];
        }

        for (int i = 0; i < n; i++) {
            if (numArr[i] < numArr[maxRightIndex[i]]) {
                char temp = numArr[i];
                numArr[i] = numArr[maxRightIndex[i]];
                numArr[maxRightIndex[i]] = temp;
                return Integer.parseInt(new String(numArr));
            }
        }
        return num;
    }
}
