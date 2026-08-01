package com.leetsols.esm.math;

public class ConcatNonZeroDigits {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;
        var compressed = Long.toString(n);
        var x = new StringBuilder();
        long sum = 0;
        for (int i = 0; i < compressed.length(); i++) {
            var curr = compressed.charAt(i);
            if (curr != '0') {
                x.append(curr);
                sum += Integer.parseInt(String.valueOf(curr));
            }
        }

        return Long.parseLong(x.toString()) * sum;
    }
}
