package com.amazon.twosumindexs;

import java.util.HashSet;
import java.util.Set;

//167. Two Sum II - Input array is sorted
public class Solution {
	 	
	public int[] twoSum(int[] numbers, int target) {    
		int start = 0;
		int end = numbers.length - 1;
		while (start <=end ) {
			if(numbers[start] + numbers[end] <target ) {
				start ++;
			} else if (numbers[start] + numbers[end] > target) {
				end --;
			} else {
				break;
			}
		}
		//indices start 0 however the answer start from 1;
		return new int[] {start + 1, end + 1};
	}
	
	
	public static void main(String[] args) {
	}
}

