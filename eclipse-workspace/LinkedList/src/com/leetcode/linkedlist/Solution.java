package com.leetcode.linkedlist;

import java.util.HashSet;

class ListNode {
	int val;
	public ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

// Definition for singly-linked list with a random pointer.
class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
};

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
		// sl.reverseBetween(head, 2, 4);
		// sl.partition(head, 3);
		// sl.deleteDuplicates(head);
		// ListNode r = sl.deleteDuplicates2(head);
		// ListNode r = sl.rotateRight(head, 2);
		ListNode nth = new ListNode(1);
		nth.next = new ListNode(2);
		sl.removeNthFromEnd(nth, 2);
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
			head2.next = cur; // 1 -> 3, 1 -> 4...
			cur = pre.next;
		}

		return dummy.next;
	}

	// 86. Partition List
	// Company: Microsoft Yahoo Adobe
	// Description: Given a linked list and a value x, partition it such that all
	// nodes less than x come before nodes greater than or equal to x. You should
	// preserve the original relative order of the nodes in each of the two
	// partitions.
	// Solution: Use two linkedlist, left and right, left to record the number
	// smaller, right to record the number larger and combine the linkedlist.
	public ListNode partition(ListNode head, int x) {
		ListNode left_dummy = new ListNode(-1);
		ListNode right_dummy = new ListNode(-1);
		ListNode left_cur = left_dummy;
		ListNode right_cur = right_dummy;

		ListNode cur = head;

		while (cur != null) {
			if (cur.val < x) {
				left_cur.next = cur;
				left_cur = left_cur.next; // iterate.
			} else {
				right_cur.next = cur;
				right_cur = cur;
			}
			cur = cur.next;
		}

		left_cur.next = right_dummy.next;
		right_cur.next = null;

		return left_dummy.next;
	}

	// 83. Remove Duplicates from Sorted List
	// Company: Amazon Alibaba
	// Description: Given a sorted linked list, delete all duplicates such that each
	// element appear only once.
	// eg. 1 -> 1 -> 1 -> 2 ==> 1 -> 2
	// Solution: iterate the linked list, while cur.next != null && cur.val ==
	// cur.next.val, iterate to next.
	public ListNode deleteDuplicates(ListNode head) {
		ListNode cur = head;

		while (cur != null) {
			while (cur.next != null && cur.val == cur.next.val) {
				cur.next = cur.next.next;
			}

			cur = cur.next;
		}

		return head;
	}

	// 82. Remove Duplicates from Sorted List II
	// Company: Microsoft Amazon Baidu Bloomberg
	// Description: Given a sorted linked list, delete all nodes that have duplicate
	// numbers, leaving only distinct numbers from the original list.
	// eg. 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 ==> 1 -> 2 -> 5;
	// Solution: Two pointers, pre and cur.
	public ListNode deleteDuplicates2(ListNode head) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = dummy.next;

		while (cur != null) {
			boolean duplicate = false;
			while (cur.next != null && cur.val == cur.next.val) {
				cur = cur.next;
				duplicate = true;
			}

			if (duplicate) {
				cur = cur.next;
				continue;
			}

			pre.next = cur;
			pre = pre.next;
			cur = cur.next;
		}

		pre.next = null;
		return dummy.next;
	}

	// 61. Rotate List
	// Company: Microsoft Amazon Adobe Bloomberg Apple
	// Description: Given a linked list, rotate the list to the right by k places,
	// where k is non-negative.
	// eg. Input: 1->2->3->4->5->NULL, k = 2
	// Solution: 1. Find the element, then add a dummy head for that specific
	// element, connect head to that dummy.
	// 2. Create a circle of list, then disconnect
	public ListNode rotateRight2(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}

		int length = 1;
		ListNode cur = head;
		while (cur.next != null) {
			length++;
			cur = cur.next;
		}

		cur.next = head; // create loop
		k = length - k % length;

		for (int i = 0; i < k; i++) {
			cur = cur.next;
		}

		head = cur.next;
		cur.next = null;

		return head;
	}

	public ListNode rotateRight(ListNode head, int k) {
		int size = 0;
		if (head == null || head.next == null) {
			return head;
		}

		ListNode cur = head;

		while (cur != null) {
			cur = cur.next;
			size++;
		}

		k = k % size;
		if (k == 0) {
			return head;
		}

		ListNode nHead = new ListNode(-1); // new head.
		ListNode dummy = new ListNode(-1); // head for shift part.
		ListNode shiftCur = dummy;
		cur = head; // reset iterator.
		int counter = 1;
		while (cur != null) {
			if (counter == size - k) {
				shiftCur.next = cur.next;
				shiftCur = shiftCur.next;
				cur.next = null;
				while (shiftCur != null) {
					if (shiftCur.next == null) {
						shiftCur.next = head;
						break;
					} else {
						shiftCur = shiftCur.next;
					}
				}

				break;
			}
			counter++;
			cur = cur.next;
		}

		nHead.next = dummy.next;
		return nHead.next;
	}

	// 19. Remove Nth Node From End of List
	// Company: Microsoft Amazon Facebook Google Zenefits Aetion Adobe ebay.
	// Description: Given a linked list, remove the n-th node from the end of list
	// and return its head.
	// eg. 1 -> 2 -> 3 -> 4 -> 5, n = 2; => 1 -> 2 -> 3 -> 5;
	// Solution: 1. find the element to be removed, if it's index 0, then iterate
	// the head to its next
	// else just delete it in the middle.
	// 2. Use Two pointers, p, q, let p go n steps first, then q will go (length -
	// n) steps, just use q to delete.
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy;
		ListNode q = dummy;

		for (int i = 0; i < n; i++) {
			p = p.next;
		}

		while (p.next != null) { // [1] 1 case
			p = p.next;
			q = q.next; // q will go length - n steps;
		}

		q.next = q.next.next;

		return dummy.next;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		int length = 1;
		ListNode cur = head; // iterator

		while (cur.next != null) {
			length++;
			cur = cur.next;
		}

		int m = length - n; //

		if (m == 0) { // delete first element.
			head = head.next;
			return head;
		}

		cur = head;

		for (int i = 0; i < m - 1; i++) {
			cur = cur.next;
		}

		if (cur.next != null) {
			cur.next = cur.next.next; // delete the next one.
		}

		return head;
	}

	// 24. Swap Nodes in Pairs
	// Company: Microsoft Amazon Adobe Facebook Bloomberg Google Uber
	// Description:Given a linked list, swap every two adjacent nodes and return its
	// head. eg. Given 1->2->3->4, you should return the list as 2->1->4->3.
	// 1. Constant space. 2. You may not modify the values in the list's nodes, only
	// nodes itself may be changed.
	// Solution: 1. NOT ALLOWED, swap value 1 and 2, then p went to next next.
	// continue;
	// 2. Use pre, cur, next iterate.
	public ListNode swapPairs(ListNode head) {

	}

	// 25. Reverse Nodes in k-Group
	// Company: Microsoft Google Mathworks Bloomberg Aamzon Goo
	// Description: Given a linked list, reverse the nodes of a linked list k at a
	// time and return its modified list.
	// eg. 1->2->3->4->5 k = 2 => 2->1->4->3->5, k = 3 => 3->2->1->4->5.
	// Solution:
	public ListNode reverseKGroup(ListNode head, int k) {

	}

	// 138. Copy List with Random Pointer
	// Company: Amazon Microsoft Bloomberg Facebook Google Alibaba LinkedIn
	// Mathworks GoldmanSachs Adobe
	// Description: A linked list is given such that each node contains an
	// additional random pointer which could point to any node in the list or null.
	// Return a deep copy of the list.
	// Solution:
	public RandomListNode copyRandomList(RandomListNode head) {

	}

	// 141. Linked List Cycle
	// Company: Tencent Apple Baidu Alibaba Microsoft Amazon Facebook
	// Description: Given a linked list, determine if it has a cycle in it. Solve it
	// without using extra space.
	// Solution: 1. Two pointers, slow & fast, if they meet in the mid, then it must
	// have circle. If they run into the end of list,
	// which means it does not have circle.
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slow = head;
		ListNode fast = head.next;

		while (slow != fast) {

			if (fast == null || fast.next == null) {
				return false;
			}

			fast = fast.next.next;
			slow = slow.next;
		}

		return true;
	}

	public boolean hasCycle2(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast)
				return true;
		}
		return false;
	}

	// 142. Linked List Cycle II
	// Company: Bloomberg Microsoft Google Adobe Tencent
	// Description: Given a linked list, return the node where the cycle begins. If
	// there is no cycle, return null.
	// Can you solve it without using extra space.
	// Solution: 1. Use extra space, hashset check whether it's already been added.
	// 2. Not use extra space. When slow and fast meet, start slow2 from head.
	public ListNode detectCycle2(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}

		HashSet<ListNode> set = new HashSet<>();
		ListNode cur = head;

		while (cur != null) {
			if (!set.add(cur)) {
				return cur;
			}
			cur = cur.next;
		}

		return null;
	}

	//
	public ListNode detectCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;

			if (fast == slow) {
				ListNode slow2 = head;

				while (slow2 != slow) {
					slow2 = slow2.next;
					slow = slow.next;
				}

				return slow2;
			}
		}

		return null;
	}

	// 143. Reorder List
	// Company: Facebook Microsoft Amazon Hulu Bloomberg
	// Description: Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
	// L0→Ln→L1→Ln-1→L2→Ln-2→…
	// May Not modify the values in the list's nodes, only nodes itselft may be
	// changed.
	// Solution:
	public void reorderList(ListNode head) {
		// split the lists from the middle into two parts.
		if (head == null || head.next == null) {
			return;
		}

		ListNode slow = head;
		ListNode fast = head;
		ListNode pre = null;

		while (fast != null && fast.next != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next.next; // runner
		}

		pre.next = null; // cut in the middle.
		slow = reverse(slow);

		ListNode cur = head;

		while (cur.next != null) {
			ListNode next = cur.next;
			cur.next = slow;
			slow = slow.next;
			cur.next.next = next;
			cur = next;
		}

		cur.next = slow; // 1 -> 2 && 5 -> 4 -> 3 - > null;
	}

	// reverse
	private ListNode reverse(ListNode head) {
		ListNode pre = null;
		ListNode cur = head;

		while (cur != null) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}

		return pre;
	}

	// 234. Palindrome Linked List
	// Company: Microsoft Bloomberg Twitter IXL Amazon Facebook Alibaba Adobe
	// NetEase
	// Description: Given a singly linked list, determine if it is a palindrome
	// Follow up: Could you do it in O(n) time and O(1) space?
	// Solution: Find the middle element, cut in the middle, and then reverse the second part.
	// compare the first & second part node value.
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		
		ListNode pre = null;
		ListNode slow = head;
		ListNode fast = head;
		
		while (fast != null && fast.next != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		pre.next = null; // 1->2->null 3->2->1(3->4);  
		slow = reverse(slow);
		ListNode cur = head;
		
		while (cur != null) {
			if (cur.val == slow.val) {
				cur = cur.next;
				slow = slow.next;
			} else {
				return false;
			}
		}
		
		return true;
	}

	// 146. LRU Cache
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:
}
