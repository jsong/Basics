package com.leetcode.dfs;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 200. Number of Islands
	// Description:
	// Company: Amazon Lyft Facebook Google LinkedIn Uber Bloomberg Apple Microsoft Qualtrics Yahoo Twitter Alibaba Yelp Oracle Adobe
	// eBay AppDynamics Goldman Sachs Affirm # DoorDash Pinterest Zillow Palantir Technologies Expedia.
	// Solution: Iterate each node, if node is 1, then DFS it's four directions, make them as 0. Count how many 1s left.
	public int numIslands(char[][] grid) {
		int m = grid.length;
		if (m == 0) return 0;
		int n = grid[0].length;
		if (n == 0) return 0;

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
		if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == '0') return;
		grid[i][j] = '0';
		dfs(grid, i - 1, j);
		dfs(grid, i + 1, j);
		dfs(grid, i, j - 1);
		dfs(grid, i, j + 1);
	}

	//
	//
	//
	//
}
