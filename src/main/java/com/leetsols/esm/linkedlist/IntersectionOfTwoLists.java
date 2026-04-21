package com.leetsols.esm.linkedlist;

import java.util.HashSet;

public class IntersectionOfTwoLists {
    /*
     * Return the node at which the 2 lists intersect.
     * - Intersected node's value is not 1 because the nodes with value 1 in A and B
     *   (2nd node in A and 3rd node in B) are different node references. In other words, they
     *   point to two different locations in memory, while the nodes with value 8 in A and B (3rd
     *   node in A and 4th node in B) point to the same location in memory.
     * - Intersected Node is the one on which nodes from 2 lists are pointing at.
     * - Iterate over the both lists and check does the link to the next node exists or not.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA.next == headB) return headB;
        if (headB.next == headA) return headA;

        var map = new HashSet<ListNode>();
        map.add(headA);
        while (headA != null) {
            map.add(headA.next);
            headA = headA.next;
        }

        if (map.contains(headB)) return headB;
        else map.add(headB);

        while (headB != null) {
            if (map.contains(headB.next)) return headB.next;
            else headB = headB.next;
        }
        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
