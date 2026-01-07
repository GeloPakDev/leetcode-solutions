package com.leetsols.esm.strings;

import java.util.ArrayList;

/*
 * Problem type: String
 * Number: 6 ZigZag Conversion
 */
public class ZigZag {
    /*
     * PAYPALISHIRING
     * numRow = 4
     *
     * P    I    N -> PIN
     * A  L S  I G -> ALSIG
     * Y A  H R    -> YAHR
     * P    I      -> PI
     * PINALSIGYAHRPI
     *
     * Algorithm:
     * - If the number of rows equals one or exceeds the length of the string -> the res is initial string
     * - If the ZigZag rows are indexed, each row corresponds to complete string,
     *   instead of making manipulations around the string, ZigZag structure will be built by
     *   making the rows on each level and filling these rows by moving upwards and downwards in the
     *   ZigZag
     */
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows > s.length()) {
            return s;
        }

        var rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        /*
         * Move the currRow up/downward based on the currRow value
         *  - currRow = 0           -> top need to go DOWN
         *  - currRow = numRows - 1 -> bottom need to go UP
         */
        int currRow = 0;
        int step = 1;
        for (char ch : s.toCharArray()) {
            rows.get(currRow).append(ch);

            currRow += step;
            if (currRow == 0 || currRow == numRows - 1) {
                step = -step;
            }
        }

        var res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
