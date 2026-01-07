package com.leetsols.esm.linkedlist;


public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int firstValue = countValue(l1, 0);
        int secondValue = countValue(l2, 0);

        int sum = firstValue + secondValue;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (sum > 0) {
            int digit = sum % 10;
            current.next = new ListNode(digit);
            current = current.next;
            sum = sum / 10;
        }
        return dummy.next;

    }

    public int countValue(ListNode node, int length) {
        if (node == null) {
            return 0;
        }
        return (int) (Math.pow(10, length) * node.val + countValue(node.next, length + 1));
    }

    /*
     * sum % 10 -> provides us remainder after diving by 10 -> 15 % 10 = 5
     * Any number divided by 10 leave a reminder between 0 and 9
     *
     * carry -> drops the decimal part and provides you the whole number
     * It identifies how many tens are in your sum, carry is used for the
     * next pair of digits in the next iteration of the loop.
     */
    public ListNode addTwoNumbersFinal(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode();
        ListNode current = temp;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return temp.next;
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
