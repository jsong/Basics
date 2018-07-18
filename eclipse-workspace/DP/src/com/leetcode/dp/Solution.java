package com.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// int[][] triangle = {{2}, {3 ,4}, {6, 5, 7}, {4, 1, 8, 3}};

		List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(2));
		List<Integer> l2 = new ArrayList<Integer>(Arrays.asList(3, 4));
		List<Integer> l3 = new ArrayList<Integer>(Arrays.asList(6, 5, 7));
		List<Integer> l4 = new ArrayList<Integer>(Arrays.asList(4, 1, 8, 3));

		List<List<Integer>> t = new ArrayList<>();
		t.add(l1);
		t.add(l2);
		t.add(l3);
		t.add(l4);

		Solution sl = new Solution();
		sl.minimumTotal(t);

		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		sl.maxSubArray(nums);
	}

	// 152. Maximum Product Subarray
	// Company: LinkedIn
	// Description:
	// Solution:
	public int maxProduct(int[] nums) {

	}

	// 53. Maximum Subarray
	// Company: Microsoft Bloomberg LinkedIn
	// Description: Given an interger nums, find the contiguous subarray which has
	// the largest sum and return its sum.
	// Solution: dp[i] = max(arr[i], dp[i - 1] + arr[i]);
	public int maxSubArray(int[] nums) {
		// int[] dp = new int[nums.length];
		// dp[0] = nums[0];
		int local_max = nums[0];
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			local_max = Math.max(nums[i], local_max + nums[i]);
			res = Math.max(res, local_max);
		}

		return res;
	}

	// 120. Triangle
	// Company: N/A
	// Description: Only able to move to ADJACENT numbers.
	// Solution:

	public int minimumTotal(List<List<Integer>> triangle) {
		// row col.
		int[][] sum = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
		sum[0][0] = triangle.get(0).get(0);
		for (int i = 1; i < triangle.size(); i++) {
			List<Integer> cols = triangle.get(i);
			for (int j = 0; j < cols.size(); j++) {
				if (j == 0) {
					sum[i][j] = sum[i - 1][j] + triangle.get(i).get(j);
				} else if (j == cols.size() - 1) {
					sum[i][j] = sum[i - 1][j - 1] + triangle.get(i).get(j);
				} else {
					sum[i][j] = Math.min(sum[i - 1][j - 1] + triangle.get(i).get(j),
							sum[i - 1][j] + triangle.get(i).get(j));
				}
			}
		}

		// iterate through the lower level.
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < sum[sum.length - 1].length; i++) {
			if (minSum > sum[sum.length - 1][i]) {
				minSum = Math.min(minSum, sum[sum.length - 1][i]);
			}
		}

		return minSum;
	}

	// NOTE: This is wrong, due to not satisfy the adjacent condition.
	public int minimumTotal2(List<List<Integer>> triangle) {
		// f[i, j] = min(f[])
		int sum = 0;
		for (int i = 0; i < triangle.size(); i++) {
			sum += findMin(triangle.get(i));
		}

		return sum;
	}

	private int findMin(List<Integer> level) {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < level.size(); i++) {
			min = Math.min(level.get(i), min);
		}

		return min;
	}

}
