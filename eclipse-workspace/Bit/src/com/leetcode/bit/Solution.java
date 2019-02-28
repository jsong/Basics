package com.leetcode.bit;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 231. Power of Two
	// Company: Amazon
	// Description: Given an integer, write a function to determine if it is a power of two.
	// Solution: 1. % 2 until 0 or 1; 2. Highest bit should be 1, minus 1 other bits will be 1, bit& them to check whether it become 0.
	public boolean isPowerOfTwo(int n) {
		if (n == 2 || n == 1) {
				 return true;
		 }

		if (n % 2 == 0) {
		 	n = n / 2;
		 	return isPowerOfTwo(n);
	 	} else {
		 return false;
	 	}
	}

	public boolean isPowerOfTwo2(int n) {
		return n > 0 && ((n&(n - 1)) == 0);
	}

	// 190. Reverse Bits
	// Company:
	// Description:
	// Solution:
	// you need treat n as an unsigned value
  public int reverseBits(int n) {
    int res = 0;
		for (int i = 0; i < 32; ++i) {
			if ((n & 1) == 1) {
				res = (res << 1) + 1;
			} else {
				res = res << 1;
			}
			n = n >> 1;
		}

		return res;
  }

	// 187. Repeated DNA Sequences
	// Company: LinkedIn
	// Description:
	// Solution: TODO Memory profiling. 
	public List<String> findRepeatedDnaSequences(String s) {
		HashMap<String, Integer> map = new HashMap<>();
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < s.length() - 9; i++)
		{
			String key = s.substring(i, i + 10); // 10 bits in total.
			if (map.containsKey(key))
			{
				map.put(key, map.get(key) + 1);
				set.add(key);
			}
			else
			{
				map.put(key, 1);
			}
		}

		List<String> arr = new ArrayList<String>(set);

		return arr;
  }

}
