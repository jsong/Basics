package com.leetcode.search;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 35. Search Insert Position 
	// Description: Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order. 
	// Company: Google # Amazon
	// Solution: Binary Search find the middle element, check which range it belongs to.
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
        } else {  // in the middle
            return left + 1;
        }
    }

}
