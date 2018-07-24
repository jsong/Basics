package com.leetcode.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		int[] nums = { 1, 1, 2 };
		int[] nums2 = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };

		sl.removeDuplicates(nums);
		sl.removeDuplicates2(nums2);

		int[] closetNums = { -1, 2, 1, -4 };
		int closetRes = sl.threeSumClosest(closetNums, 1);

		int[] fsum = { -3, -1, 0, 2, 4, 5 };

		sl.fourSum(fsum, 0);
		
		int mod = 0; 
		int mod2 = 0 % 2;
		System.out.println(mod2);
	}

	// 31. Next Permutation
	// Company: Google
	// Description: Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
	// for eg. 
	//	1,2,3 -> 1,3,2
	//	3,2,1 -> 1,2,3
	//	1,1,5 -> 1,5,1
	// Solution:
    public void nextPermutation(int[] nums) {
    		
    }

	//
	// Company:
	// Description:
	// Solution:
	
	//
	// Company:
	// Description:
	// Solution:
	
	
	// 1. Two Sum
	// Company: Facebook Microsoft Amazon Bloomberg LinkedIn Apple Airbnb Yelp Yahoo
	// Adobe Dropbox
	// Description: Given an array, return indices of the two numbers such that they
	// can
	// add up to a specific target.
	// Solution: Use Hashmap to store the key and indexes, if target - cur has a
	// match, then return.
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				int j = map.get(target - nums[i]);
				return new int[] { i, j };
			}
			map.put(nums[i], i);
		}

		return new int[] {};
	}

	// 15. 3Sum
	// Company: Facebook Microsoft Amazon Bloomberg Adobe Works Applications
	// Description: Given an array nums of n integers, are there elements a, b, c in
	// nums such that a + b + c = 0?
	// Solution: First sort, and then anchor the i, use two pointers left and right
	// to find the other two elements.
	// Complexity Onlogn and On2 => On2
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		for (int i = 0; i < nums.length - 2; i++) { // use the right except i to find the other sum
			int left = i + 1;
			int right = nums.length - 1;
			int sum = 0 - nums[i];

			if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
				while (left < right) {
					if (nums[left] + nums[right] == sum) {
						res.add(Arrays.asList(nums[i], nums[left], nums[right]));
						while (left < right && nums[left] == nums[left + 1])
							left++; // remove left duplicates
						while (left < right && nums[right] == nums[right - 1])
							right--; // remove right duplicates
						left++;
						right--;
					} else if (nums[left] + nums[right] < sum) {
						left++;
					} else {
						right--;
					}
				}
			}
		}

		return res;
	}

	// 16. 3Sum Closest
	// Company: Bloomberg
	// Description: Given array, find three integers so that the sum is closest to
	// target. Return the SUM, not the closest value.
	// Solution: Anchor i, and then for the left and right, find the minGap so does
	// the sum;
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int minGap = Integer.MAX_VALUE;
		int res = 0;
		for (int i = 0; i < nums.length - 2; i++) { // use the right except i to find the other sum
			int left = i + 1;
			int right = nums.length - 1;

			while (left < right) {
				int sum = nums[left] + nums[right] + nums[i];
				int gap = Math.abs(target - sum);

				if (gap < minGap) {
					minGap = gap;
					res = sum;
				}

				if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}

		return res;
	}

	// 18. 4Sum
	// Company: N/A
	// Description: Given an array and a target, find a, b, c, d such that a + b + c
	// + d = target
	// find all unique quad tuples.
	// Solution: Use HashMap to store the sum and the list which contains all the
	// pairs. Then iterate through the
	// target - sum, find all the possible pairs, remove the duplicate and then
	// sorted.
	public List<List<Integer>> fourSum(int[] nums, int target) {
		HashMap<Integer, List<List<Integer>>> map = new HashMap<>(); // key as sum, value as indices;
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				List<Integer> list = new ArrayList<>(Arrays.asList(i, j));
				List<List<Integer>> temp = map.get(nums[i] + nums[j]);

				if (temp == null) {
					temp = new ArrayList<>();
					map.put(nums[i] + nums[j], temp);
				}
				temp.add(list);
			}
		}

		Set<List<Integer>> ret = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int sum = target - nums[i] - nums[j];
				if (map.containsKey(sum)) {
					List<List<Integer>> lists = map.get(sum);

					for (List<Integer> list : lists) {
						if (list.get(0) == i || list.get(1) == i || list.get(1) == j || list.get(0) == j) { // no
																											// duplicates
																											// allowed.
							continue;
						}

						List<Integer> level = Arrays.asList(nums[i], nums[j], nums[list.get(0)], nums[list.get(1)]);
						Collections.sort(level);
						ret.add(level);
					}
				}
			}
		}

		return new ArrayList<List<Integer>>(ret);
	}

	// 27. Remove Element
	// Company: N/A
	// Description: Given an array, remove the target value and return the new
	// length of the array. Must in-place and O(1) memory.
	// Solution: Use index, check whether it's equal to val, if not then re-write
	// the original array.
	public int removeElement(int[] nums, int val) {
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[index++] = nums[i];
			}
		}

		return index;
	}

	// 283. Move Zeroes
	// Company: Facebook Bloomberg
	// Description: Given an array, move all the zero's to the back of the array
	// while maintaining the relative order of
	// the non-zero elements.
	// Solution: Find non zero element, increase the index, reset from the index to
	// length with 0. Same idea of remove element.
	public void moveZeroes(int[] nums) {
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[index++] = nums[i];
			}
		}

		for (int i = index; i < nums.length; i++) {
			nums[i] = 0;
		}
	}

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
