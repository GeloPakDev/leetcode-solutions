package com.leetsols.esm.strings;

public class ReverseDigits {
    /*
     * Repeatedly extract last digit of input number using the modulus operator
     * (num % 10) and appending to the reverse number. After extracting the digit
     * input number is reduced by diving it by 10 (n = n / 10).
     */
    public int reverseNumber(int num) {
        int tem = 0;
        while (num != 0) {
            tem = tem * 10 + num % 10;
            num = num / 10;
        }
        return tem;
    }
}

