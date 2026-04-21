package com.leetsols.esm.math;

public class Pow {
    public double myPow(double x, int n) {
        return helper(x, n, 1);
    }

    public double helper(double x, int n, int i) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (i > n) return x;
        return helper(x * x, n, i + 1);
    }
}
