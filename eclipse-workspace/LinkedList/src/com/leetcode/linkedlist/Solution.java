package com.leetcode.linkedlist;

class ListNode {
	int val;
	public ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//// 1-> 2 -> 3 -> 4 -> 5;
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		Solution sl = new Solution();
		// sl.oddEvenList(head);
		///
		/// 243, 564
		sl.reverseBetween(head, 2, 4);
	}

	// 206. Reverse Linked List
	// Company: Microsoft Facebook Amazon Apple Baidu Intuit Two Sigma Google Adobe
	// Tencent Expedia Bloomberg
	// Uber Twitter Snapchat Zenefits Yelp Yahoo
	// Description: Reverse a singly linked list.
	// eg. Input: 1->2->3->4->5->NULL
	// Output: 5->4->3->2->1->NULL
	// Solution: Use Two pointers iteratively until one of them reaches the end.
	public ListNode reverseList(ListNode head) {
		ListNode curNode = head;
		ListNode preNode = null;

		while (curNode != null) {
			ListNode nextNode = curNode.next;
			curNode.next = preNode;
			preNode = curNode;
			curNode = nextNode;
		}

		return preNode;
	}

	// 328. Odd Even Linked List
	// Company: Microsoft Bloomberg Amazon eBay Adode Expedia Yahoo
	// Description: Given a singly linked list, group all odd nodes together
	// followed by the even nodes.
	// NOTE: first node is considered odd, the second is even. we are talking about
	// node numbers not value inside.
	// eg. Input: 1->2->3->4->5->NULL
	// Input: 1->2->3->4->5->NULL
	// Output: 1->3->5->2->4->NULL
	// Solution:
	public ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode evenHeadNode = null;
		ListNode oddHeadNode = null;
		ListNode curNode = head;
		ListNode newEvenHeadNode = null;
		ListNode newOddHeadNode = null;
		int counter = 0;

		while (curNode != null) {
			++counter;
			if (counter % 2 == 1) {
				if (oddHeadNode == null) {
					oddHeadNode = curNode;
					newOddHeadNode = oddHeadNode;
				} else {
					oddHeadNode.next = curNode;
					oddHeadNode = oddHeadNode.next;
				}
			} else {
				if (evenHeadNode == null) {
					evenHeadNode = curNode;
					// newEvenHeadNode = curNode; // what if newEvenHeadNode point to evenHeadNode,
					// does it change accrodingly?
					newEvenHeadNode = evenHeadNode;
				} else {
					evenHeadNode.next = curNode;
					evenHeadNode = evenHeadNode.next;
				}
			}
			curNode = curNode.next;
		}

		oddHeadNode.next = newEvenHeadNode;
		evenHeadNode.next = null;

		return newOddHeadNode;
	}

	// 2. Add Two Numbers
	// Company: Adobe Microsoft Google Baidu Amazon Alibaba Apple Airbnb Gilt Groupe
	// Tencent Samsung GoDaddy Bloomberg
	// Description: You are given two non-empty linked lists representing two
	// non-negative integers. The digits are stored in reverse order and each of
	// their nodes contain a single digit. Add the two numbers and return it as a
	// linked list. eg. (2 -> 4 -> 3) + (5 -> 6 -> 4) => 7 -> 0 -> 8, explaination
	// 342 + 464 = 807
	// Solution: use two iterator, and one dummy linkedlist to iterate the l1 and
	// l2.

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode cur = head;
		ListNode p = l1;
		ListNode q = l2;

		int c = 0;

		while (p != null || q != null) {
			int p1 = p == null ? 0 : p.val;
			int p2 = q == null ? 0 : q.val;

			int sum = p1 + p2 + c;
			c = sum / 10;
			cur.next = new ListNode(sum % 10);
			cur = cur.next;

			if (p != null) {
				p = p.next;
			}
			if (q != null) {
				q = q.next;
			}
		}

		if (c > 0) {
			cur.next = new ListNode(c);
		}

		return head.next;
	}

	// 92. Reverse Linked List II
	// Company: Amazon Microsoft Facebook Two Sigma Apple Tencent
	// Description: Reverse a linked list from position m to n. Do it in one-pass.
	// Solution: Find the preNode, and iterate through the remaining.
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy; // after which is the last node
		
		for (int i = 0; i < m - 1; i++) {
			pre = pre.next;
		}
		
		ListNode head2 = pre; 
		pre = pre.next;
		ListNode cur = pre.next;
		
		for (int i = m; i < n; i++) {
			pre.next = cur.next;
			cur.next = head2.next;
			head2.next = cur; //1 -> 3, 1 -> 4...
			cur = pre.next;
		}
		
		return dummy.next;
	}

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:
}
