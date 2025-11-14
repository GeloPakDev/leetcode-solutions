package com.leetsols.esm.queue;

import java.util.ArrayDeque;
import java.util.Deque;

class MovingAverage {
    private final Deque<Integer> window;
    private final int size;
    private int sum = 0;

    public MovingAverage(int size) {
        this.size = size;
        window = new ArrayDeque<>();
    }

    public double next(int val) {
        window.add(val);
        int first = window.size() > size ? window.poll() : 0;
        sum += val - first;
        return 1.0 * sum / window.size();
    }
}