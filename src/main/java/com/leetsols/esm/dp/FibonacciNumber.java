package com.leetsols.esm.dp;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {
    private final Map<Integer, Integer> map = new HashMap<>();

    public int fib(int n) {
        if (map.containsKey(n)) map.get(n);

        int res = 0;
        if (n < 2) res = n;
        else res = fib(n - 1) + fib(n - 2);
        map.put(n, res);
        return res;
    }

    public int tribonacci(int n) {
        if (map.containsKey(n)) return map.get(n);

        int res = 0;
        if (n == 1 || n == 2) res = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        map.put(n, res);
        return res;
    }
}
