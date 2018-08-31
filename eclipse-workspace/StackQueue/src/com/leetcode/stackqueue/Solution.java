package com.leetcode.stackqueue;

import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 155. Min Stack
	// Company: Amazon Google Lyft Bloomberg Uber # Microsoft Adobe Apple Yahoo
	// Description: Design a stack that supports push, pop, top, and retrieving the
	// minimum element in constant time. (O(1) requirement)
	// Solution: 1. Use one stack. However each time pop will need to find the
	// min_value again, if top equals to min_value this will be O(n);
	// 2. Use two stack, one normal stack the other min stack, the min_stack will
	// always make sure the top is current min_value.
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
			for (int i : stack) {
				if (i < min_value) {
					min_value = i;
				}
			}
		}
	}

	// 20. Valid Parentheses
	// Company: Facebook Bloomberg Microsoft Amazon Google LinkedIn Mathworks
	// Alibaba Adobe Samsung Baidu Twitter Riot Games Visa Akuna Capital Apple eBay
	// Indeed Tecent Yandex Uber Lyft # Cloudera Blizzard Twillo Airbnb Expedia
	// Paypal VMWare ServiceNow Yahoo Qualcomm Gilt Groupe Aetion Oracle.
	// Description: Given a string containing just the characters '(', ')', '{',
	// '}', '[' and ']', determine if the input string is valid.
	// besides the empty string is also considered valid.
	// Solution: 1. Use stack and pattern match, check left and right parentheses.
	public boolean isValid(String s) {
		if (s.length() == 0) {
			return true;
		}
		
		Stack<Character> stack = new Stack<>();
		
		for (char c: s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else if (c == ')' || c == '}' || c == ']') {
				if (stack.isEmpty()) {
					return false;
				}
				
				char l = stack.pop();
				if (!isMatch(l, c)) {
					return false;
				}
			}
		}
		
		return stack.isEmpty();
	}
	
	private boolean isMatch(char left, char right) {
		if ((left == '(' && right == ')') || (left == '{' && right == '}') || (left == '[' && right == ']')) {
			return true;
		}
		
		return false;
	}
}
