package com.leetsols.esm.arrays;

import java.util.HashMap;

public class RomanToInt {
    /*
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     */
    public int romanToInt(String s) {
        var map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        var sum = 0;
        var start = 0;
        while (start < s.length()) {
            if (start < s.length() - 1) {
                String doubleSymbol = s.substring(start, start + 2);
                // 2 symbol case
                if (map.containsKey(doubleSymbol)) {
                    sum += map.get(doubleSymbol);
                    start += 2;
                    continue;
                }
            }

            String singleSymbol = s.substring(start, start + 1);
            sum += map.get(singleSymbol);
            start += 1;
        }
        return sum;
    }
}
