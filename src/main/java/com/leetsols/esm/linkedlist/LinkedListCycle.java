package com.leetsols.esm.linkedlist;

import java.util.HashMap;

public class LinkedListCycle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
     * Given the head of the LinkedList, return the node where the cycle begins
     * If there is no cycle, return null.
     *
     * Iterate over the list and check if the next pointer contains in the hashmap
     */
    public ListNode detectCycle(ListNode head) {
        var hashmap = new HashMap<ListNode, ListNode>();
        ListNode dummy = head;
        while (dummy != null) {
            if (hashmap.containsKey(dummy)) {
                return dummy;
            } else {
                hashmap.put(dummy, dummy.next);
            }
            dummy = dummy.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode node) {
        if (node == null || node.next == null) return null;

        ListNode slow = node;
        ListNode fast = node;

        // Find the collision point
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        // If no cycle return null
        if (!hasCycle) return null;

        // Finding the entry node
        ListNode entryFinder = node;
        while (entryFinder != slow) {
            entryFinder = entryFinder.next;
            slow = slow.next;
        }
        return entryFinder;
    }
}
