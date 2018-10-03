package com.leetcode.combination;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 78. Subsets
	// Description:
	// Company:
	// Solution:
	public List<List<Integer>> subsets(int[] nums) {
		
	}

	// 77. Combinations
	// Description: Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	// Company: Facebook # Google Amazon.
	// Solution: Use DFS for loop to next level.
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		dfs(1, n, k, path, res);
		
		return res;
	}
	
	private void dfs(int start, int n, int k, List<Integer> path, List<List<Integer>> res) {
		if (path.size() == k) {
			res.add(new ArrayList<>(path));
			return;
		}
		
		for (int i = start; i <= n; i++) {
			path.add(i);
			dfs(i + 1, n, k, path, res);
			path.remove(path.size() - 1);
		}
	}
}
