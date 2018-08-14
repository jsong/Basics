package com.leetcode.string;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		String p = "A man, a plan, a canal: Panama";
		// 012345678901234567890123456789
		boolean pa = sl.isPalindrome(p);
		System.out.println("pa:" + pa);
		String common = "0123";
		System.out.println(common.substring(0, 2));
	}

	// 125. Valid Palindrome
	// Company: Facebook Apple Amazon Google Microsoft Adobe Snapchat
	// Description: Given a string, determine if it is a palindrome, considering
	// only alphanumeric characters and ignoring cases.
	// eg. Input: "A man, a plan, a canal: Panama" Output: true
	// Solution: Use two pointers from start and tail, compare the character, skip
	// the non-alpha.
	public boolean isPalindrome(String s) {
		if (s.isEmpty()) {
			return true;
		}

		String p = s.toLowerCase();
		int head = 0;
		int tail = p.length() - 1;

		while (head < tail) {
			if (!Character.isLetterOrDigit(p.charAt(head))) {
				head++;
			} else if (!Character.isLetterOrDigit(p.charAt(tail))) {
				tail--;
			} else {
				if (p.charAt(head) != p.charAt(tail)) {
					return false;
				}

				head++;
				tail--;
			}
		}

		return true;
	}

	// 14. Longest Common Prefix
	// Company: Adobe Microsoft Airbnb IXL Amazon Aetion Google Facebook Alibaba
	// Baidu Yelp Apple Expedia Samsung
	// Description: Write a function to find the longest common prefix string
	// amongst an array of strings. If there is no common prefix, return an empty string "".
	// Input: ["flower","flow","flight"] Output: "fl"
	// Solution: Compare each character position by position with first string, until reaches other strings length or not equal 
	// to each other.
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}
		
		for (int j = 0; j < strs[0].length(); j++) {
			for (int i = 1; i < strs.length; i++) {
				if (j == strs[i].length() || strs[i].charAt(j) != strs[0].charAt(j)) {
					return strs[0].substring(0, j); // j is not included.
				}
			}
		}
		
		return strs[0];
	}
	
	// 14. Longest Common Prefix
	// Company:
	// Description:
	// Solution:

	// 14. Longest Common Prefix
	// Company:
	// Description:
	// Solution:
}
