package com.linklist.removeNode;

//19. Remove Nth Node From End of List
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		Solution sl = new Solution();
		ListNode result = sl.removeNthFromEnd(head, 2);
		
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode newHead = new ListNode(0);
		newHead.next = head;
		ListNode p = newHead;
		ListNode runner = newHead;
		while (n > 0) {
			runner = runner.next;
			n--;
		}
		while (runner.next != null) {
			runner = runner.next;
			p = p.next;
		}
		p.next = p.next.next;
		return newHead.next;
	}
}
