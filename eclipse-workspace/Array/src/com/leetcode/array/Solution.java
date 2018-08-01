package com.leetcode.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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

		int mod2 = 0 % 2;
		System.out.println(mod2);

		int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		sl.setZeroes(matrix);

		int[] ratings = { 1, 2, 87, 87, 87, 2, 1 };
		// int candyCount = sl.candy(ratings);

		int[] mArray = { 3, 2, 3 };
		int mElement = sl.majorityElement(mArray);

		int[] dup = { 1, 2, 3, 1 };
		boolean dupContains = sl.containsDuplicate1(dup);

		int[] increase = { 2, 1, 5, 0, 3 };
		sl.increasingTriplet(increase);

		int[][] zeroes = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		sl.setZeroes(zeroes);

		int[][] board = { { 0, 1, 0 }, { 1, 1, 0 } };
		sl.gameOfLife(board);

		int[] gas = { 1, 2, 3, 4, 5 };
		int[] cost = { 3, 4, 5, 1, 2 };

		int start = sl.canCompleteCircuit(gas, cost);
	}

	// 31. Next Permutation
	// Company: Google
	// Description: Implement next permutation, which rearranges numbers into the
	// lexicographically next greater permutation of numbers.
	// for eg.
	// 1,2,3 -> 1,3,2
	// 3,2,1 -> 1,2,3
	// 1,1,5 -> 1,5,1
	// Solution:
	public void nextPermutation(int[] nums) {

	}

	// 60. Permutation Sequence
	// Company: Amazon Bloomberg Facebook LinkedIn Twitter
	// Description: Given n construct all n! permutations, and k which you need to
	// return the kth permutation.
	// Solution:

	// 36. Valid Sudoku
	// Company: Google Microsoft Uber Pinterest Apple Snapchat
	// Description: Given n * n sudoku, the row and col should not contain duplicate
	// number, also the small 3 * 3 cube
	// should not contains any duplicate numbers.
	// Solution:

	// 42. Trapping Rain Water
	// Company: Amazon Facebook Lyft Microsoft Intuit Google Airbnb Bloomberg Uber
	// Alibaba Twitter Apple Zenefits.
	// Description: Given n non-negative integers representing an elevation map,
	// compuate how much water it's able to hold.
	// for eg, [0,1,0,2,1,0,1,3,2,1,2,1] could hold maximum 6 units of rain water.
	// Solution:

	// 48. Rotate Image
	// Company: Amazon Google Microsoft Baidu Houzz Adobe Apple
	// Description: Given an n * n matrix representing an image, rotate the image by
	// 90 degrees. Should do this in-place.
	// Solution:

	// 66. Plus One
	// Company: Google Adobe Bloomberg eBay Facebook.
	// Description: Given a non-empty array of digits representing a non-negative
	// integer, plus one to the integer. 
	// eg. [1, 2, 3] => [1, 2, 4]
	// Solution: Add base on the digits[], use digits[i] to temporarily store the results.
	public int[] plusOne(int[] digits) {
		int c = 1; 
		
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i] += c;
			c = digits[i] / 10;
			digits[i] %= 10;
		}
		
		if (c > 0) { //has carry.
			int[] tmp = new int[digits.length + 1];
			System.arraycopy(digits, 0, tmp, 1, digits.length);
			tmp[0] = c;
			return tmp;
		} else {
			return digits;
		}
    }

	// 70. Climbing Stairs
	// Company: Amazon Bloomberg LinkedIn Tencent Alibaba Uber Google Zenefits Apple
	// Adobe
	// Description: Given n steps staris, each time you can either climb 1 or 2
	// steps, in how many distinct ways can you climb to the top.
	// Solution: 1. when reaching stairs n, it either comes from n - 1 steps or n -
	// 2 steps. So F(n) = F(n- 1) + F(n - 2) will take O(n) space.
	// 2. O(1) space.
	public int climbStairs(int n) {
		int[] f = new int[n + 1]; // array starts from 0.
		f[0] = 1;
		f[1] = 1;

		for (int i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}

		return f[n];
	}

	public int climbStairs2(int n) {
		int prev = 0;
		int cur = 1;

		for (int i = 1; i <= n; i++) {
			int tmp = cur;
			cur += prev;
			prev = tmp;
		}

		return cur;
	}

	// Used by 73. Set Matrix Zeroes
	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 73. Set Matrix Zeroes
	// Company: Facebook Amazon Expedia Microsoft Bloomberg Google Snapchat Twitter
	// Description: m * n matrix, if element is 0 set its entire row and column to
	// 0, in place.
	// Solution: 1. Use extra m + n space, 2. Use no extra space.
	public void setZeroes(int[][] matrix) {
		int[] m = new int[matrix.length]; // row
		int[] n = new int[matrix[0].length]; // col

		Arrays.fill(m, 1);
		Arrays.fill(n, 1);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					m[i] = 0;
					n[j] = 0;
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (n[j] == 0) {
					matrix[i][j] = 0;
				}

				if (m[i] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	public void setZeroes2(int[][] matrix) {

	}

	// 134. Gas Station
	// Company: Microsoft Amazon Google Alibaba
	// Description: There are N gas stations along a circular route, gas[i] indicate
	// amount of gas at station i,
	// cost[i] means gas cost from station i to i + 1. Return the gas stations index
	// if you can travel around the circuit once.
	// otherwise return -1;
	// for eg. gas = [1,2,3,4,5] cost = [3,4,5,1,2], return 3. Starting from station
	// 3(4).
	// Solution: assume it starts i, then starting i, we do the calculation until we found the sum < 0, then we start move i to the next position. 
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int k = -1;
		int sum = 0;

		for (int i = 0; i < gas.length; i++) {
			k = i;
			sum = 0;
			boolean exitinnerLoop = false;
			
			for (int j = k; j < gas.length; j++) { // k till end
				sum += gas[j] - cost[j];
				if (sum < 0) {
					exitinnerLoop = true;
					break;
				}
			}

			if (exitinnerLoop) {
				continue;
			}
			
			for (int j = 0; j < k; j++) { // 0 to k - 1.
				sum += gas[j] - cost[j];
				if (sum < 0) {
					break;
				}
			}

			if (sum >= 0) {
				return k;
			}
		}

		return -1;
	}

	// 135. Candy
	// Company: Google Microsoft Uber Snapchat Facebook Palantir
	// Description: N children standing in line, each child is assigned a rating, 1.
	// each child must have one candy 2. children has a higher rating ( > ) get more
	// than their neighbors.
	// Solution:
	public int candy(int[] ratings) {
		int left = 0;
		int[] inc = new int[ratings.length];
		
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i - 1] < ratings[i]) {
				inc[i] = Math.max(++left, inc[i]);
			} else {
				left = 0;
			}
		}
		
		int right = 0;
		// right to left;
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				inc[i] = Math.max(++right, inc[i]);
			} else {
				right = 0;
			}
		}
		
		int sum = ratings.length; 
		for (int i: inc) {
			sum += i;
		}
		
		return sum;
	}

	// 169. Majority Element
	// Company: Amazon Google Microsoft Tencent Baidu Zenefits Adobe
	// Description: Given an array of size n, find the majority element. The
	// majority element is the element that appears more than
	// [n / 2] times. Return the majority element;
	// Solution: 1. Sort the array, return the middle element. 2. Not use sort,
	// -+-+-, after it becomes 0, then the result will be
	// the element. 3. Use Hashtable<element, count>
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		// int counter = 1;
		// int mIndex = 0;
		// for (int i = 0; i < nums.length - 1; i++) {
		// if (nums[i] == nums[i + 1]) {
		// counter++;
		// if (counter > nums.length / 2) {
		// mIndex = i;
		// break;
		// }
		// } else {
		// counter = 1;
		// }
		// }

		int mIndex = nums.length / 2;
		return nums[mIndex];
	}

	public int majorityElement3(int[] nums) {
		HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		int ret = 0;

		for (int num : nums) {
			if (myMap.containsKey(num)) {
				myMap.put(num, myMap.get(num) + 1);
			} else {
				myMap.put(num, 1);
			}
			if (myMap.get(num) > nums.length / 2) {
				ret = num;
				break;
			}
		}
		return ret;
	}

	// 189. Rotate Array
	// Company: Microsoft Google Amazon Bloomberg
	// Description: Given an array, rotate the array to the right by k steps, where
	// k is non-negative.
	// Try to think in-place O(1) space solution.
	// Solution: 1. 1234 567 -> 2. 4321 765 -> 3. 567 1234;
	public void rotate(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - k - 1);
		reverse(nums, nums.length - k, nums.length - 1);
		reverse(nums, 0, nums.length - 1);
	}

	private void reverse(int[] nums, int i, int j) {
		while (i < j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
			i++;
			j--;
		}
	}

	// utility method.
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// 217. Contains Duplicate
	// Company: Google Baidu Apple Airbnb Yahoo Palantir
	// Description: Given an array of integers, find if the array contains any
	// duplicate.
	// eg, [1, 2, 3, 1] true, since 1 appears more than once.
	// Solution: 1. Hashset 2. Sort
	public boolean containsDuplicate2(int[] nums) {
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				return true;
			}
		}

		return false;
	}

	public boolean containsDuplicate1(int[] nums) {
		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < nums.length; i++) {
			if (!set.add(nums[i])) {
				return true;
			}
		}

		return false;
	}

	// 219. Contains Duplicate II
	// Company: Google Uber Airbnb Palantir
	// eg. [1, 2, 3, 1] k = 3 => true; [1, 0, 1, 1], k = 1 true; [1, 2, 3, 1, 2, 3]
	// k = 2, false;
	// Description: Given an array of integers and an integer k, find out whether
	// there are two distinct indices i and j in the array such that nums[i] =
	// nums[j] and the absolute difference between i and j is at most k.
	// Solution: Use HashMap to record the previous index.
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		// key = nums[i], value = index;
		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				int preIndex = map.get(nums[i]);
				if (i - preIndex <= k) {
					return true;
				} else {
					map.put(nums[i], i);
				}
			} else {
				map.put(nums[i], i);
			}
		}

		return false;
	}

	// 220. Contains Duplicate III
	// Company: Google Coursera Airbnb Palantir
	// Description: Given an array of integers, find out whether there are two
	// distinct indices i and j in the array such that the absolute difference
	// between nums[i] and nums[j] is at most t and the absolute difference between
	// i and j is at most k.
	// Solution: Use TreeSet or double loop could solve this problem.
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		TreeSet<Integer> set = new TreeSet<>();

		for (int i = 0; i < nums.length; i++) {
			int x = nums[i];
			Integer floor = set.floor(x);
			Integer ceiling = set.ceiling(x);
			if ((floor != null && x <= floor + t) || (ceiling != null && x >= ceiling - t)) {
				return true;
			}
			set.add(x);
			// sliding window.
			if (i >= k) {
				set.remove(nums[i - k]);
			}
		}

		return false;
	}

	// 289. Game of Life
	// Company: Dropbox Google Uber Square Bloomberg Snapchat Two Sigma
	// Description:
	// 1. Any live cell with fewer than two live neighbors dies, as if caused by
	// under-population.
	// 2. Any live cell with two or three live neighbors lives on to the next
	// generation.
	// 3. Any live cell with more than three live neighbors dies, as if by
	// over-population..
	// 4. Any dead cell with exactly three live neighbors becomes a live cell, as if
	// by reproduction.
	// Solution:
	public void gameOfLife(int[][] board) {

	}

	// 334. Increasing Triplet Subsequence
	// Company: Google Facebook
	// Description: Given an unsorted array return whether an increasing subsequence
	// of length 3 exists or not in the array. Formally the function should:
	// Return true if there exists i, j, k such that arr[i] < arr[j] < arr[k] given
	// 0 ≤ i < j < k ≤ n-1 else return false.
	// Note: Your algorithm should run in O(n) time complexity and O(1) space
	// complexity.
	// Solution: Record the minimum and the second minimum element from the array,
	// then if there is element which is larger than 1st, 2nd,
	// then we should return true;
	public boolean increasingTriplet(int[] nums) {
		int x1 = Integer.MAX_VALUE; // minimum
		int x2 = Integer.MAX_VALUE; // second minimum

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= x1) {
				x1 = nums[i];
			} else if (nums[i] <= x2) {
				x2 = nums[i];
			} else {
				return true; // x1 < x2 < nums[i];
			}
		}

		return false;
	}

	//
	// Company:
	// Description:
	// Solution:

	// 238. Product of Array Except Self
	// Company: Lyft Facebook Amazon Apple Hulu Google Zenefits Expedia Microsoft
	// Yelp LinkedIn
	// Description: Given an array nums of n integers where n > 1, return an array
	// output such that output[i] is equal to the product of all the elements of
	// nums except nums[i].
	// e.g: Input: [1, 2, 3, 4], Output: [24, 12, 8, 6]. Not use extra space(output
	// array[n] is not consider extra).
	// Solution: [a1, a2, a3, a4] = > left -> [1, a1, a1a2, a1a2a3]; right
	// ->[a2a3a4, a3a4, a4, 1]
	public int[] productExceptSelf(int[] nums) {
		int[] product = new int[nums.length];
		int left = 1;

		for (int i = 0; i < nums.length; i++) {
			product[i] = left;
			left *= nums[i];
		}

		int right = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			product[i] *= right;
			right *= nums[i];
		}

		return product;
	}

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
