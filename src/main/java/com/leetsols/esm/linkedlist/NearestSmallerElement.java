package com.leetsols.esm.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class NearestSmallerElement {
    /*
     * Nearest Smaller Element:
     * - A[j] for every element A[i], such that [j < i && A[j] < A[i]]
     * - if no such element exists return -1
     * - A = [4,  5, 2, 10, 8]
     * - G = [-1, 4, -1, 2, 2]
     * - Make an increasing monotonic stack
     * - If (peek < curr) -> keep poping the elements from the stack until the
     *   larger element is found, if there is no element left in the stack, put -1 in
     *   the result array;
     */
    public static ArrayList<Integer> prevSmaller(ArrayList<Integer> A) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (Integer integer : A) {
            if (!stack.isEmpty() && stack.peek() >= integer) {
                while (!stack.isEmpty() && stack.peek() >= integer) {
                    stack.pop();
                }
            }
            if (stack.isEmpty()) res.add(-1);
            else res.add(stack.peek());
            stack.push(integer);
        }
        return res;
    }
}