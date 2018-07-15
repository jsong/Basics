package com.facebook.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		// int[] nums = { 1, 2, 3, 4, 5 };
		// int v = sl.minSubArrayLen(11, nums);

		int a = 'a', b = 'b';
		System.out.println(Integer.toBinaryString(a));

		System.out.println(Integer.toBinaryString(b));
		a = a ^ b;
		System.out.println(Integer.toBinaryString(a));
		b = a ^ b;
		System.out.println(Integer.toBinaryString(b));
		a = a ^ b;
		System.out.println(Integer.toBinaryString(a));

		int[] input = { 10, 20, 30, 40, 50 }; // input array
		int k = 3; // sequence length

		List<int[]> subsets = new ArrayList<>();

		int[] s = new int[k]; // here we'll keep indices
								// pointing to elements in input array

		if (k <= input.length) {
			// first index sequence: 0, 1, 2, ...
			for (int i = 0; (s[i] = i) < k - 1; i++)
				;
			subsets.add(getSubset(input, s));
			for (;;) {
				int i;
				// find position of item that can be incremented
				for (i = k - 1; i >= 0 && s[i] == input.length - k + i; i--) {
					System.out.println("i:" + i);
				}
				if (i < 0) {
					break;
				}
				s[i]++; // increment this item
				for (++i; i < k; i++) { // fill up remaining items
					s[i] = s[i - 1] + 1;
				}
				subsets.add(getSubset(input, s));
			}
		}

		int res = fun1(2, 3);
		int[] arr = { 2, 3, 1, 2, 4, 3 };
		int val = minSubArrayLen(7, arr);

		String window = minWindow("ADOBECODEBANC", "ABC");
		
		List<List<Integer>> combine = combine(4, 2);

	}

	static int[] getSubset(int[] input, int[] subset) {
		int[] result = new int[subset.length];

		for (int i = 0; i < subset.length; i++) {
			result[i] = input[subset[i]];
		}

		return result;
	}

	static int fun1(int x, int y) {
		if (x == 0)
			return y;
		else
			return fun1(x - 1, x + y);
	}

	// Problem: 77. Combinations
	// Description: Given two integers n and k, return all the k numbers of the
	// combinations.
	// For eg. 4,2, then [1, 2] [1, 3] [1, 4] [2, 3] [2, 4] [3, 4] will be returned
	// Solution: use recursion, as illustrate above, starts from 1, then 2, 3, 4, and then remove 1, 
	// starts from 2.
	public static List<List<Integer>> combine(int n, int k) {
		int[] source = new int[n];
		for (int i = 0; i < n; i++) {
			source[i] = i + 1; //starts from 1.
		}
		ArrayList<List<Integer>> res = new ArrayList<>();
//		for (int i = 0; i < n; i++) {
			dfsaddnumberhelper(source, res, 0, k, new ArrayList<>());
//		}
		
		return res;
 	}
	
	private static void dfsaddnumberhelper(int[] source, List<List<Integer>> res, int start, int k, ArrayList<Integer> level) {
		if (k == level.size()) {
//			res.add(level);
			res.add(new ArrayList<>(level));
			return;
		}
		for (int i = start; i < source.length; i++) {
			level.add(source[i]);
			dfsaddnumberhelper(source, res, i + 1, k, level);
			level.remove(level.size() - 1);			
		}
	}

	// Problem: 76. Minimum Window Substring
	// Given a string S and a string T, find the minimum window in S which will
	// contain all the characters in T in complexity O(n).
	// Example:
	// Input: S = "ADOBECODEBANC", T = "ABC"
	// Output: "BANC"
	// Solution:

	public static String minWindow(String s, String t) {
		int sourceLen = s.length();
		int targetLen = t.length();
		int[] cnt = new int[128];
		int minleft = 0;
		int minLen = Integer.MAX_VALUE;
		int count = 0;
		int left = 0;
		for (int i = 0; i < t.length(); i++) {
			cnt[t.charAt(i)]++;
		}

		for (int right = 0; right < sourceLen; right++) {
			cnt[s.charAt(right)]--;
			if (cnt[s.charAt(right)] >= 0) {
				count++;
			}

			while (count == targetLen) {
				if (right - left + 1 < minLen) {
					minLen = right - left + 1;
					minleft = left;
				}

				cnt[s.charAt(left)]++;
				if (cnt[s.charAt(left)] > 0) {
					count--;
				}
				left++;
			}
		}

		return minLen == Integer.MAX_VALUE ? "" : s.substring(minleft, minleft + minLen);
	}

	// Problem: 209. Minimum Size Sub array Sum
	// Description: Find the minimum contiguous sub array of which sum >= s, if not
	// found just return 0 instead.
	// Company: Facebook.
	// Solution: Two pointer and maintain a sliding window to make sure the value in
	// between is
	// the minimum numbers required to be larger than the target.
	public static int minSubArrayLen(int s, int[] nums) {
		int left = 0;
		int right = 0;
		int min = Integer.MAX_VALUE;
		int sum = 0;

		for (right = 0; right < nums.length; right++) {
			sum += nums[right];
			if (sum >= s) {
				min = Math.min(min, right - left + 1);
				while (sum >= s) {
					sum = sum - nums[left++];
					if (sum >= s) {
						min = Math.min(min, right - left + 1);
					}
				}
			}
		}

		return min == Integer.MAX_VALUE ? 0 : min;
	}

}
