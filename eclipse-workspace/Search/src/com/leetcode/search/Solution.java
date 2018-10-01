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
	// Time Complexity, Average O(LogN), Worst O(N).
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
	// Solution: 1. find which row it belongs to, then use binary search. 2. Binary
	// Search
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
			int value = matrix[mid / n][mid % n];

			if (value == target) {
				return true;
			} else if (value < target) {
				first = mid;
			} else {
				last = mid;
			}
		}

		if (matrix[first / n][first % n] == target || matrix[last / n][last % n] == target) {
			return true;
		}

		return false;
	}

	// 240. Search a 2D Matrix II
	// Description: m * n matrix, Integers in each row are sorted in ascending from
	// left to right. Integers in each column are sorted in ascending from top to
	// bottom.
	// Company: Amazon Microsoft Tencent Goldman Sachs # Bloomberg Google Facebook
	// Solution: O(m + n), Starting from right corner. Target < Corner, go col - 1,
	// else go row + 1, until we find the value.
	// Can also be applied to 74. However the Binary Search could be faster if (m +
	// n) become large.
	public boolean searchMatrix3(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}

		int i = 0;
		int j = matrix[0].length - 1;

		while (i < matrix.length && j >= 0) {
			int value = matrix[i][j];
			if (target == value) {
				return true;
			} else if (target > value) {
				i++;
			} else if (target < value) {
				j--;
			}
		}

		return false;
	}

	// 153. Find Minimum in Rotated Sorted Array
	// Description: Suppose an array sorted in ascending order is rotated at some
	// pivot unknown to you beforehand. Find the minimum element.
	// Company: Goldman Sachs Bloomberg Microsoft Amazon Google Salesforce #
	// Facebook eBay Twitter Baidu.
	// Solution: 1. O(N) compare every element. 2. BinarySearch
	public int findMin(int[] nums) {
		int min = Integer.MAX_VALUE;

		for (int num : nums) {
			min = Math.min(num, min);
		}

		return min;
	}

	public int findMin2(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] < nums[right]) {
				right = mid;
			} else {
				left = ++mid;
			}
		}

		return nums[left];
	}

	// 154. Find Minimum in Rotated Sorted Array II
	// Description: Suppose an array sorted in ascending order is rotated at some
	// pivot unknown to you beforehand. Same as 153. The array may contain
	// duplicates.
	// Company: Uber # Google
	// Solution: 1. O(N) Solution iterate the whole array. 2. O(LogN) average, worst
	// O(N).
	public int findMinII(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] < nums[right]) {
				right = mid;
			} else if (nums[mid] > nums[right]) {
				left = ++mid;
			} else {
				right--;
			}
		}

		return nums[left];
	}

	// 4. Median of Two Sorted Arrays
	// Description: There are two sorted arrays nums1 and nums2 of size m and n
	// respectively. Find the median of the two sorted arrays. The overall run time
	// complexity should be O(log (m+n)).
	// Company: Goldman Sachs Adobe Facebook Amazon Microsoft Tencent Google Two
	// Sigma Baidu Alibaba NetEase VMWare Walmart Labs # Bloomberg Airbnb Uber
	// Twitter Hulu eBay Apple Yahoo Lyft Aetion Pocket Gems
	// Solution: Find the mid element in the final array, binary search around.
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length;
		int m = nums2.length;
		if (n > m) { // make sure n <= m
			return findMedianSortedArrays(nums2, nums1);
		}

		int k = (n + m + 1) / 2;

		int l = 0;
		int r = n;
		int m1 = 0;
		int m2 = 0;

		while (l < r) {
			m1 = l + (r - l) / 2;
			m2 = k - m1;

			if (nums1[m1] < nums2[m2 - 1]) {
				l = m1 + 1;
			} else {
				r = m1;
			}
		}

		m1 = l;
		m2 = k - l;

		int c1 = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1], m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);

		if ((n + m) % 2 == 1) {
			return c1;
		}

		int c2 = Math.min(m1 >= n ? Integer.MAX_VALUE : nums1[m1], m2 >= m ? Integer.MAX_VALUE : nums2[m2]);

		return (c1 + c2) * 0.5;
	}
	//
	//
	//
	//

}
