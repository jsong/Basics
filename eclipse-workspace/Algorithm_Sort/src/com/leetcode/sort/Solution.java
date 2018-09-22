package com.leetcode.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

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
		ListNode head = new ListNode(3);
		head.next = new ListNode(1);
		head.next.next = new ListNode(5);

		Solution sl = new Solution();
		ListNode sorted = sl.insertionSortList(head);

		// l1
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(5);

		// l2
		// ListNode l2 = new ListNode(1);
		// l2.next = new ListNode(3);
		// l2.next.next = new ListNode(4);
		//
		// // l3
		// ListNode l3 = new ListNode(2);
		// l3.next = new ListNode(6);

		ListNode[] lists = { l1 };
		ListNode res = sl.mergeKLists(lists);
		System.out.println("Insertion Sort Complexity O(n ^ 2)");
	}

	// ## Insertion Sort
	// 147. Insertion Sort List
	// Company: Apple # Adobe Alibaba Google Airbnb
	// Description: Sort a linked list using insertion sort.
	// Solution: Start a new dummy linked list, start from beginning, scan current
	// linked list, check whether cur.val
	// is bigger than dummy linked list, if not return the previous node in the
	// dummy linked list, and append the current
	// behind.
	public ListNode insertionSortList(ListNode head) {
		ListNode dummy = new ListNode(Integer.MIN_VALUE);

		for (ListNode cur = head; cur != null;) {
			ListNode pos = findInsertPos(dummy, cur.val);
			ListNode tmp = cur.next;
			cur.next = pos.next;
			pos.next = cur;
			cur = tmp;
		}

		return dummy.next;
	}

	private ListNode findInsertPos(ListNode node, int val) {
		ListNode pre = null;

		for (ListNode cur = node; cur != null && cur.val <= val; pre = cur, cur = cur.next) {

		}

		return pre;
	}

	// ## Merge Sort
	// 88. Merge Sorted Array
	// Company: Facebook Microsoft Amazon Indeed Baidu # Adobe Bloomberg Yelp Google
	// Expedia Intel Quip
	// Description: Given two sorted integer arrays nums1 and nums2, merge nums2
	// into nums1 as one sorted array.
	// Solution: From back to front while both the arrays indicator still > 0,
	// choose bigger from two arrays.
	// if ib still > 0, then we need to put it back.
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int ia = m - 1;
		int ib = n - 1;
		int ic = m + n - 1;

		while (ia >= 0 && ib >= 0) {
			nums1[ic--] = nums1[ia] >= nums2[ib] ? nums1[ia--] : nums2[ib--];
		}

		while (ib >= 0) {
			nums1[ic--] = nums2[ib--];
		}
	}

	// 21. Merge Two Sorted Lists
	// Company: Microsoft Amazon Facebook Adobe Alibaba Tencent Warmart labs Apple
	// Indeed Google # Uber Yahoo Baidu Yelp Bloomberg Arista Networks LinkedIn Hulu
	// Intuit Groupon
	// Description: Merge two sorted linked lists and return it as a new list. The
	// new list should be made by splicing together the nodes of the first two
	// lists.
	// Solution: Iterate both pointers and set cur pointers.
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;

		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				cur.next = l1;
				l1 = l1.next;
			} else {
				cur.next = l2;
				l2 = l2.next;
			}

			cur = cur.next;
		}

		while (l1 != null) {
			cur.next = l1;
			cur = cur.next;
			l1 = l1.next;
		}

		while (l2 != null) {
			cur.next = l2;
			cur = cur.next;
			l2 = l2.next;
		}

		return dummy.next;
	}

	// 23. Merge k Sorted Lists
	// Company: Amazon Facebook Google Microsoft Apple Tencent Bloomberg Uber Alibaba Oracle Dropbox Paypal # 
	// Indeed Capital One Airbnb eBay LinkedIn Baidu Adobe Snapchat VMWare IXL Lyft Coupang Nutanix TinyCo
	// Description: Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
	// Solution: PriorityQueue Time Complexity O(N Log k), k is the number of linkedlists, N is the final element. Space Complexity
	// O(N) vs O(k) which is the PQ space.
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		PriorityQueue<ListNode> queue = new PriorityQueue<>(new ListNodeComparator());
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				queue.offer(lists[i]);
			}
		}

		while (!queue.isEmpty()) {
			ListNode node = queue.poll();
			cur.next = node;
			cur = cur.next;

			if (node.next != null) {
				queue.offer(node.next);
			}
		}

		return dummy.next;
	}

	public class ListNodeComparator implements Comparator<ListNode> {
		@Override
		public int compare(ListNode o1, ListNode o2) {
			return o1.val - o2.val;
		}
	}

	// 148. Sort List
	// Company: Baidu Hulu # Adobe Yahoo Microsoft Alibaba Pony.ai Tencent
	// Description: Sort a linked list in O(n log n) time using constant space complexity.
	// Solution: Merge Sort 
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode middle = findMiddle(head);
		ListNode head2 = middle.next;
		middle.next = null; 	// cut in the middle.
		
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(head2);
		
		return mergeSort(l1, l2);
	}
	
	private ListNode findMiddle(ListNode node) {
		if (node == null) {
			return null;
		}
		
		ListNode slow = node;
		ListNode fast = node.next;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	
	private ListNode mergeSort(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		
		for (ListNode p = dummy; l1 != null || l2 !=null; p = p.next) {
			int val1 = l1 != null ? l1.val : Integer.MAX_VALUE;
			int val2 = l2 != null ? l2.val : Integer.MAX_VALUE;
			
			if (val1 <= val2) {
				p.next = l1;
				l1 = l1.next;
			} else {
				p.next = l2;
				l2 = l2.next;
			}
		}
		
		return dummy.next;
	}

	// 23. Merge k Sorted Lists
	// Company:
	// Description:
	// Solution:

	// 23. Merge k Sorted Lists
	// Company:
	// Description:
	// Solution:

	// 23. Merge k Sorted Lists
	// Company:
	// Description:
	// Solution:
}
