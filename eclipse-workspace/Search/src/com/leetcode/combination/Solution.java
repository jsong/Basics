package com.leetcode.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 78. Subsets
	// Description: Given a set of distinct integers, nums, return all possible subsets (the power set).
	// Company: Facebook Adobe Amazon Google # Microsoft Alibaba Lyft Uber
	// Solution: Time Complexity O(2^n). Space O(n);
	// 1. Use for loop. 2. Use choose or not. 
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		Arrays.sort(nums);
		List<Integer> path = new ArrayList<>();
		dfsSubset(0, nums, path, res);
		// dfsSubset2(0, nums, path, res);
		return res;
	}
	
	private void dfsSubset(int start, int[] nums, List<Integer> path, List<List<Integer>> res) {
		// JAVA all REFERENCE
		res.add(new ArrayList<Integer>(path));
		
		for (int i = start; i < nums.length; i++) {
			path.add(nums[i]);
			dfsSubset(i + 1, nums, path, res);
			path.remove(path.size() - 1);
		}
	}
	
	private void dfsSubset2(int index, int nums[], List<Integer> path, List<List<Integer>> res) {
		if (index == nums.length) {
			res.add(new ArrayList<Integer>(path));
			return;
		}
		
		dfsSubset2(index + 1, nums, path, res);
		path.add(nums[index]);
		dfsSubset2(index + 1, nums, path, res);
		path.remove(path.size() - 1);
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
