package com.leetsols.esm;

public class LinkedList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    void add(ListNode node, ListNode nodeToAdd) {
        // Save reference of prev node
        ListNode prevNode = node.prev;
        // Add reference to the existing node (Head)
        nodeToAdd.next = node;
        // Add reference to the previous node (i - 1)
        nodeToAdd.prev = prevNode;
        // Add [next] reference to the NEW node
        prevNode.next = nodeToAdd;
        // Re-reference the previous node to the NEW node
        node.prev = nodeToAdd;
    }

    void delete(ListNode node) {
        // Maintain the link to the previous node (i - 1)
        ListNode prevNode = node.prev;
        // Maintain the link to the next node (i + 1)
        ListNode nextNode = node.next;
        // Create a reference to the next node -> nextNode -> node.next
        prevNode.next = nextNode;
        // Create a reference to the prev node -> prevNode -> node.prev
        nextNode.prev = prevNode;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

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

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public ListNode swapPairs(ListNode head) {
        // Linked List has 0 or 1 node
        if (head == null || head.next == null) {
            return head;
        }
        /*
         * Reserve secondNode in dummy to return it in the end
         */
        ListNode dummy = head.next;
        ListNode prev = null;
        while (head != null && head.next != null) {
            if (prev != null) {
                prev.next = head.next;
            }
            prev = head;
            /*
             * - Save access to the rest of the Linked List
             * - Change reference of the next node to the Head
             */
            ListNode nextNode = head.next.next;
            head.next.next = head;
            /*
             * If the number of elements in the list is ODD
             */
            head.next = nextNode;
            head = nextNode;
        }
        return dummy;
    }

    public int pairSum(ListNode head) {
        /*
         * Get middle of the list
         */
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        /*
         * Reverse a Second Half of the list
         */
        ListNode nextNode;
        ListNode prev = null;
        while (slow != null) {
            nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }

        /*
         * - HEAD to iterate over the original list -> first half is not touched
         * - PREV to iterate over the reversed list
         */
        ListNode start = head;
        int res = 0;
        while (prev != null) {
            res = Math.max(res, start.val + prev.val);
            prev = prev.next;
            start = start.next;
        }
        return res;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        /*
         * Iterate over the LinkedList before the first Node of the sublist
         */
        ListNode dummy = new ListNode(0, head);

        ListNode before = dummy;

        for (int i = 1; i < left; i++) {
            before = before.next;
        }

        /*
         * - Create Node to the current Node
         * - Create Node to the Node before it
         */
        ListNode prev = before;
        ListNode curr = before.next;
        for (int i = left; i <= right; i++) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        /*
         * - before.next.next - end of our sublist
         * - before.next      - start of our sublist
         */
        before.next.next = curr;
        before.next = prev;

        return dummy.next;
    }

    public static ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;

        for (int i = 0; i < n; i++) {
            current = current.next;
        }

        if (current == null) {
            return head.next;
        }

        ListNode nodeBeforeRemoved = head;
        while (current.next != null) {
            current = current.next;
            nodeBeforeRemoved = nodeBeforeRemoved.next;
        }

        nodeBeforeRemoved.next = nodeBeforeRemoved.next.next;
        return head;
    }

    public static ListNode deleteDuplicatesTwo(ListNode head) {
        ListNode sentinel = new ListNode(0, head);
        /*
         * Here we are using predecessor as the last node before sublist to remove
         */
        ListNode predecessor = sentinel;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                /*
                 * Iterate over the sublist of duplicated until we find an end
                 */
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                /*
                 * As soon as we found out the end of the duplicate sublist -> delete it
                 */
                predecessor.next = head.next;
            } else {
                predecessor = predecessor.next;
            }
            head = head.next;
        }
        return sentinel.next;
    }

    /*
     * Util methods
     */
    public static ListNode createSingleLinkedList(int[] values) {
        if (values == null || values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}