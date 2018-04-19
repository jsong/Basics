package com.facebook.movezeroes;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double distDouble = 4.0;
		double rounded = Math.round(distDouble / 10);
		System.out.println(rounded);
		int[] nums = {0,1,0,3,12};
		Solution sl = new Solution();
		sl.moveZeroes2(nums);
		System.out.println("Finish calculating");
	}
	
	public void moveZeroes2(int[] nums) {
		/*NOTE: The below algorithm use two pointers which goes into each other.
		/which has fast move and write time. However it will make the array in non-sorted sequence.
		*/
		int left = 0, right = nums.length - 1;
		while (left < right) {
			if(nums[left] != 0) {
				left++;
			}
			if(nums[right] == 0) {
				right--;
			}
			if (nums[right] != 0 && nums[left] == 0) {
				int temp = nums[right];
				nums[right] = nums[left];
				nums[left] = temp;
				left++;
				right--;
			}
		}
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] == 0) {
				count = i;
				break;
			}
		}
		int[] nonZeroArray = Arrays.copyOf(nums, count );
		Arrays.sort(nonZeroArray);
		
		
		
		//NOTE: The below algorithm use the same direction, which basically the right pointer is not null, then swap it with the previous 0.
		//
//		int left = 0, right = 0;
//		while (right < nums.length) {
//			if (nums[right] != 0) {
//				int temp = nums[right];
//				nums[right] = nums[left];
//				nums[left] = temp;
//				left++;
//			}
//			right++;
//		}
		
	}
	
	
	public void moveZeroes(int[] nums) {
		int pivot = 0;
		if (nums.length == 0)
			return;
		for (int i = 0; i < nums.length; i++)
			if (nums[i] != 0)
				nums[pivot++] = nums[i];
		int zeros = nums.length - pivot;
		while (zeros > 0) {
			nums[pivot++] = 0;
			zeros--;
		}
	}
}
