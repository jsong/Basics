package com.leetcode;

public class Solution {

	public static void main(String[] args) {
		int result = 5/2;
		System.out.println("result:"+result);
	}

	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		int result = 0;
		int counter = 1;
		for (int i = 0; i< flowerbed.length; i++) {
			if(flowerbed[i] == 0) {
				counter ++;
			} else {
				result += (counter - 1)/2;
				counter = 0;
			}
		}
		if(counter > 0) result += counter/2;
		return result > n;
	}

	// 7. Reverse Integer
	// Company: Apple NetEase Bloomberg
	// Description:
	// Solution:
	public int reverse(int x) {
		int res = 0;
		while (x != 0)
		{
			if (Math.abs(res) > Integer.MAX_VALUE / 10) return 0; // overflow
			res = res * 10 + x % 10;
			x /= 10;
		}

		return res;
  }

	// 9. Palindrome Number
	// Company:
	// Description:
	// Solution:
	public boolean isPalindrome(int x) {
		if (x < 0) return false;
		String s = Integer.toString(x);
		int i = 0;
		int j = s.length() - 1;
		while (i < j)
		{
						char h = s.charAt(i++);
						char t = s.charAt(j--);
			if (h != t)
			{
				return false;
			}
		}
		return true;
  }

	// 57. Insert Interval
	// Company: LinkedIn Facebook Google
	// Description:
	// Solution: Use cur indicator inside the intervals. Compare it against the newInterval.
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

  }

	// 56. Merge Interval
	// Company: LinkedIn Google Facebook Microsoft Bloomberg Yelp Twitter
	// Description: LinkedIn Google Facebook Microsoft Bloomberg Yelp Twitter
	// Solution:
	public List<Interval> merge(List<Interval> intervals) {

	}

	// 76. Minimum Window Substring
	// Company: LinkedIn Facebook Snapchat Uber
	// Description:
	// Solution:
	public String minWindow(String s, String t) {

  }

	// 43. Multiply Strings
	// Company: Twitter Facebook
	// Description:
	// Solution:
	public String multiply(String num1, String num2) {

	}

	// 30. Substring with Concatenation of All Words
	// Company:
	// Description:
	// Solution:
	public List<Integer> findSubstring(String s, String[] words) {

  }

	// 118. Pascal's Triangle
	// Company:
	// Description:
	// Solution: Draw a picture, with triangle.
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < numRows; i++)
		{
			List<Integer> row = new ArrayList<>();
			for (int j = 0; j <=i; j++)
			{
				if (j == 0 || j == i)
				{
					row.add(1);
				}
				else
				{
					row.add(res.get(i - 1).get(j) + res.get(i - 1).get(j - 1));
				}
			}

			res.add(row);
		}

		return res;
	}

	//
	//
	//
	//

}
