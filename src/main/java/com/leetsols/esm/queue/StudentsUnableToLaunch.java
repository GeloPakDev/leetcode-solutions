package com.leetsols.esm.queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Objects;

public class StudentsUnableToLaunch {
    /*
     * How do we know that none of the students in the queue want to take the
     * top sandwich?
     * - Keep track of the [lastServed] student, that indicates when we were
     *   not able to serve the student. When it reaches the size of the queue
     *   it means we top sandwich didn't match to all the students in the
     *   queue.
     * - After serving all the students, remaining students in the queue is
     *   our answer.
     */
    public static int countStudents(int[] students, int[] sandwiches) {
        int length = students.length;
        var stack = new LinkedList<Integer>();
        var queue = new ArrayDeque<Integer>();
        for (int i = 0; i < length; i++) {
            stack.push(sandwiches[length - i - 1]);
            queue.offer(students[i]);
        }

        int lastServed = 0;
        while (!queue.isEmpty() && lastServed < queue.size()) {
            if (Objects.equals(stack.peek(), queue.peek())) {
                stack.pop();
                queue.poll();
                lastServed = 0;
            } else {
                queue.offer(queue.poll());
                lastServed++;
            }
        }
        return queue.size();
    }
}
