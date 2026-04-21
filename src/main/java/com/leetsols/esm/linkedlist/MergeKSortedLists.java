package com.leetsols.esm.linkedlist;

import java.util.ArrayDeque;

public class MergeKSortedLists {
    /*
     * Approach one:
     * - Array of [k] linked lists, merge all linked-lists into one single linked-list.
     * - [[1,1],[2,2],[3,3],[4,4],[5,5],[6,6]].
     * - Take 2 lists from the array, merge them and put them back to the end of the queue.
     * - Merging the similar sizes, gives us faster time complexity, as it formes for us the
     *   binary tree, where every node is touched or processed, once per level of the tree.
     *
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        var heap = new ArrayDeque<ListNode>();
        for (ListNode list : lists) {
            if (list != null) heap.offer(list);
        }

        ListNode res = null;
        while (!heap.isEmpty()) {
            var one = heap.pop();
            if (!heap.isEmpty()) {
                var two = heap.pop();
                res = mergeLists(one, two);
                heap.addLast(res);
            } else {
                return one;
            }
        }
        return res;
    }

    public ListNode mergeLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) current.next = list1;
        else current.next = list2;

        return dummy.next;
    }

    public class ListNode {
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
