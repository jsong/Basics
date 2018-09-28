package com.leetcode.search;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 35. Search Insert Position
	// Description: Given a sorted array and a target value, return the index if the
	// target is found. If not, return the index where it would be if it were
	// inserted in order.
	// Company: Google # Amazon
	// Solution: Binary Search find the middle element, check which range it belongs
	// to.
	public int searchInsert(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}

		if (nums[left] == target) {
			return left;
		}

		if (nums[right] == target) {
			return right;
		}

		if (target > nums[right]) {
			return right + 1;
		} else if (nums[left] > target) {
			return left;
		} else { // in the middle
			return left + 1;
		}
	}

	// 33. Search in Rotated Sorted Array
	// Description: Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
	// You are given a target value to search. If found in the array return its index, otherwise return -1. You may assume no duplicate exists in the array. 
	// Your algorithm's runtime complexity must be in the order of O(log n).
	// Company: Facebook Microsoft Google LinkedIn Bloomberg Amazon Alibaba Baidu Apple Samsung SnapChat # Palantir Tech IXL VMWare Expedia Mathworks Uber Yelp. 
	// Solution: Binary Search based on where the mid could be.
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		int left = 0;
		int right = nums.length - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			// 1. equal. 2. mid in left 3. mid in right
			if (target == nums[mid]) {
				return mid;
			} else if (nums[mid] >= nums[left]) { // 2.
				if (nums[left] <= target && nums[mid] > target) {
					right = mid;
				} else {
					left = mid;
				}
			} else if (nums[mid] <= nums[right]) { // 3.
				if (nums[mid] < target && nums[right] >= target) {
					left = mid;
				} else {
					right = mid;
				}
			}
		}

		if (nums[left] == target) {
			return left;
		}

		if (nums[right] == target) {
			return right;
		}

		return -1;
	}

}
