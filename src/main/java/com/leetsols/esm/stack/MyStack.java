package com.leetsols.esm.stack;

import java.util.ArrayDeque;

public class MyStack {
    /*
     * Make a Stack using 2 Queues
     *
     * push()
     * - e.g. push(1), push(2), push(3), push(4)
     *  First Queue:
     *   tail -> 4,3,2,1 <- head
     *  Stack
     * - 4
     *   3
     *   2
     *   1
     * - pop()
     *  First Queue:
     *   tail -> 4,3,2,1 <- head
     *  Second Queue:
     *   - Use the second queue as a temporary space for the not removed elements
     *
     *   First Queue:
     *    tail -> 4,3,2,1 <-head
     * --------------------------------------------
     *    while (i < firstQueue.size - 1) {
     *     i++;
     *     secondQueue.push(firstQueue.pop);
     *
     *    secondQueue.pop();
     *
     *    i = 0;
     *    while (i < secondQueue.size()) {
     *     i++;
     *     firstQueue.push(secondQueue.pop());
     * --------------------------------------------
     *   Second Queue:
     *    tail -> 3,2,1 <- head
     *
     *  - remove from the tail of the queue, using the double linked list
     *    operation will be simplified as it has pointers to both tail and
     *    head of the list. Use the LinkedList, with general insertion at
     *    the end and removing from the head.
     */
    private final ArrayDeque<Integer> firstQueue;
    private final ArrayDeque<Integer> secondQueue;

    public MyStack() {
        firstQueue = new ArrayDeque<>();
        secondQueue = new ArrayDeque<>();
    }

    public void push(int x) {
        firstQueue.offer(x);
    }

    /*
     * Remove the elements from the first queue until the last element in it
     * FirstQueue
     * - 4,3,2,1
     * - remove the elements until 4
     *
     *
     * SecondQueue
     * -
     */
    public int pop() {
        if (firstQueue.size() == 1) return firstQueue.pop();
        while (firstQueue.size() != 1) {
            secondQueue.push(firstQueue.pop());
        }

        int res = firstQueue.pop();

        while (!secondQueue.isEmpty()) {
            firstQueue.push(secondQueue.pop());
        }
        return res;
    }

    public int top() {
        if (firstQueue.size() == 1) return firstQueue.peek();

        while (firstQueue.size() != 1) {
            secondQueue.push(firstQueue.pop());
        }

        int res = firstQueue.pop();
        secondQueue.push(res);

        while (!secondQueue.isEmpty()) {
            firstQueue.push(secondQueue.pop());
        }
        return res;
    }

    public boolean empty() {
        return firstQueue.isEmpty();
    }
}
