package com.leetcode.linkedlist;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

	}

	// 206. Reverse Linked List
	// Company:
	// Description:
	// Solution:

}
