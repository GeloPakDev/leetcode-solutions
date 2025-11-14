package com.leetsols.esm.greedy;

public class MaximumNumber {
    /*
     * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
     */
    public int maximum69Number(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(num);

        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '6') {
                stringBuilder.setCharAt(i, '9');
                break;
            }
        }
        return Integer.parseInt(stringBuilder.toString());
    }
}
