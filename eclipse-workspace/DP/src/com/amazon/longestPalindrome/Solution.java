package com.amazon.longestPalindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String palin = "abccccdd";
		Solution sl = new Solution();
		int maxSubSeqLength = sl.longestPalindromeSubseq(palin);
		System.out.println("length" + maxSubSeqLength);
		int longestPalindromeLength = sl.longestPalindrome(palin);
		System.out.println("long:" + longestPalindromeLength);
	}

	// 647. Palindromic Substrings DP,
	public int countSubstrings(String s) {
		int n = s.length();
		int count = 0;
		char[] sc = s.toCharArray();
		boolean[][] dp = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
			count++;
			for (int j = 0; j < i; j++) {
				if (sc[i] == sc[j] && (j + 1 >= i - 1 || dp[i - 1][j + 1])) {
					dp[i][j] = true;
					count++;
				}
			}
		}
		return count;
	}

	// Palindrome sub sequence.
	public int longestPalindromeSubseq(String s) {
		int n = s.length();
		char[] sc = s.toCharArray();
		int[][] dp = new int[n][n]; // dp[i][j] means in string s, from index i to j, the longest palindrome.

		for (int j = 0; j < n; j++) {
			dp[j][j] = 1;
			for (int i = j - 1; i >= 0; i--) {
				if (sc[i] == sc[j])
					dp[i][j] = dp[i + 1][j - 1] + 2; // if chars at two end is same, lenght + 2.
				else
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
			}
		}

		return dp[0][n - 1];
	}

	// 5. Longest Palindromic Substring DP
	// dp[i][j] means the longest pal from distance j to i
	public String longestPalindromeSubString(String s) {
		if (s == null || s.length() == 0)
			return "";
		int n = s.length();
		boolean dp[][] = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
		}
		int start = 0;
		int longest = 1;
		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				dp[i][i + 1] = true;
				start = i;
				longest = 2;
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 2; j < n; j++) {
				dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);

				if (dp[i][j] && j - i + 1 > longest) {
					start = i;
					longest = j - i + 1;
				}

			}
		}

		return s.substring(start, start + longest);
	}
	
	// LintCode 627. Longest Palindrome same as below Leetcode 409
	public int longestPalindrome2(String s) {
		Set<Character> pal = new HashSet<Character>();
		char[] allCharacters = s.toCharArray();
		int count = 0;
		for (char a: allCharacters) {
			if (pal.contains(a)) {
				pal.remove(a);
				count ++;
			} else {
				pal.add(a);
			}
		}
		if (pal.size() == 0) {
			count = count * 2;
		} else {
			count = count * 2 + 1;
		}
		return count;
	}
	
	// Leetcode 409. Longest Palindrome string that could built up palindrome.
	public int longestPalindrome(String s) {
		Hashtable table = new Hashtable(); // T type hashtable key, value pair <char,int>
		for (int i = 0; i < s.length(); i++) {
			if (!table.containsKey(s.charAt(i))) {
				table.put(s.charAt(i), 1);
			} else {
				table.put(s.charAt(i), (Integer) table.get(s.charAt(i)) + 1);
			}
		}

		Set keys = table.keySet();
		Iterator itr = keys.iterator();
		int result = 0;
		boolean addon = false;
		while (itr.hasNext()) {
			char key = (char) itr.next();
			if ((Integer) table.get(key) % 2 == 0) {
				result += (Integer) table.get(key);
			} else {
				result += (Integer) table.get(key) - 1;
				addon = true;
			}
		}

		if (addon) {
			result += 1;
		}

		return result;
	}

}
