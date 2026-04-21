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
}
