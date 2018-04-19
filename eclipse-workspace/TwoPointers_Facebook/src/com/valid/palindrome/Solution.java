package com.valid.palindrome;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		String testCase = "a.";
		boolean isP1 = sl.isPalindrome(testCase);
		boolean isP2 = sl.isPalindrome2(testCase);
		System.out.println("Par:p1:" + isP1 + " p2:" + isP2);
	}

//680. Valid Palindrome II Facebook
	//Only contains a-z
	 public boolean validPalindrome(String s) {
		 int length = s.length();
		 int left = 0, right = length - 1;
		 while(left < right) {
			 if (s.charAt(left) != s.charAt(right)) {
				 break;
			 }
			 left++;
			 right--;
		 }
		 
		 return isSimplePalindrome(s, left + 1, right) || isSimplePalindrome(s,left, right-1);
	 }
	 
	 private boolean isSimplePalindrome(String s, int left, int right) {
		 while (left < right) {
			 if (s.charAt(left) != s.charAt(right)) {
				 return false;
			 }
			 left++;
			 right--;
 		 }
		 return true;
	 }

//125. Valid Palindrome
// Leetcode submission
	public boolean isPalindrome2(String s) {
		if (s.isEmpty()) {
			return true;
		}
		int head = 0, tail = s.length() - 1;
		char cHead, cTail;
		while (head <= tail) {
			cHead = s.charAt(head);
			cTail = s.charAt(tail);
			//if it's symbol then pointer++
			if (!Character.isLetterOrDigit(cHead)) {
				head++;
			} else if (!Character.isLetterOrDigit(cTail)) {
				tail--;
			} else {
				if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
					return false;
				}
				head++;
				tail--;
			}
		}

		return true;
	}

	// Lintcode submission
	public boolean isPalindrome(String s) {
		// write your code here
		int length = s.length();
		if (s == null || length == 0 || length == 1)
			return false;
		int i = 0, j = 0;
		if (length % 2 == 1) {
			i = length / 2;
			j = length / 2 + 1;
		} else if (length % 2 == 0) {
			i = length / 2 - 1;
			j = length / 2;
		}

		for (; i >= 0 && j <= length - 1; i--, j++) {
			if (s.charAt(i) != s.charAt(j))
				return false;
		}

		return true;
	}

}
