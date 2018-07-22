package com.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		int[] nums = { 1, 1, 2 };
		int[] nums2 = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };

		sl.removeDuplicates(nums);
		sl.removeDuplicates2(nums2);
	}

	// 1. Two Sum
	// Company: Facebook Microsoft Amazon Bloomberg LinkedIn Apple Airbnb Yelp Yahoo
	// Adobe Dropbox
	// Description: Given an array, return indices of the two numbers such that they can 
	// add up to a specific target.
	// Solution: Use Hashmap to store the key and indexes, if target - cur has a match, then return.
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				int j = map.get(target - nums[i]);
				return new int[] {i, j};
			}
			map.put(nums[i], i);
		}
	
		return new int[] {};
	}

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:

	//
	// Company:
	// Description:
	// Solution:

	// 128. Longest Consecutive Sequence
	// Company: Google, Facebook
	// Description: Given an unsorted array of integers, find the length of the
	// longest
	// consecutive elements sequence.
	// Solution: 1. Olog(n) sorted first, then seek for the consecutive next
	// element. 2.
	// O(n) solution. Use Hashset to record each element, then starting for n in
	// nums, search
	// up and search down.
	public int longestConsecutive2(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int n : nums) {
			set.add(n);
		}

		int longest = 0;

		for (int i : nums) {
			int counter = 1;

			for (int j = i - 1; set.contains(j); j--) {
				counter++;
				set.remove(j);
			}

			for (int j = i + 1; set.contains(j); j++) {
				counter++;
				set.remove(j);
			}

			longest = Math.max(longest, counter);
		}

		return longest;
	}

	public int longestConsecutive(int[] nums) {
		if (nums.length == 0 || nums.length == 1) {
			return nums.length;
		}

		Arrays.sort(nums);
		int counter = 1;
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] + 1 == nums[i + 1]) {
				counter++;
			} else if (nums[i] != nums[i + 1]) {
				counter = 1;
			}
			res = Math.max(counter, res);
		}

		return res;
	}

	// 26. Remove Duplicates from Sorted Array
	// Company: Facebook Microsoft Bloomberg
	// Description: Given a sorted array, remove duplicates in-place, such that each
	// element only appear once.
	// return the new length of the array, so that within the length the array does
	// not have the duplicates.
	// Solution: use left pointer to indicate the actual length, only ++ when the
	// adjacent elements are different.
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int left = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] != nums[i + 1]) {
				nums[++left] = nums[i + 1];
			}
		}

		// starting from 0;
		return left + 1;
	}

	// 80. Remove Duplicates from Sorted Array II
	// Company: Facebook
	// Description: Remove the duplicates in-place, so that the duplicates appears
	// at most twice. Return the new length.
	// eg. Given nums = [1,1,1,2,2,3], => return 5, which will be [1, 1, 2, 2, 3];
	// Solution:
	public int removeDuplicates2(int[] nums) {
		int i = 0;

		for (int n : nums) {
			if (i < 2 || n > nums[i - 2]) {
				nums[i++] = n;
			}
		}

		return i;
	}
}
