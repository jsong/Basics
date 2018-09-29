package com.leetcode.search;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		int[] nums = { 1 };
		int[] r = sl.searchRange(nums, 1);
	}

	// 81. Search in Rotated Sorted Array II
	// Description: Same as 33. However, allow duplicates in array.
	// Company: Facebook Microsoft Google LinkedIn # Alibaba Amazon
	// Solution: Skip the duplicates, by start++. Others are the same as 33.
	public boolean search2(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return false;
		}

		int start = 0;
		int end = nums.length - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target) {
				return true;
			} else if (nums[start] < nums[mid]) {
				if (target >= nums[start] && target < nums[mid]) {
					end = mid;
				} else {
					start = mid;
				}
			} else if (nums[start] > nums[mid]) {
				if (target > nums[mid] && target <= nums[end]) {
					start = mid;
				} else {
					end = mid;
				}
			} else {
				start++; // skip duplicates.
			}
		}

		if (nums[start] == target || nums[end] == target) {
			return true;
		}

		return false;
	}

	// 61. Search for a Range (LintCode)
	// Description: Given a sorted array of n integers, find the starting and ending
	// position of a given target value.
	// Company: LinkedIn
	// Solution: Binary Search lower bound / upper bound.
	public int[] searchRange(int[] A, int target) {
		int lowerBound = lowerBound(A, target, 0, A.length);
		int upperBound = upperBound(A, target, 0, A.length);

		if (lowerBound == A.length || A[lowerBound] != target) {
			return new int[] { -1, -1 };
		}

		return new int[] { lowerBound, upperBound - 1 };
	}

	private int lowerBound(int[] A, int target, int start, int end) {
		while (start != end) {
			int mid = start + (end - start) / 2;
			if (target > A[mid]) {
				start = ++mid;
			} else {
				end = mid;
			}
		}

		return start;
	}

	private int upperBound(int[] A, int target, int start, int end) {
		while (start != end) {
			int mid = start + (end - start) / 2;
			if (target >= A[mid]) { // upper
				start = ++mid;
			} else {
				end = mid;
			}
		}

		return start;
	}

	// 35. Search Insert Position
	// Description: Given a sorted array and a target value, return the index if the
	// target is found. If not, return the index where it would be if it were
	// inserted in order.
	// Company: Google # Amazon
	// Solution: 1. Binary Search find the middle element, check which range it
	// belongs
	// to.2. lower bound.
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
	// Description: Suppose an array sorted in ascending order is rotated at some
	// pivot unknown to you beforehand.
	// You are given a target value to search. If found in the array return its
	// index, otherwise return -1. You may assume no duplicate exists in the array.
	// Your algorithm's runtime complexity must be in the order of O(log n).
	// Company: Facebook Microsoft Google LinkedIn Bloomberg Amazon Alibaba Baidu
	// Apple Samsung SnapChat # Palantir Tech IXL VMWare Expedia Mathworks Uber
	// Yelp.
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
			} else if (nums[mid] > nums[left]) { // 2.
				if (nums[left] <= target && nums[mid] > target) {
					right = mid;
				} else {
					left = mid;
				}
			} else if (nums[mid] < nums[left]) { // 3.
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

	// 74. Search a 2D Matrix
	// Description: m * n matrix. Integers in each row are sorted from left to
	// right. The first integer of each row is greater than the last integer of the
	// previous row.
	// Company: Amazon Twitter # Microsoft Facebook Baidu Uber
	// Solution: 1. find which row it belongs to, then use binary search. 2. Binary Search
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null) {
			return false;
		}

		int m = matrix.length;
		if (m == 0) {
			return false;
		}

		int n = matrix[0].length;
		if (n == 0) {
			return false;
		}

		int i = 0;
		for (i = 0; i < m;) {
			if (target >= matrix[i][0]) {
				i++;
			} else {
				break;
			}
		}

		if (i == 0) {
			return false;
		}

		int[] nums = matrix[i - 1];

		int left = 0;
		int right = n - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}

		if (nums[left] == target || nums[right] == target) {
			return true;
		}

		return false;
	}
	
	public boolean searchMatrix2(int[][] matrix, int target) {
		int m = matrix.length;
		if (m == 0) {
			return false;
		}
		
		int n = matrix[0].length;
		
		// consider the matrix as whole big array.
		int first = 0;
		int last = m * n - 1;
		
		while (first + 1 < last) {
			int mid = first + (last - first) / 2;
			int value = matrix[mid / n] [mid % n];
			
			if (value == target) {
				return true;
			} else if (value < target) {
				first = mid;
			} else {
				last = mid;
			}
		}
		
		if (matrix[first / n] [first % n] == target || matrix[last / n] [last % n] == target) {
		    return true;
		}
		
		return false;
	}
	//
	//
	//
	//

	//
	//
	//
	//

	//
	//
	//
	//

	//
	//
	//
	//

}
