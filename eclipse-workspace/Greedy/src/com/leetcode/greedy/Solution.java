package com.leetcode.greedy;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 55. Jump Game
	// Description: Given an array of steps, check whether you could reach the last index of element.
	// Company: Facebook Apple Amazon Microsoft # Uber
	// Solution: iterate through each element in array, record the furtherest it could reach.
	public boolean canJump(int[] nums) {
		int step = 1;

		for (int i = 0; i < step && step < nums.length; i++) {
			step = Math.max(step, nums[i] + i + 1);
			if (step >= nums.length) {
				return true;
			}
		}

		return step >= nums.length;
	}

	// 45. Jump Game II
	// Description: Given an array represent max steps, calculate the minimum steps needed to jump to last index.
	// Company: NetEase
	// Solution: Use last for last maxium steps been reached, if i exceeds then reapply the maxium steps will be reaching. Count ++;
	// TimeComplexity: O(n)
	public int jump(int[] nums) {
		// maximum steps already reached;
		int last = 0;
		// maxium steps will be reached;
		int cur = 0;
		int steps = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > last) {
				last = cur;
				steps++;
			}

			cur = Math.max(cur, nums[i] + i);
		}

		return steps;
	}

	// 121. Best Time to Buy and Sell Stock
	// Description:
	// Company:
	// Solution:
	public int maxProfit(int[] prices) {
		// int buy = 1;
		int max_profit = 0;
		for (int i = 0, int j= i + 1; i < prices.length, j < prices.length;) {
			if (prices[i] > prices[j]) {
				i++;
				j++;
			} else {
				max_profit = Math.max(max_profit, prices[j] - prices[i]);
				j++;
			}
		}

		return max_profit;
	}
}
