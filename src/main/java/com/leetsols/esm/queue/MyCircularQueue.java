package com.leetsols.esm.queue;

public class MyCircularQueue {
    private int[] queue;
    private int headIndex;
    private int count;
    private int capacity;

    public MyCircularQueue(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
    }

    /*
     * count     -> quantity elements in the array
     * headIndex -> indicator of the start of the sequence
     *
     * (headIndex + count) % capacity -> provides an index where to insert an element
     *
     */
    public boolean enQueue(int value) {
        if (this.count == this.capacity) return false;

        this.queue[(this.headIndex + this.count) % this.capacity] = value;
        this.count += 1;
        return true;
    }

    public boolean deQueue() {
        if (this.count == 0) return false;
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count -= 1;
        return true;
    }

    public int Front() {
        if (this.count == 0) return -1;
        return this.queue[this.headIndex];
    }

    /*
     * % -> no matter how high addition goes, result is always a valid index the array bounds
     *
     * tailIndex = (headIndex + count - 1) % capacity
     * headIndex + count - 1 -> point at the last element
     * % capacity            -> if tail moves beyond the end of the array, it wraps back
     *
     * Example:
     * [_, _, A, B, C]
     * headIndex = 2
     * count = 3
     * (2 + 3 - 1) % 5 -> 4 % 5 = 4
     *
     * [D, E, A, B, C]
     * headIndex = 2
     * count = 5
     * (2 + 5 - 1) % 5 = 1
     */
    public int Rear() {
        if (this.count == 0) return -1;
        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.queue[tailIndex];
    }

    public boolean isEmpty() {
        return (this.count == 0);
    }

    public boolean isFull() {
        return (this.count == this.capacity);
    }
}
