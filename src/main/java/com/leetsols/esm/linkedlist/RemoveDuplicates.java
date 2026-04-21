package com.leetsols.esm.linkedlist;

public class RemoveDuplicates {
    /*
     * If the duplicate appears
     * assign the next of the first appeared element to the
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = head;
        while (dummy != null && dummy.next != null) {
            if (dummy.val == dummy.next.val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        return head;
    }

    /*
     * Approach: Sentinel + Predecessor
     * Delete Internal Nodes:
     * - The node is a duplicate by comparing its value to the node after it in the list
     * - The first node in the duplicates sublist should be removed as well. That means
     *   that we have to track the predecessor of the duplicates sublist, i.e., the last
     *   node before the sublist of duplicates.
     * - pred.next = head.next
     */
    public ListNode deleteDuplicatesTwo(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pred = dummy;

        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                pred.next = head.next;
            } else {
                pred = pred.next;
            }
            head = head.next;
        }
        return dummy.next;
    }

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
}
