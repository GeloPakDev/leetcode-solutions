package com.leetsols.esm.linkedlist;

public class SwapPairs {
    /*
     * Algorithm:
     * - In order to better build an understanding of this problem is to represent it
     *   in the form of the calls on the stack, for example let's take the list
     *   1 -> 2 -> 3 -> 4
     *
     * 2 (3 -> 4) -> (4 -> 3)  -  1 -> 2 -> 4 -> 3
     * 1 (1 -> 2) -> (2 -> 1)  -  2 -> 1 -> 4 -> 3
     * - Every call to the recursive function, will be taken 2 nodes and others passed
     *   down to the next recursive calls
     * - Assume that recursion would return swapped remaining list nodes and
     *   swap current two nodes, then attach remaining list received from recursion
     *   to these 2 swapped pairs.
     * - Important to note that each next recursion call is made with
     *   head of next pair of nodes.
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;
        return secondNode;
    }

    public static class ListNode {
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
}
