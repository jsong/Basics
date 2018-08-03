package com.leetcode.linkedlistcycle;

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

	}
	
	public boolean hasCycle(ListNode head) {
        if(head == null)	return false;
        ListNode runner = head;
        ListNode walker = head;
        while (runner.next !=null && runner.next.next !=null) {
        		runner = runner.next.next;
        		walker = walker.next;
        		if(runner == walker) break;
        }
        return true;
    }

}
