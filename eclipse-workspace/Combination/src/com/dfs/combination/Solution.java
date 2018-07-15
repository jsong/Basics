package com.dfs.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		int[] nums = { 1, 2, 3 };
		sl.subsets(nums);

		String ss = "aab";
		// Another problem needs to look into DP. MinCut.
		// String timelimit = "ababababababababababababcbabababababababababababa";
		// int res = sl.minCut(timelimit);
		// System.out.print(res);

		String s = "abc";
		String p = "abc?";
		// This demonstrate that '?' could not be considered as empty.
		// Only '*' can be.
		boolean match = sl.isMatch(s, p);
		System.out.println("44. Wildcard matching " + match);

		String string = "a**"; // a*b false,
		boolean empty = sl.isEmpty(0, string);
		System.out.println("empty:" + empty);

		String m1 = "ab";
		String m2 = ".*c";
		boolean matches = sl.isMatch2(m1, m2);

		System.out.println("Match: " + matches);

		String split = "abc";
		List<List<String>> res = sl.splitString(split);

		System.out.println(res);
		String s1 = "catsanddog";
		List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
		// new ArrayList<String> ("cat", "cats", "and", "sand", "dog");

		List<String> res2 = sl.wordBreak2(s1, dict);

		System.out.println("Res: " + res2);
		// for (int i = 0; i < 3; i++) {
		// System.out.println("result" + i);
		// }
		letterCasePermutation("a1b2");
		int[] numsWithDup = { 1, 2, 2 };
		List<List<Integer>> rs = subsetsWithDup(numsWithDup);
		System.out.println(rs);
	}

	// 394. Decode String
	// Company:
	// Description:
	// Solution: 
	
	
	// 90. Subsets II
	// Company: Facebook
	// Description: Given array might contain duplicates, return all possible
	// subsets
	// the solution must not contains duplicate subsets.
	// Solution: 
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums); //avoid duplicates
		subsetHelper(res, 0, nums, new ArrayList<>());
		return res;
	}

	private static void subsetHelper(List<List<Integer>> res, int index, int[] nums, List<Integer> level) {
		if (!res.contains(level)) {
			res.add(new ArrayList<Integer>(level));
		}
		
		if (index == nums.length) {
			return;
		}
		
		for (int i = index; i < nums.length; i++) {
			level.add(nums[i]);
			subsetHelper(res, i + 1, nums, level);
			level.remove(level.size() - 1);
		}
	}

	// 784. Letter Case Permutation
	// Company: Facebook Yelp
	// Description: Given a string S, for eg, "a1b2", output all the possible
	// strings
	// [a1b2, a1B2, A1b2, A1B2]
	// Solution:

	public static List<String> letterCasePermutation(String S) {
		List<String> res = new ArrayList<>();
		
		return res;
	}

	// 139. Word Break
	// Company: Facebook Amazon Google Uber
	// Description: Given a string, try to find whether there are words can be
	// concatenated into.
	// Solution: Suggested using DP.
	// TODO: Revisit using DP to improve performance.
	// Now beats 66.74%
	private HashMap<String, Boolean> mem = new HashMap<>();

	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) {
			return false;
		}

		HashSet<String> dict = new HashSet<String>();
		for (String word : wordDict) {
			dict.add(word);
		}

		return wbHelper(s, dict);
	}

	// key point, use mem to record whether s has been visited before.
	// split word into left and right part.
	private boolean wbHelper(String s, HashSet<String> dict) {
		if (mem.containsKey(s)) {
			return mem.get(s);
		}

		if (dict.contains(s)) {
			mem.put(s, true);
			return true;
		}

		for (int i = 1; i < s.length(); i++) {
			String left = s.substring(0, i);
			String right = s.substring(i);
			if (dict.contains(right) && wbHelper(left, dict)) {
				mem.put(s, true);
				return true;
			}
		}

		mem.put(s, false);
		return false;
	}

	// 140. Word Break II
	// Company: Google Uber Snapchat Twitter
	// Description:
	// Solution:
	private HashMap<String, List<String>> mem2 = new HashMap<>();

	public List<String> wordBreak2(String s, List<String> wordDict) {
		List<String> res = new ArrayList<String>();

		if (s == null || s.length() == 0) {
			return res;
		}

		HashSet<String> dict = new HashSet<String>();

		for (String word : wordDict) {
			dict.add(word);
		}

		res = wbhelper2(s, dict);
		System.out.println("In Memory:" + mem2);
		return res;
	}

	private List<String> wbhelper2(String s, HashSet<String> dict) {
		if (mem2.containsKey(s)) {
			return mem2.get(s);
		}

		List<String> ans = new ArrayList<>();
		if (dict.contains(s)) {
			ans.add(s);
		}

		for (int i = 1; i < s.length(); i++) {
			String right = s.substring(i);
			if (!dict.contains(right)) {
				continue;
			}

			String left = s.substring(0, i);
			System.out.println("left:" + left);
			List<String> ans_left = wbhelper2(left, dict);
			List<String> ans_append = append(ans_left, right);
			ans.addAll(ans_append);
		}

		mem2.put(s, ans);
		System.out.println("mem:" + mem2);
		return mem2.get(s);
	}

	// {cat sand, cats and} append "dog"
	private List<String> append(List<String> prefixes, String str) {
		List<String> res = new ArrayList<String>();
		for (String s : prefixes) {
			res.add(s + " " + str);
		}

		return res;
	}

	// Lintcode: 680. Split String
	// Description: Given the string "123"
	// return [["1","2","3"],["12","3"],["1","23"]]
	// Solution:

	public List<List<String>> splitString(String s) {
		// write your code here
		List<List<String>> results = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return results;
		}

		dfsHelper(results, new ArrayList<>(), 0, s);

		return results;
	}

	private void dfsHelper(List<List<String>> results, List<String> result, int index, String s) {
		if (index == s.length()) {
			results.add(new ArrayList<String>(result));
		}

		for (int i = index; i < index + 2 && i < s.length(); i++) {
			String substring = s.substring(index, i + 1);
			result.add(substring);
			dfsHelper(results, result, i + 1, s);
			result.remove(result.size() - 1);
		}
	}

	// 10. Regular Expression Matching
	// Company: Facebook Google, Uber, Twitter, Airbnb.
	// Description: Unlike wild matching, '*' can be used to represent numbers
	// preceding alphabet. For e.g, a* could mean empty or aa, even aaa. '.' means
	// any character.
	// Solution:

	public boolean isMatch2(String s, String p) {
		if (s == null || p == null) {
			return false;
		}

		boolean[][] memo = new boolean[s.length()][p.length()];
		boolean[][] visited = new boolean[s.length()][p.length()];

		return regularmatch(s, 0, p, 0, memo, visited);
	}

	private boolean regularmatch(String s, int sIndex, String p, int pIndex, boolean[][] memo, boolean[][] visited) {
		if (sIndex == s.length()) {
			return isEmpty(pIndex, p);
		}

		if (pIndex == p.length()) {
			return sIndex == s.length();
		}

		if (visited[sIndex][pIndex]) {
			return memo[sIndex][pIndex];
		}

		boolean match = false;
		char sChar = s.charAt(sIndex);
		char pChar = p.charAt(pIndex);
		System.out.println("sIndex:" + sIndex + " " + s.charAt(sIndex) + " pIndex:" + pIndex + " " + p.charAt(pIndex));

		if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
			// need to check a*b => b, it won't out of boundary since pIndex + 2 maximum is
			// plength;
			match = regularmatch(s, sIndex, p, pIndex + 2, memo, visited) ||
			// a*b = > aab, a repeat.
					charMatch(sChar, pChar) && regularmatch(s, sIndex + 1, p, pIndex, memo, visited);
		} else {
			match = charMatch(sChar, pChar) && regularmatch(s, sIndex + 1, p, pIndex + 1, memo, visited);
		}

		visited[sIndex][pIndex] = true;
		memo[sIndex][pIndex] = match;

		return match;
	}

	private boolean isEmpty(int pIndex, String p) {
		// if p = "a", i = 0,
		// i + 1 == p.length, means there is no other character after, so it's not
		// empty.
		// in order it to be empty, it must follow a*b* pattern.
		for (int i = pIndex; i < p.length(); i += 2) {
			if (i + 1 >= p.length() || p.charAt(i + 1) != '*') {
				return false;
			}
		}

		return true;
	}

	private boolean charMatch(char sChar, char pChar) {

		return sChar == pChar || pChar == '.';
	}

	// 44. Wildcard Matching
	// Company: Facebook, Google, Twitter, Snapchat.
	// Description: Given string p check whether it matches s, p could contains "?"
	// (any string) or "*" (any strings including empty)
	// Solution: 1. Use backtracking. Please note this is only beat 17% which means
	// performance is bad.
	// TODO: solution 2. May be use DP.

	public boolean isMatch(String s, String p) {
		if (s == null || p == null) {
			return false;
		}

		boolean[][] memo = new boolean[s.length()][p.length()];
		boolean[][] visited = new boolean[s.length()][p.length()];

		return wildmatch(s, 0, p, 0, memo, visited);
	}

	private boolean wildmatch(String s, int sIndex, String p, int pIndex, boolean[][] memo, boolean[][] visited) {
		if (sIndex == s.length()) {
			return allStar(p, pIndex);
		}

		if (pIndex == p.length()) {
			return sIndex == s.length();
		}

		if (visited[sIndex][pIndex]) {
			return memo[sIndex][pIndex];
		}

		boolean match = false;

		if (p.charAt(pIndex) == '*') {
			// p could be empty, so check whether next p matches current s.
			match = wildmatch(s, sIndex, p, pIndex + 1, memo, visited)
					|| wildmatch(s, sIndex + 1, p, pIndex, memo, visited);
		} else {
			match = isCharMatch(s.charAt(sIndex), p.charAt(pIndex))
					&& wildmatch(s, sIndex + 1, p, pIndex + 1, memo, visited);
		}

		visited[sIndex][pIndex] = true;
		memo[sIndex][pIndex] = match;

		return match;
	}

	private boolean isCharMatch(char m, char n) {
		return (m == n) || n == '?';
	}

	// if s reaches end, then only under the case
	// p start from index all '*'.
	private boolean allStar(String p, int index) {
		for (int i = index; i < p.length(); i++) {
			if (p.charAt(i) != '*') {
				return false;
			}
		}

		return true;
	}

	// 132. Palindrome Partitioning II
	// Description: Given a string, trying to cut into small string pieces
	// which could make palindrome strings, return minimal cut.
	// TODO: Should use DP to solve this kind of problem which only ask for result
	// instead of all the conbinations.
	private int m_cut = 0;

	public int minCut(String s) {
		if (s == null || s.length() == 0) {
			return m_cut;
		}

		// NOTE: this is helper for using backtracking and search
		// TODO: if future using DP, then this will not need anymore.
		// List<List<String>> res = new ArrayList<>();
		m_cut = Integer.MAX_VALUE;
		cuthelper(s, 0, new ArrayList<String>());

		return m_cut;
	}

	private void cuthelper(String s, int start, List<String> list) {
		if (start == s.length()) {
			// 2 elements only need 1 cut.
			m_cut = Math.min(m_cut, new ArrayList(list).size() - 1);
			return;
		}

		for (int i = start; i < s.length(); i++) {
			String substring = s.substring(start, i + 1);
			if (!isPalindrome(substring)) {
				continue;
			}
			list.add(substring);
			cuthelper(s, i + 1, list);
			list.remove(list.size() - 1);
		}
	}

	private boolean isPalindrome(String s) {
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}

		return true;
	}

	// 216. Combination Sum III
	// Description: use up to k unique numbers start from 1, to construct target n
	// Solution:
	public List<List<Integer>> combinationSum3(int k, int n) {

		List<List<Integer>> res = new ArrayList<>();
		helper(res, new ArrayList<Integer>(), 1, k, n);

		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, int start, int k, int n) {
		if (n == 0 && k == list.size()) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = start; i < n; i++) {
			if (i > n) {
				break;
			}

			list.add(i);
			helper(res, list, i + 1, k, n - i);
			list.remove(list.size() - 1);
		}
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
	// Company: Facebook Amazon, Bloomberg, Uber, Coupang. Apple.
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
