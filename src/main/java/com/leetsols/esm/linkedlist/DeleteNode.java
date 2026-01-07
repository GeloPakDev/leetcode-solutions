package com.leetsols.esm.linkedlist;

/*
 * Problem type: Linked List
 * Number: 237. Delete Node in a Linked List
 */
public class DeleteNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        // Make current node point to next of next node.
        node.next = node.next.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
