package com.leetcode.stackqueue;

import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 155. Min Stack
	// Company: Amazon Google Lyft Bloomberg Uber # Microsoft Adobe Apple Yahoo
	// Description: Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	// Solution:
	
	class MinStack {
		Stack<Integer> stack;
		int min_value;
	    /** initialize your data structure here. */
	    public MinStack() {
	        stack = new Stack<>();
	        min_value = Integer.MAX_VALUE;
	    }
	    
	    public void push(int x) {
	        if (x < min_value) {
	        	min_value = x;
	        }
	        
	    	stack.push(x);
	    }
	    
	    public void pop() {
	        int top = stack.peek();
	    	stack.pop();	        
	        if (top == min_value) { // need to find new min
	        	findMin();
	        }
	    }
	    
	    public int top() {
	        return stack.peek();
	    }
	    
	    public int getMin() {
	        return min_value;
	    }
	    
	    private void findMin() {
            min_value = Integer.MAX_VALUE; // reset
	    	for (int i: stack) {
	    		if (i < min_value) {
	    			min_value = i;
	    		}
	    	}
	    }
	}
}
