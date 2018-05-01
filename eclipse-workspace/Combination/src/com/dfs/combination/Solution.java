package com.dfs.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		int[] nums = { 1, 2, 3 };
		sl.subsets(nums);
	}

	// 40. Combination Sum II
	// Company: snapchat
	// Description: find all possible combinations which could sum up to target,
	// value in duplicate array could not be reused.
	// Solution:
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		
		if (candidates == null || candidates.length == 0) {
			return res;
		}
		
		Arrays.sort(candidates);
		dfshelper2(res, candidates, target, new ArrayList<Integer>(), 0);
		
		return res;
	}

	private void dfshelper2(List<List<Integer>> res, int[] candidates, int target, List<Integer> list, int start) {
		if (target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
			if (candidates[i] > target) {
				break;
			}
			
			// skip the second 1
			// [1,1,2,3,4] target 5
			if (i != start && candidates[i] == candidates[i - 1]) {
				continue;
			}
			
			list.add(candidates[i]);
			dfshelper2(res, candidates, target - candidates[i], list, i + 1);
			list.remove(list.size() - 1);
		}
	}
	
	// 39. Combination Sum
	// Company: Uber snapchat
	// Description: find all possible combination which could add up to target.
	// Solution: Like subsets, how the return of recursion is different, and allow
	// duplicates.

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();

		if (candidates == null || candidates.length == 0) {
			return res;
		}

		Arrays.sort(candidates);
		sumhelper(candidates, new ArrayList<>(), res, 0, target);

		return res;
	}

	private void sumhelper(int[] candidates, List<Integer> list, List<List<Integer>> res, int index, int target) {
		if (target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			if (candidates[i] > target) {
				break;
			}
			list.add(candidates[i]);
			// if (i != index && candidates[i] == candidates[i - 1]) {
			// continue;
			// }
			sumhelper(candidates, list, res, i, target - candidates[i]);
			list.remove(list.size() - 1);
		}
	}

	// 78. Subsets
	// Company: Facebook Amazon, Uber
	// Description: Given a int[], find all possible combination
	// Solution: Use DFS.

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		dfs(res, 0, new ArrayList<Integer>(), nums);

		return res;
	}

	//
	private void dfs(List<List<Integer>> res, int start, List<Integer> subset, int[] nums) {
		// res.add(subset); need deep copy, otherwise it will be incorrect.
		res.add(new ArrayList<Integer>(subset));
		for (int i = start; i < nums.length; i++) {
			// start with 1
			subset.add(nums[i]);
			System.out.println(subset);
			// find all, start with 1, like 1,2,...
			dfs(res, i + 1, subset, nums);
			// backtracking,
			subset.remove(subset.size() - 1);
		}
	}
}
