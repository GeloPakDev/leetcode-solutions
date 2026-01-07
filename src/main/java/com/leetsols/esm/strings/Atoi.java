package com.leetsols.esm.strings;

/*
 * Problem type: String
 * Number: 8. String to Integer (atoi)
 */
public class Atoi {
    /*
     * This fucking problem consider many edge cases
     * Whitespaces
     * - Discards at the start
     * - Stop iteration and return the int
     *
     * Digits
     * - Discards leading zeros
     * - Stop if non-digit character occurred
     * - If no digits found, return 0
     *
     * Sign
     * - + or no sign in the beginning -> positive
     * - - first non-negative -> will be negative
     * - anywhere in the string -> return the int
     *
     * Algorithm:
     * - Initialize 2 variables, sign and result
     * - Skip all leading whitespaces
     * - Check if the current char + or -
     *  - If there is no symbol or character is + -> positive -> sign = 1
     *  - Otherwise sign - -> -1
     * - Iterate over the string as long as curr char is not letter or any other sign
     *  - Before appending the currently selected digit check over/under flow
     *  - Otherwise continue appending digit to the result
     */
    public int myAtoi(String s) {
        int sign = 1;
        int res = 0;
        int index = 0;
        int n = s.length();

        // Skip all whitespace characters
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // sign = +1
        if (index < n && s.charAt(index) == '+') {
            index++;
        } else if (index < n && s.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            if ((res > Integer.MAX_VALUE / 10) ||
                    (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = 10 * res + digit;
            index++;
        }

        return res * sign;
    }
}
