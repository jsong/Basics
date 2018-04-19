package com.facebook.general.twosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 2, 1, 3, 4 };
		Solution sl = new Solution();
		int[] res = sl.twoSum(nums, 6);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		// int a = arr[0];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// int closetNum = sl.threeSumClosest(nums, 1);
		int index = sl.partitionArray(nums, 3);
		System.out.println(index);
		int[] unsortedArray = {2,1};
		int result = sl.findKthLargest(unsortedArray, 2);
		System.out.println(result);
	}

	// Problem: 215. Kth Largest Element in an Array
	public int findKthLargest(int[] nums, int k) {
		int left = 0, right = nums.length - 1;
		while (true) {
			int pos = partition(nums, left, right);
			if (pos + 1 == k) {
				return nums[pos];
			} else if (pos + 1 < k) {
				right = pos - 1;
			} else {
				left = pos + 1;
			}
		}
	}

	private int partition(int[] nums, int left, int right) {
		int pivot = nums[left];
		int start = left + 1;
		int end = right; 
		while (start < end) {
			if (nums[start] < pivot && nums[end] > pivot) {
				swap(nums, start, end);
				start++;
				end--;
			}
			while (nums[start] > pivot) start++;
			while (nums[end] < pivot) end--;
			
		}
		swap(nums, left, end);
		return end;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// 1. Two Sum
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int left = 0, right = 0;
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
			if (map.containsKey(target - nums[i])) {
				left = i;
				right = map.get(target - nums[i]);
				if (left != right)
					break;
			}
		}

		return new int[] { left, right };
	}

	// Problem: 31. Partition Array
	// Description: Move all the elements who is greater than k to the left, larger
	// to the right,
	// Return the first index.
	public int partitionArray(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int left = 0, right = nums.length - 1;
		while (left <= right) {

			while (left <= right && nums[left] < k) {
				left++;
			}

			while (left <= right && nums[right] >= k) {
				right--;
			}

			if (left <= right) {
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;

				left++;
				right--;
			}
		}
		return left;
	}
	//Problem: 75. Sort Colors
	//0, 1, 2 represent three different colors, use algorithm to seperate them.
	//Company: Facebook
	//1,2,0
	public void sortColors(int[] nums) {
		int left = 0; 
		int index = 0;
		int right = nums.length - 1;
		while (index <= right) {
			if(nums[index] == 1) {
				index++;
			} else if (nums[index] == 0) {
				swap(nums, index++, left++);
			} else {
				swap(nums, index, right--);
			}
		}
	}
	
	
	// Problem: 16 Leetcode. 3Sum Closest
	// Description return the SUM which is the closest to the target.
	// O(n^2);
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int min = Integer.MAX_VALUE;
		int sumcloset = 0;
		for (int i = 0; i < nums.length; i++) {
			int left = 0, right = i - 1;
			while (left < right) {
				int sum = nums[left] + nums[right] + nums[i];
				if (sum < target) {
					if (min > target - sum) {
						min = target - sum;
						sumcloset = sum;
					}
					// min = Math.min(min, target - sum);
					left++;
				} else {
					if (min > sum - target) {
						min = sum - target;
						sumcloset = sum;
					}
					// min = Math.min(min, sum - target);
					right--;
				}
			}
		}

		return sumcloset;
	}

	// Problem: 533 LintCode
	// Description: Two Sum Closest to target
	// Algorithm: O(nlog(n))
	/**
	 * @param nums
	 *            an integer array
	 * @param target
	 *            an integer
	 * @return the difference between the sum and the target
	 */
	public int twoSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int left = 0, right = nums.length - 1;
		int min = Integer.MAX_VALUE;
		while (left < right) {
			if (nums[left] + nums[right] < target) {
				min = Math.min(min, (target - (nums[left] + nums[right])));
				left++;
			} else {
				min = Math.min(min, nums[left] + nums[right] - target);
				right--;
			}
		}

		return min;
	}

	// 611. Valid Triangle Number (Triangle Count)
	// Which should valid all the numbers could form how many triangles.
	// Company: Expedia
	public int triangleNumber(int[] nums) {
		Arrays.sort(nums);
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			int left = 0, right = i - 1;
			while (left < right) {
				if (nums[left] + nums[right] > nums[i]) {
					res += right - left;
					right--;
				} else {
					left++;
				}
			}
		}

		return res;
	}

	// 15. 3Sum find all nums which sums up to target.
	// Divide the problem into two sums with unique pairs.
	// for each nums[i] find all the other two which sums up to -nums[i];
	// meanwhile needs to skip the duplicate by checking adjust value.
	// Facebook, Amazon, etc.
	public List<List<Integer>> threeSum(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> res = new LinkedList<>();
		for (int i = 0; i < num.length - 2; i++) {
			// second condition is to skip the duplicate.
			if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
				int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
				while (lo < hi) {
					if (num[lo] + num[hi] == sum) {
						res.add(Arrays.asList(num[i], num[lo], num[hi]));
						// skip duplicate.
						while (lo < hi && num[lo] == num[lo + 1])
							lo++;
						while (lo < hi && num[hi] == num[hi - 1])
							hi--;
						lo++;
						hi--;
					} else if (num[lo] + num[hi] < sum) {
						lo++;
					} else {
						hi--;
					}
				}
			}
		}

		return res;
	}

	// LintCode 587. Two Sum Unique Pair. Find all the pairs that sums up to
	// specific number.
	// Find all the unique pairs and return the count.
	public int twoSum6(int[] nums, int target) {
		// Write your code here
		if (nums == null || nums.length < 2)
			return 0;

		Arrays.sort(nums);
		int cnt = 0;
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int v = nums[left] + nums[right];
			if (v == target) {
				cnt++;
				left++;
				right--;
				while (left < right && nums[right] == nums[right + 1])
					right--;
				while (left < right && nums[left] == nums[left - 1])
					left++;
			} else if (v > target) {
				right--;
			} else {
				left++;
			}
		}
		return cnt;
	}

	// 167. Two Sum II - Input array is sorted
	// Amazon
	public int[] twoSum2(int[] numbers, int target) {
		int start = 0;
		int end = numbers.length - 1;
		while (start <= end) {
			if (numbers[start] + numbers[end] < target) {
				start++;
			} else if (numbers[start] + numbers[end] > target) {
				end--;
			} else {
				break;
			}
		}

		return new int[] { start + 1, end + 1 };
	}
	// 170. Two Sum III - Data structure design
	// LinkedIn
	// Data Structure Design.

}
