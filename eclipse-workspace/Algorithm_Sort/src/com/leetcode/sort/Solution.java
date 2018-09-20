package com.leetcode.sort;

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
		System.out.println("Insertion Sort Complexity O(n ^ 2)");
	}
	
	// ## Insertion Sort
	// 147. Insertion Sort List
	// Company: Apple # Adobe Alibaba Google Airbnb
	// Description: Sort a linked list using insertion sort.
	// Solution: Start a new dummy linked list, start from beginning, scan current linked list, check whehter cur.val 
	// is bigger than dummy linked list, if not return the previous node in the dummy linked list, and append the current 
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
		
		for (ListNode cur = node; cur != null && cur.val < val; pre = cur, cur = cur.next) {
			
		}
		
		return pre;
	}

}
