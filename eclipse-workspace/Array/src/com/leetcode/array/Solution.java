package com.leetcode.array;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		int[] nums = {1, 1, 2};
		sl.removeDuplicates(nums);
	}

	// 26. Remove Duplicates from Sorted Array
	// Company: Facebook Microsoft Bloomberg
	// Description: Given a sorted array, remove duplicates in-place, such that each element only appear once.
	// return the new length of the array, so that within the length the array does not have the duplicates.
	// Solution: use left pointer to indicate the actual length, only ++ when the adjacent elements are different.
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

}
