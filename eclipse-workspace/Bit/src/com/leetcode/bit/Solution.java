package com.leetcode.bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		List<String> res = sl.findRepeatedDnaSequences("AAAAAAAAAAAAA");
		System.out.println("Repeat: " + res);
	}

	// 231. Power of Two
	// Company: Amazon
	// Description: Given an integer, write a function to determine if it is a power of two.
	// Solution: 1. % 2 until 0 or 1; 2. Highest bit should be 1, minus 1 other bits will be 1, bit& them to check whether it become 0.
	// 3. Count the number of 1s in n, cnt should be 1;
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
		List<String> arr = new ArrayList<String>();
		// HashSet<String> set = new HashSet<>();
		for (int i = 0; i < s.length() - 9; i++)
		{
			String key = s.substring(i, i + 10); // 10 bits in total.
			if (map.containsKey(key))
			{
				if (map.get(key) == 1)
				{
					arr.add(key);
				}
				map.put(key, map.get(key) + 1);
			}
			else
			{
				map.put(key, 1);
			}
		}

		return arr;
  }

	// 191. Number of 1 Bits
	// Company:
	// Description:
	// Solution:
	public int hammingWeight(int n) {
      int count = 0;
			// while (n != 0)
			for (int i = 0; i < 32; i++)
			{
				if ((n & 1) == 1)
				{
					count++;
				}
				n = n >>> 1;
			}

			return count;
  }

	// 89. Gray Code
	// Company: Amazon
	// Description:
	// Solution:
	public List<Integer> grayCode(int n) {
			int loop = (int)Math.pow(2, n);
			List<Integer> res  = new ArrayList<Integer>();
			for (int i = 0; i < loop; i++)
			{
				res.add(i ^ (i >> 1));
			}

			return res;
  }

	// 136. Single Number
	// Company: Airbnb Palantir
	// Description:
	// Solution: 0 XOR b equals to b, a XOR a = 0.
	public int singleNumber(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++)
		{
			res ^= nums[i];
		}

		return res;
	}

	// 137. Single Number II
	// Company:
	// Description:
	// Solution:
	public int singleNumber(int[] nums) {

  }

	// 268. Missing Number
	// Company: Facebook Bloomberg Microsoft
	// Description:
	// Solution: XOR on the missing number all paired nums will be 0 eventually.
	public int missingNumber(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++)
		{
			res ^= (i + 1) ^ nums[i];
		}

		return res;
	}

	// 318. Maximum Product of Word Lengths
	// Company: Google
	// Description:
	// Solution: mapping each word to mask[i], if mask[i] & mask[j] == 0
	// then update the res.
	public int maxProduct(String[] words) {
		int[] mask = new int[words.length];
		for (int i = 0; i < words.length; i++)
		{
			for (char c: words[i].toCharArray())
			{
				mask[i] |= 1 << (c - 'a');
			}

			for (int j = 0; j < i; j++)
			{
				if (mask[i] & mask[j] == 0)
				{
					res = Math.max(res, word[i].length() * word[j].length());
				}
			}
		}

		return res;
  }

	// 326. Power of Three
	// Company: Google
	// Description:
	// Solution: 1. Recursion
	public boolean isPowerOfThree(int n) {
		if (n == 0)
		{
			return false;
		}

		if (n == 1 || n == 3)
		{
			return true;
		}

		if (n % 3 == 0)
		{
			return isPowerOfThree(n / 3);
		}
		else
		{
			return false;
		}
  }

	// 201. Bitwise AND of Numbers Range
	// Company:
	// Description:
	// Solution: 1. Time limit exceeds. 2. Find the common parts between m and n.
	public int rangeBitwiseAnd(int m, int n) {
		int mask = Integer.MAX_VALUE;
		while ((m & mask) != (n & mask))
		{
			mask = mask << 1;
		}

		return m & mask;
  }

	public int rangeBitwiseAnd(int m, int n) {
		int res = m;
		for (int i = m + 1; i <=n; i++)
		{
			res &= i;
		}

		return res;
  }
}
