package com.google.findkcloset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[] {0,0,0,1,3,5,6,7,8,8};
		//[0,0,0,1,3,5,6,7,8,8]
//		2
//		2
		Solution sl = new Solution();
		List<Integer> re = sl.findClosestElements(arr, 2, 2);
		System.out.println(re.size());
		int[] array = new int[] {1,3,5};
		int index = sl.search(array, 0);
		 
	}
	
	
	//153. Find Minimum in Rotated Sorted Array
	//Microsoft
	//Find first element <= target
	public int findMin(int[] nums) {
		if (nums ==null || nums.length == 0) {
			return -1;
		}
		
		int start = 0; 
		int end = nums.length - 1;
		int target = nums[end];
		while (start + 1 < end) {
			int mid = start + (end - start)/2; 
			if (nums[mid] <= target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if(nums[start] <= target) {
			return nums[start];
		} else {
			return nums[end];
		}
		
    }
	//162. Find Peak Element Microsoft & Google. Please note the following solution could also be used
	//to this question. But note, index vs value.
	//585. Maximum Number in Mountain Sequence LintCode
	//Given a mountain sequence of n integers which increase first and then decrease, find the top.
	public int mountainSequence(int[] nums) {
		int start = 0, end = nums.length - 1;
//		int end = nums.length - 1;
		while (start + 1 < end) {
			int m1 = start + (end - start)/2;
			int m2 = end - (end - m1)/2;
			if (nums[m1] < nums[m2]) {
				start = m1 + 1;
			} else if (nums[m1] > nums[m2]) {
				end = m2 - 1;
			} else {
				start = m1;
				end = m2;
			}
		}
		
		return nums[start] > nums[end] ? nums[start]: nums[end];
	}
	
	
	
	//50. Pow(x,n)
	//n could be negative. Beware not use for ... n loop.
	//cause it will cause overflow. use divide and concur. 
	//Company Google Facebook.
	 public double myPow(double x, int n) {
	        long N = n;
	        if (N < 0) {
	            x = 1 / x;
	            N = -N;
	        }
	        double ans = 1, tmp = x;
	        while (N != 0) {
	            if (N % 2 == 1) {
	                ans = ans * tmp;
	            }
	            tmp *= tmp;
	            N = N/2;
	        }
	        /* O(n) will exceed time limit.
	        for (long i = 0; i < N; i++)
	            ans = ans * x;
	        */
	        return ans;
	    }
	
	//33. Search in Rotated Sorted Array
	//Search in a rotated array.
	 public int search(int[] nums, int target) {
		 if (nums ==null || nums.length == 0) {
				return -1;
			}
			
			int start = 0; 
			int end = nums.length - 1;
			while (start + 1 < end) {
				int mid = start + (end - start)/2; 
				if (target == nums[mid]) {
					return mid;
				} else if (nums[start] <= nums[mid]) {
					if (target < nums[mid] && target >= nums[start]) {
                     end = mid;
                 } else {
                     start = mid;
                 }
				} else if (nums[mid] <= nums[end]) {
                 if (target <= nums[end] && target > nums[mid]) {
                     start = mid;
                 } else {
                     end = mid;
                 }
				}
			}
			
			if (nums[start] == target) {
				return start;
			} else if (nums[end] == target) {
				return end;
			}
			
			return -1;
	 }
	
	//658. Find K Closest Elements
	//extend the space to mid + k and starts from there.
	//
	  public List<Integer> findClosestElements2(int[] arr, int k, int x) {
          int start = 0;
          int end = arr.length - k;
          List<Integer> res = new ArrayList<Integer>();
          while (start < end) {
              int mid = start + (end - start)/2;
              if (x > arr[mid]) {
                  if (x - arr[mid] > arr[mid + k] - x) {
                      start = mid + 1;
                  } else {
                      end = mid;
                  }
              } else {
                  end = mid;
              }
          }
          
          int index = start; 
          while (k != 0) {
              res.add(arr[index++]);
              k--;
          }
          return res;
      }
	
	//k is count, x is target.
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		List<Integer> list = new ArrayList<Integer>();
		//empty check? 
		int left = findLowerCloset(arr, x);
		int right = left + 1;
		int count = 0;
		int[] res = new int[k];
		
		while (count < k - 1) {
			if (isLeftCloser(arr, x, left, right)) {
				res[count++] = arr[left];
				left--;
			} else {
				res[count++] = arr[right];
				right++;
			}
		}
		
		Arrays.sort(res);
		
		for (int i: res) {
			list.add(i);
		}
		return list;
	}

	private boolean isLeftCloser(int[] arr, int target, int left, int right) {
		if (left < 0) {
			return false;
		}
		if (right >= arr.length) {
			return true;
		}
		if (target - arr[left] < arr[right] - target) {
			return true;
		}
		
		return true;
	}
	
	private int findLowerCloset(int[] arr, int target) {
		int start = 0; int end = arr.length - 1;
		//after the loop, start and end should be close to each other, then 
		//start from there.
		while (start + 1 < end) {
			int mid = start + (end - start)/2;
			if (arr[mid] == target) {
				end = mid;
			} else if (arr[mid] > target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		System.out.println("start:" + start + " end:" + end + " target:" + target);
		
		//find last position of target.
		if (arr[end] < target) {
			return end;
		}
		if (arr[start] < target) {
			return start;
		}
		
		return -1;
	}
	
}
