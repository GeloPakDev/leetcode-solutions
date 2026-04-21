package com.leetsols.esm.stack;

import java.util.Stack;

public class StackWithIncrement {
    private final Stack<Integer> stack;
    private final Stack<Integer> temp;
    private final int maxSize;
    private int currSize;

    public StackWithIncrement(int maxSize) {
        currSize = 0;
        this.maxSize = maxSize;
        stack = new Stack<>();
        temp = new Stack<>();
    }

    public void push(int x) {
        if (currSize < maxSize) {
            stack.push(x);
            currSize++;
        }
    }

    public int pop() {
        if (!stack.isEmpty()) {
            currSize--;
            return stack.pop();
        } else {
            return -1;
        }
    }

    /*
     * Increments the bottom [k] elements of the stack by [val], If there are
     * less than [k] elements in the stack, increment all elements in the stack.
     *
     * Insists us to use a temporary stack for storage of
     * k = 3
     * 4 1 + 100 4
     * 3 2 + 100 103
     * 2 3 + 100 102
     * 1 4       101
     *
     * if (stack.size() < k || currSize < ) -> increment all of them.
     * else
     *  don't increment first k elements
     *
     *  var temp = 0;
     *  while(idx < k)
     *   idx++;
     *   temp.push(stack.pop);
     *
     *  increment all other elements
     *
     *  idxTwo = 0;
     *  while (idx < stack.size - (stack.size - k)) {
     *   temp.push(stack.pop() + val)
     *
     * if k > maxSize
     * - update more elements than expected
     *
     * if k < maxSize && k < stack.size
     *
     * max = 4
     * stack.size = 2
     * k = 3
     * update all
     *
     * max = 5
     * stack.size = 4
     * k = 6
     * update all
     *
     * max = 5
     * stack.size = 4
     * k = 3
     * update only 3
     *
     * stacksize can never be larger than maxSize
     * if (k > stack.size || k > maxSize) -> update all
     * else if (k < maxSize) {
     *
     *
     */
    public void increment(int k, int val) {
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }

        int count = 0;
        // The first elements out of temp are the bottom elements of the stack
        while (!temp.isEmpty()) {
            int element = temp.pop();
            if (count < k) {
                element += val;
                count++;
            }
            stack.push(element);
        }
    }
}
