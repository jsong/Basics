package com.leetcode.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		String[] s = { "2", "1", "+", "3", "*" };
		String temp = "";
		int result = sl.evalRPN(s);
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

		for (char c : s.toCharArray()) {
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

	// 32. Longest Valid Parentheses
	// Company: Facebook Amazon Adobe Expedia # Google Alibaba Uber Goupang
	// Salesforce
	// Description: Given a string containing just the characters '(' and ')', find
	// the length of the longest valid (well-formed) parentheses substring.
	// NOTE: ()(() should return 2 instead of 4, so it's basically continuous
	// substring length.
	// Solution: Use stack to record position of ( and last to track the last
	// non-pairing ).
	public int longestValidParentheses(String s) {
		// TODO: DP
		return 0;
	}

	public int longestValidParentheses2(String s) {
		Stack<Integer> stack = new Stack<>(); // position of '('

		char[] arr = s.toCharArray();
		int last = -1; // last position of non-matching ')'
		int maxLen = 0;

		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (c == '(') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					//
					last = i;
				} else {
					stack.pop();
					if (stack.isEmpty()) { // we have equal ( and )
						maxLen = Math.max(maxLen, i - last);
					} else { // we have more (
						maxLen = Math.max(maxLen, i - stack.peek());
					}
				}
			}
		}

		return maxLen;
	}

	// 84. Largest Rectangle in Histogram
	// Company: Amazon Google Facebook # Microsoft
	// Description: Given n non-negative integers representing the histogram's bar
	// height where the width of each bar is 1, find the area of largest rectangle
	// in the histogram.
	// Solution: Use a increase stack to record the heights, the maximum area must
	// within the increase stack.
	// if the element in heights starts to decrease, then start to calculate the
	// area.
	public int largestRectangleArea(int[] heights) {
		Stack<Integer> stack = new Stack<>();
		int maxArea = 0;

		for (int i = 0; i <= heights.length;) {
			int value = i < heights.length ? heights[i] : 0;

			if (stack.isEmpty() || value > heights[stack.peek()]) {
				stack.push(i++);
			} else {
				int tmp = stack.pop();
				maxArea = Math.max(maxArea, heights[tmp] * (stack.isEmpty() ? i : i - stack.peek() - 1));
			}
		}

		return maxArea;
	}

	// 150. Evaluate Reverse Polish Notation
	// Company: LinkedIn Google Amazon # Uber Two Sigma Microsoft Adobe
	// Description: Evaluate the value of an arithmetic expression in Reverse Polish
	// Notation. Valid operators are +, -, *, /. Each operand may be an integer or
	// another expression.
	// Solution: Use stack, always push the number, if operator, then pop the previous numbers and do the 
	// calculation. Tree, postorder RPN. child as numbers, root are operator.
	public int evalRPN(String[] tokens) {
		Stack<String> stack = new Stack<>();

		for (String s : tokens) {
			if (!isOperator(s)) {
				stack.push(s);
			} else {
				int x = Integer.parseInt(stack.pop());
				int y = Integer.parseInt(stack.pop());
				switch (s) {
				case "+":
					y += x;
					break;
				case "-":
					y -= x;
					break;
				case "*":
					y *= x;
					break;
				case "/":
					y /= x;
					break;
				default:
					break;
				}
				stack.push(String.valueOf(y));
			}
		}

		return Integer.parseInt(stack.peek());
	}

	private boolean isOperator(String c) {
		// if (c == "+" || c == "-" || c == "*" || c == "/") {
		// return true;
		// }
		String oper = "+-*/";
		if (oper.indexOf(c) != -1) {
			return true;
		}

		return false;
	}

	// 225. Implement Stack using Queues
	// Company: Apple Microsoft # Google LinkedIn Amazon Bloomberg
	// Description: Implement the following operations of a stack using queues. 
	// Only use standard operations of queue, push to back, peek/pop from front, size, and is empty operations are valid.
	// Solution: Use two queues. 
	
	class MyStack {
		Queue<Integer> queue;
		Queue<Integer> temp;
	    /** Initialize your data structure here. */
	    public MyStack() {
	        queue = new LinkedList<>();
	        temp = new LinkedList<>();
	    }
	    
	    /** Push element x onto stack. */
	    public void push(int x) {
	    	temp.add(x);
	    	while (!queue.isEmpty()) {
	    		temp.add(queue.poll());
	    	}
	    	
	    	Queue<Integer> tmp = temp;
	    	temp = queue;
	    	queue = tmp;
	    }
	    
	    /** Removes the element on top of the stack and returns that element. */
	    public int pop() {
	    	return queue.poll();
	    }
	    
	    /** Get the top element. */
	    public int top() {
	    	return queue.peek();
	    }
	    
	    /** Returns whether the stack is empty. */
	    public boolean empty() {
	        return queue.isEmpty();
	    }
	}
	
	// 232. Implement Queue using Stacks
	// Company: eBay Amazon # Yandex Salesforce Mathworks Apple
	// Description: Implement the following operations of a queue using stacks.
	// Solution: Use mid stack to reverse the stack order, so that the temp will have 
	// the correct order.
	class MyQueue {
		Stack<Integer> stack;
		Stack<Integer> temp;
	    /** Initialize your data structure here. */
	    public MyQueue() {
	        stack = new Stack<>();
	        temp = new Stack<>();
	    }
	    
	    /** Push element x to the back of queue. */
	    public void push(int x) {
	    	while (!stack.isEmpty()) {
	    		temp.push(stack.pop());
	    	}
	    	
	    	temp.push(x);
	    	while (!temp.isEmpty()) {
	    		stack.push(temp.pop());
	    	}
//	        temp.push(x);
//	        Stack<Integer> mid = new Stack<>();
//	        while (!stack.isEmpty()) {
//	        	mid.push(stack.pop());
//	        }
//	        
//	        while (!mid.isEmpty()) {
//	        	temp.push(mid.pop());
//	        }
//	        
//	        Stack<Integer> tmp = temp;
//	        temp = stack;
//	        stack = tmp;
	    }
	    
	    /** Removes the element from in front of queue and returns that element. */
	    public int pop() {
	        return stack.pop();
	    }
	    
	    /** Get the front element. */
	    public int peek() {
	        return stack.peek();
	    }
	    
	    /** Returns whether the queue is empty. */
	    public boolean empty() {
	        return stack.isEmpty();
	    }
	}
}
