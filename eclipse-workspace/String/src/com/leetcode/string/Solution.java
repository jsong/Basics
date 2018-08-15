package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		String num = "1.0e5";
		boolean isNum = sl.isNumber(num);
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
	// amongst an array of strings. If there is no common prefix, return an empty
	// string "".
	// Input: ["flower","flow","flight"] Output: "fl"
	// Solution: Compare each character position by position with first string,
	// until reaches other strings length or not equal
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

	// 65. Valid Number
	// Company: Facebook Google LinkedIn Adobe
	// Description: Validate if a given string is numeric. eg, Some examples:
	// "0" => true " 0.1 " => true "abc" => false "1 a" => false "2e10" => true
	// Note: It is intended for the problem statement to be ambiguous. You should
	// gather all requirements up front before implementing one.
	// Solution: 1. check number, '.', 'e', '+/-', excludes all the illegal cases.
	// 2. Use infinite automata.
	public boolean isNumber(String s) {
		s = s.trim();
		boolean numberSeen = false;
		boolean dotSeen = false;
		boolean eSeen = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c <= '9' && c >= '0') {
				numberSeen = true;
			} else if (c == '.') {
				if (dotSeen || eSeen) {
					return false;
				}
				dotSeen = true;
			} else if (c == 'e') {
				if (!numberSeen || eSeen) {
					return false;
				}
				numberSeen = false;
				eSeen = true;
			} else if (c == '+' || c == '-') {
				if (i != 0 && s.charAt(i - 1) != 'e') { // 1.0e+5 legal
					return false;
				}
			} else {
				return false;
			}
		}

		return numberSeen;
	}

	// 12. Integer to Roman
	// Company: Amazon LinkedIn Microsoft Adobe Alibaba Facebook Google Bloomberg
	// Intuit Airbnb
	// Description: I:1 V:5 X:10 L:50 C:100 D:500 M:1000, subtraction: I can be
	// placed before V (5) and X (10) to make 4 and 9. X can be placed before L (50)
	// and C (100) to make 40 and 90. C can be placed before D (500) and M (1000) to
	// make 400 and 900.
	// Solution: Use mapping table for every Roman number, / get the count and % get
	// the next number.
	public String intToRoman(int num) {
		int[] radix = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuffer sb = new StringBuffer();
		for (int i = 0; num != 0; i++) {
			int count = num / radix[i];
			num %= radix[i];
			for (; count > 0; count--) {
				sb.append(symbols[i]);
			}
		}

		return sb.toString();
	}

	// 38. Count and Say
	// Company: Google Facebook Intuit Microsoft Amazon Adobe Apple eBay Airbnb
	// Description: The count-and-say sequence is the sequence of integers beginning
	// as follows:
	// 1, 11, 21, 1211, 111221, ...
	// 1 is read off as "one 1" or 11.
	// 11 is read off as "two 1s" or 21.
	// 21 is read off as "one 2", then "one 1" or 1211.
	// Given an integer n, generate the nth sequence.
	// Solution: Use count to count how many times, and char to form the current
	// string.
	public String countAndSay(int n) {
		if (n <= 0) {
			return "";
		}

		String base = "1";

		while (--n > 0) {
			StringBuffer sb = new StringBuffer();
			int count = 1;

			for (int j = 1; j < base.length(); j++) {

				if (base.charAt(j) == base.charAt(j - 1)) {
					count++;
				} else {
					sb.append(count);
					sb.append(base.charAt(j - 1));
					count = 1;
				}
			}

			sb.append(count);
			sb.append(base.charAt(base.length() - 1));
			base = sb.toString();
		}

		return base;
	}

	// 49. Group Anagrams
	// Company: Amazon Microsoft Google Uber Yelp Goldman Sachs Facebook Apple
	// Alibaba
	// Adobe Yahoo Airbnb Twitter Bloomberg Pinterest
	// Description: Given an array of strings, group anagrams together.
	// Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
	// Output:
	// [
	// ["ate","eat","tea"],
	// ["nat","tan"],
	// ["bat"]
	// ]
	// Solution: Sort the input string, use adjacent list to store the other
	// anagrams.
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, ArrayList<String>> map = new HashMap<>();

		for (String str : strs) {
			char[] tmp = str.toCharArray();
			Arrays.sort(tmp);

			String key = new String(tmp);

			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(str);
		}

		List<List<String>> result = new ArrayList<>();

		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
			List<String> value = entry.getValue();
			result.add(value);
		}

		return result;
	}

	// 242. Valid Anagram
	// Company: Bloomberg Google Microsoft Amazon Apple Goldman Sachs Facebook Snapchat
	// Description: Given two strings s and t , write a function to determine if t
	// is an anagram of s.
	// eg, Input: s = "anagram", t = "nagaram" => true, Input: s = "rat", t = "car" => false;
	// Solution: 1. Use O(1) memory 2. Use sort and compare side by side.
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		
		int[] alpha = new int[26];
		
		for (int i = 0; i < s.length(); i++) {
			++alpha[s.charAt(i) - 'a'];
			--alpha[t.charAt(i) - 'a'];
		}
		
		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i] != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isAnagram2(String s, String t) {
		char[] s_char = s.toCharArray();
		char[] t_char = t.toCharArray();
		
		Arrays.sort(s_char);
		Arrays.sort(t_char);
		
		if (s_char.length != t_char.length) {
			return false;
		}
		
		for (int i = 0; i < s_char.length; i++) {
			if (s_char[i] != t_char[i]) {
				return false;
			}
		}
		
		return true;
	}

	// 242. Valid Anagram
	// Company:
	// Description:
	// Solution:

	// 242. Valid Anagram
	// Company:
	// Description:
	// Solution:

	// 242. Valid Anagram
	// Company:
	// Description:
	// Solution:

	// 242. Valid Anagram
	// Company:
	// Description:
	// Solution:

	// 242. Valid Anagram
	// Company:
	// Description:
	// Solution:
}
