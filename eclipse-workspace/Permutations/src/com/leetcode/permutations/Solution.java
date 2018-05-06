package com.leetcode.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		int[] nums = {1, 2, 3};
		List<List<Integer>> per = sl.permute(nums);

		System.out.println("Per:" + per);
		
//		sl.swap(nums, 0, 1);
//		sl.reverse(nums, 0, nums.length - 1);
		sl.nextPermutation(nums);
		System.out.println(nums);
	}

	// 31. Next Permutation
	// Company: Google
	// Description: [1,2,3] - > [1,3,2]; [3,2,1] -> [1,2,3]
	// Solution:
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		
		int firstSmall = -1;
		
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				firstSmall = i;
				break;
			}
		}
		// not find under 3,2,1 case
		if (firstSmall == -1) {
			reverse(nums, 0, nums.length - 1);
			return;
		}
		
		int firstLarge = -1;
		for (int i = nums.length - 1; i >firstSmall; i--) {
			if (nums[i] > nums[firstSmall]) {
				firstLarge = i;
				break;
			}
		}
		
		swap(nums, firstSmall, firstLarge);
		reverse(nums, firstSmall + 1, nums.length - 1);
		return;
	}
	
	private void reverse(int[] nums, int i, int j) {
		while (i < j) {
			swap(nums, i++, j--);
		}
	}
	
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	

	// Lint Code. String permutation II
	// http://www.lintcode.com/en/problem/string-permutation-ii/
	// Given a string, find all permutations of it without duplicates.
	// Example
	// Given “abb”, return [“abb”, “bab”, “bba”].
	// Given “aabb”, return [“aabb”, “abab”, “baba”, “bbaa”, “abba”, “baab”].
	// Same as Permutation II.

	// 47. Permutations II
	// Company: Microsoft, LinkedIn
	// Description: Same as 46, except don't allow duplicates.
	// Solution: Skip the duplicates previous value.
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		Arrays.sort(nums);
		dfs2(nums, res, new boolean[nums.length], new ArrayList<Integer>());
		return res;
	}

	private void dfs2(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> permutation) {
		if (permutation.size() == nums.length) {
			res.add(new ArrayList<Integer>(permutation));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}
			// skip the case [1, 1, 2] current index = 1.
			// since later 1 is equal to first 1, and we should skip the first.
			// it's based on the array has been sorted, all the same value is adjacent.
			if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
				continue;
			}

			permutation.add(nums[i]);
			visited[i] = true;
			dfs2(nums, res, visited, permutation);
			visited[i] = false;
			permutation.remove(permutation.size() - 1);
		}
	}

	// Shift + Ctrl + O
	// 46. Permutations
	// Company: Microsoft, LinkedIn
	// Description: Given a list of integers, return all possible sequences.
	// Permutations
	// [1, 2, 3] = > [1, 3, 2], [2, 1, 3], etc..
	// Solution: Use DFS & Backtracking. Use visited array to record which
	// index(number) been visited before.
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		dfs(nums, res, new boolean[nums.length], new ArrayList<Integer>());
		return res;
	}

	private void dfs(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> permutation) {
		if (permutation.size() == nums.length) {
			res.add(new ArrayList<Integer>(permutation));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}

			permutation.add(nums[i]);
			visited[i] = true;
			dfs(nums, res, visited, permutation);
			visited[i] = false;
			permutation.remove(permutation.size() - 1);
		}
	}
}
