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

	// 90. Subsets II
	// Company: Facebook # Microsoft
	// Description: Given a collection of integers that might contain duplicates, S, return all possible subsets.
	// Solution: Time: O(2 ^ n), Space O(n); Same solution as Subset I, the only difference is
	// we check whether during the same level, that element has already been added or not.
	// It could be added already in previous dfs(next level.).
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
			List<List<Integer>> res = new ArrayList<>();
			List<Integer> path = new ArrayList<>();
			Arrays.sort(nums);
			dfsDupHelper(0, path, res, nums);

			return res;
	}

	private void dfsDupHelper(int start, List<Integer> path, List<List<Integer>> res, int[] nums) {
			res.add(new ArrayList<Integer> (path));

			for (int i = start; i < nums.length; i++) {
					if (i != start && nums[i - 1] == nums[i]) continue;
					path.add(nums[i]);
					dfsDupHelper(i + 1, path, res, nums);
					path.remove(path.size() - 1);
			}
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

	// 17. Letter Combinations of a Phone Number
	// Description: Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
	// Company: Facebook Amazon Google Microsoft Uber JPMorgan Morgan Stanley # Lyft Yelp Airbnb Apple Adobe Pinterest Square Paypal Dropbox Alibaba Symantec
	// Solution: Time: O(3 ^ n), Space O(n). DFS, index needs to be increased to pick up the next characters, however each time i starts 0.
    private String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations(String digits) {
    	List<String> res = new ArrayList<>();
    	if (digits == null || digits.length() == 0) {
    		return res;
    	}

    	dfsLetter(0, res, digits, "");

    	return res;
    }

    private void dfsLetter(int index, List<String> res, String digits, String s) {
    	if (index == digits.length()) {
    		res.add(s);
    		return;
    	}

    	char character = digits.charAt(index);
    	String mappingString = mapping[character - '0'];
    	for (int i = 0; i < mappingString.length(); i++) {
    		dfsLetter(index + 1, res, digits, s + mappingString.charAt(i));
    	}
    }

}
