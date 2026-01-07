package com.leetsols.esm.stack;

import com.leetsols.esm.hashing.Pair;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem type: Stack, Design
 * Number: 155. Min Stack
 */
public class MinStack {
    private final List<Pair<Long, Long>> stack;

    public MinStack() {
        stack = new ArrayList<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.add(new Pair<>((long) val, (long) val));
        } else {
            // Calculate the minimum between the new value and the current minimum
            long currentMin = getMin();
            long newMin = Math.min(val, currentMin);
            stack.add(new Pair<>((long) val, newMin));
        }
    }

    public void pop() {
        stack.removeLast();
    }

    public long top() {
        return stack.getLast().getKey();
    }

    public long getMin() {
        return stack.getLast().getValue();
    }
}
