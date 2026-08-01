package com.leetsols.esm.strings;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits {
    /*
     * Approach:
     * - Construct the first number in the range of the low and high (10 < low < high < 10^9)
     *  - Identify the quantity of the digits for the initial number by [low] boundary
     *  - 1000 -> 1234, 1234 -> 2345
     *  - 1457, 1 < 4 -> means we should take larger digit -> 2345
     *  - 1291, 1 <= 1 -> T, 2 <= 2 -> T, 9 <= 3 -> F, chain is broken, take the larger value
     *    for the first digit -> 1291 -> 2345
     *  - 1239 -> 9 <= 4 -> False -> 124 -> 4 <= 3 -> False ->
     *  -
     *  - As far as the sequence of the construction is broken, we have to change the first
     *    digit of the construction number.
     *
     *  - Take first digit
     *
     * If the curr digit < low[i] && number < high -> continue construction
     */
    public List<Integer> sequentialDigits(int low, int high) {
        var res = new ArrayList<Integer>();

        int curr = getBaseLowNumber(low);

        while (curr <= high && curr != -1) {

            if (curr >= low) res.add(curr);

            curr = getNextSequential(curr);
        }
        return res;
    }

    private int getNextSequential(int val) {
        String s = String.valueOf(val);
        int len = s.length();
        int lastDigit = val % 10;

        // If the last digit is 9, we cannot increment further at this length (e.g., 6789)
        // We must transition to the next length, starting with 1 (e.g., 12345)
        if (lastDigit == 9) {
            if (len >= 9) {
                return -1; // Exceeded the maximum possible sequential integer length
            }
            return (int) constructSequential(1, len + 1);
        }

        // Otherwise, simply shift the window.
        // To turn 123 -> 234, we add 111.
        // We can generate this adder dynamically using the length.
        int adder = 0;
        for (int i = 0; i < len; i++) {
            adder = adder * 10 + 1;
        }

        return val + adder;
    }

    public int getBaseLowNumber(int low) {
        int[] num = getNumericalForm(low);
        int n = String.valueOf(low).length();
        boolean chainIsBroken = false;
        int brokenIdx = -1;

        for (int i = 1; i < n; i++) {
            if (num[i] - num[i - 1] != 1) {
                chainIsBroken = true;
                brokenIdx = i;
                break;
            }
        }

        if (!chainIsBroken) return low;

        int startDig = num[0];

        long idealWithSameStart = constructSequential(startDig, n);

        int finalStartDigit;
        if (low > idealWithSameStart) finalStartDigit = startDig + 1;
        else finalStartDigit = startDig;

        if (startDig + n - 1 > 9) return (int) constructSequential(1, n + 1);
        return (int) constructSequential(finalStartDigit, n);
    }

    private long constructSequential(int start, int len) {
        long value = 0;
        int currDigit = start;
        for (int i = 0; i < len; i++) {
            value = value * 10 + currDigit;
            currDigit++;
        }
        return value;
    }

    public int[] getNumericalForm(int low) {
        String s = String.valueOf(low);
        int n = s.length();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) num[i] = s.charAt(i) - '0';
        return num;
    }
}
