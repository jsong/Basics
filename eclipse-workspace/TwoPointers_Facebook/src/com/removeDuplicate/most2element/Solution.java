package com.removeDuplicate.most2element;

public class Solution {
//80. Remove Duplicates from Sorted Array II

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3,3};
		Solution sl = new Solution();
		int count = sl.removeDuplicates(nums);
		
		System.out.println("Finish");
	}

	public int removeDuplicates(int[] nums) {
	    int i = 0;
	    for (int n : nums) {
	    		System.out.println("counter:"+i+" n:"+n);
	    		if (i < 2 || n > nums[i-2])
	            nums[i++] = n;
	    }
	    return i;
	}
}
