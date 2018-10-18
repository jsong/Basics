package com.leetcode.dfs;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num = "123456579";
		Solution sl = new Solution();
		boolean addictive = sl.isAdditiveNumber(num);
		System.out.println("is:" + addictive);
	}

	// 200. Number of Islands
	// Description:
	// Company: Amazon Lyft Facebook Google LinkedIn Uber Bloomberg Apple Microsoft
	// Qualtrics Yahoo Twitter Alibaba Yelp Oracle Adobe
	// eBay AppDynamics Goldman Sachs Affirm # DoorDash Pinterest Zillow Palantir
	// Technologies Expedia.
	// Solution: Iterate each node, if node is 1, then DFS it's four directions,
	// make them as 0. Count how many 1s left.
	private int m;
	private int n;

	public int numIslands(char[][] grid) {
		m = grid.length;
		if (m == 0)
			return 0;
		n = grid[0].length;
		if (n == 0)
			return 0;

		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] - '0' == 1) {
					dfs(grid, i, j);
					res++;
				}
			}
		}

		return res;
	}

	private void dfs(char[][] grid, int i, int j) {
		if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == '0')
			return;
		grid[i][j] = '0';
		dfs(grid, i - 1, j);
		dfs(grid, i + 1, j);
		dfs(grid, i, j - 1);
		dfs(grid, i, j + 1);
	}

	// 306. Additive Number
	// Additive number is a string whose digits can form additive sequence.
	// Company: Epic Systems
	// Solution: Two pointers i, j, partition num into three parts, check whether i, j and j,k could form num.
	public boolean isAdditiveNumber(String num) {
		for (int i = 1; i <= num.length() / 2; ++i) {
			if (num.charAt(0) == '0' && i > 1)
				return false;
			for (int j = i + 1; j < num.length(); ++j) {
				if (num.charAt(i) == '0' && j - i > 1)
					break;
				if (dfs(num, 0, i, j))
					return true;
			}
		}
		return false;
	}

	private boolean dfs(String num, int i, int j, int k) {
		long num1 = Long.parseLong(num.substring(i, j));
		long num2 = Long.parseLong(num.substring(j, k));
		final String addition = String.valueOf(num1 + num2);

		if (!num.substring(k).startsWith(addition)) {
			return false;
		}

		if (k + addition.length() == num.length()) {
			return true;
		}

		return dfs(num, j, k, k + addition.length());
	}

	// 131. Palindrome Partitioning
	// Description: Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 	// Company: Amazon # Uber
	// Solution: DFS, like subset, continous pick the subsring (index, i + 1), leftover starts (i + 1).
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();

		if (s == null || s.length() == 0) {
			return res;
		}

		dfsP(s, 0, res, new ArrayList<String>());
		return res;
  }

	private void dfsP(String s, int index, List<List<String>> res, List<String> path) {
		if (index == s.length()) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = index; i < s.length(); i++) {
			String sub = s.substring(index, i + 1);
			if (!isPalindrome(sub)) continue;
			path.add(sub);
			dfsP(s, i + 1, res, path);
			path.remove(path.size() - 1);
		}
	}

	private boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i++) == s.charAt(j--)) {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}
}
