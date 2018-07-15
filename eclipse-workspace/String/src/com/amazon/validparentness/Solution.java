package com.amazon.validparentness;

import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		String validate = "({})";
		Solution sl = new Solution();
		Boolean isVaild = sl.isValid(validate);
		
		System.out.println("::"+isVaild);
	}

//	20. Valid Parentheses

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
    }
}
