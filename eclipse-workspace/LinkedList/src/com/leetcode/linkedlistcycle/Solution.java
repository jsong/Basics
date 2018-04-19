package com.leetcode.linkedlistcycle;

import com.leetcode.linkedlist.ListNode;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
