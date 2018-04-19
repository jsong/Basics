package com.nocompany.insertindex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

//	35. Search Insert Position
// 	Find the first position of character which needs to be inserted or its position.
	
	public int searchInsert(int[] nums, int target) {
		
		return 0;
    }
	 /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0) 
            return -1;
        
        int start = 0; int end = nums.length - 1;
            
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target) {
                end = mid;
            } else if(nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] == target) {
            return start;
        }
        
        if (nums[end] == target) {
            return end;    
        }
        
        return -1;
    }
	
	//find first position of target, if not found return -1;
	 public int findPosition(int[] nums, int target) {
	        // write your code here
	        // first or last position of target.
	        int start = 0;
	        int end = nums.length - 1;
	        while (start + 1 < end) { // start < end will cause dead loop, since start always 4 and always < 5. The same as start is 0, end is 1.
	            int mid = start + (end - start)/2;
	            if (nums[mid] > target) {
	                end = mid;
	            } else if (nums[mid] < target) {
	                start = mid;
	            } else {
	                return mid;
	            }
	        }
	        
	        if (nums[start] == target) {
	            return start;
	        }
	        
	        if(nums[end] == target) {
	            return end;
	        }
	        
	        return -1;
	    }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{1,2,2,4,5,5};
		Solution sl = new Solution();
		int index = sl.findPosition(nums,6);
		System.out.println("first occurance of target:"+index);
	}
}
