package com.leetsols.esm.prefixsum;

public class PrefixSum {
    public static int[] prefix(int[] num) {
        var prefixSum = new int[num.length];
        prefixSum[0] = num[0];
        for (int i = 1; i < num.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + num[i];
        }
        return prefixSum;
    }

    public static int[] suffix(int[] num, int k) {
        var length = num.length;
        var suffix = new int[length];
        suffix[length - 1] = num[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            suffix[i] = num[i] + suffix[i + 1];
        }
        return suffix;
    }

    public static int suffixTwo(int[] num, int start, int end) {
        var sum = 1;
        for (int i = start; i < end; i++) {
            sum *= num[start];
        }
        return sum;
    }
}