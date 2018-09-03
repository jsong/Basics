package com.leetcode.stackqueue;

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

	// 32. Longest Valid Parentheses
	// Company:
	// Description:
	// Solution:
}
