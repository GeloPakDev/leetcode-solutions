package com.leetsols.esm.linkedlist;

public class MergeTwoSortedLists {
    /*
     * Algorithm:
     * - Compare the values of heads
     * - if (list.val < list1.val)
     *  - Move to the next node of the list1, to identify the node
     *    from which the pointer will be changed to the head of
     *    another list
     * - if (list1.val < list.val)
     *  - Do the opposite
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
