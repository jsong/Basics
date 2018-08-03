package com.leetcode.linkedlist;

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
		sl.oddEvenList(head);
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
//					newEvenHeadNode = curNode; // what if newEvenHeadNode point to evenHeadNode, does it change accrodingly? 
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

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:
}
