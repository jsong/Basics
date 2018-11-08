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
}
