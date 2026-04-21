package com.leetsols.esm.linkedlist;

public class RotateList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*
     * Naive approach:
     * - Get the prev and tail of the list, to reassign the pointer back to the head
     *  - tail.next = head -> assign the reference to the head
     *  - prev.next = null -> unlink the reference to the tail
     * - Repeat it k times
     *
     * Swapping:
     * - Find the current [tail] and [prev] nodes
     * - Point [next] of the [tail] to the [head]
     * - Update the [head] to the [tail]
     * - Break chain by setting [prev.next] = null
     * - Update anchor: dummy.next = head;
     */
    public ListNode rotateRight(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        int i = 0;
        while (i <= k) {
            ListNode prev = dummy;
            ListNode tail = dummy.next;

            // Step 1: Find the actual tail and the node before it
            while (tail.next != null) {
                prev = tail;
                tail = tail.next;
            }

            tail.next = dummy.next; // Tail points to old head
            dummy.next = tail;      // Dummy points to new head (the old tail)
            prev.next = null;       //
            i++;
        }
        return dummy;
    }

    /*
     * - Count the length of the list
     * -
     */
    public ListNode rotateRightTwo(ListNode head, int k) {
        ListNode oldTail = head;
        int n = 0;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            n++;
        }
        oldTail.next = head;

        ListNode newTail = head;
        for (int i = 0; i < n - (k % n) - 1; i++)
            newTail = newTail.next;

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}
