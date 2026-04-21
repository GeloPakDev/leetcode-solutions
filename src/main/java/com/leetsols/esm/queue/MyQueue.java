package com.leetsols.esm.queue;

import java.util.Stack;

public class MyQueue {

    /*
     * Algorithm:
     * - As we push to the stack
     * Queue view:
     *  1,2,3,4 -> 4,3,2,1 <- head (here we will remove and peek)
     *
     *
     *  4,3,2,1 -> the last element in the stack (4) is the tail of the queue,
     *             but we should remove the bottom element in the stack for the
     *             pop operation (1).
     * one two
     * 4   1
     * 3   2
     * 2   3
     * 1   4
     *
     * - push
     *  Every time we are adding an element to the first stack take the peek element
     *  (the one we have added into another stack) and push it to the second stack
     *  for the pop and peek behavior.
     * - push
     *
     *
     * push(1), push(2)
     * 2 1
     * 1 2
     *
     * pop() -> - pop() all current elements from the first stack
     *          - push all of them into the second one
     *          - pop() the last element from the stack
     * e.g
     * - push(1), pop()
     * 1 -> pop() -> push(stack1.pop()) -> stack.pop()
     * - push(1), push(2), pop(), pop(), pop()
     * 2 push(2) -> pop() -> 1 -> pop()
     * 1 push(1)             2
     * - push(1), push(2), pop(), push(3), pop()
     *  2 -> pop() -> 1 -> 2 is left -> push(3)
     *  1             2
     * - pop()
     *  - check if the second stack isEmpty before pop()
     *   - [false] keep removing from the second stack
     *   - [true] it means that all previous push()'s has been exhausted
     *    we should fill the second stack for removal with a new one
     * - isEmpty()
     *  - If after all pop() there are some elements left in the first rack
     *    check it also for the size
     *    if (stack1.isEmpty() && stack2.isEmpty())
     * - peek()
     *  - check if the second stack isEmpty(), as in the condition the operation is always valid
     *    move all elements from the first stack to the second one and peek()
     */

    private final Stack<Integer> stackOne;
    private final Stack<Integer> stackTwo;

    public MyQueue() {
        stackOne = new Stack<>();
        stackTwo = new Stack<>();
    }

    public void push(int x) {
        stackOne.push(x);
    }

    public int pop() {
        if (stackTwo.isEmpty()) {
            while (!stackOne.isEmpty()) stackTwo.push(stackOne.pop());
        }
        return stackTwo.pop();
    }

    public int peek() {
        if (stackTwo.isEmpty()) {
            while (!stackOne.isEmpty()) stackTwo.push(stackOne.pop());
        }
        return stackTwo.peek();
    }

    public boolean empty() {
        return stackTwo.isEmpty() && stackOne.isEmpty();
    }
}
