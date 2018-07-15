package com.amazon.lexicalcombinationofstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {

	public static void main(String[] args) {
		String digits = "23";
		Solution sl = new Solution();

		List<String> ls = sl.letterCombinations2(digits);
		System.out.println(ls);
	}
	// 401. Binary Watch

	public List<String> readBinaryWatch(int num) {
		List<String> result = null;
		return result;
	}

	// 17. Letter Combinations of a Phone Number
	// Company: Google Facebook Amazon
	// Description: According to the T9 mapping, return all the possible
	// combinations of the letter.
	// eg. 2,3 = > ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	public List<String> letterCombinations2(String digits) {
		LinkedList<String> ans = new LinkedList<String>();
		if (digits.isEmpty())
			return ans;
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		ans.add("");
		for (int i = 0; i < digits.length(); i++) {
			int x = Character.getNumericValue(digits.charAt(i));
			System.out.println("x:" + x + "i:" + i);
			while (ans.peek().length() == i) {
				String t = ans.remove();

				for (char s : mapping[x].toCharArray()) {
					ans.add(t + s);
					System.out.println("ans:" + ans);
				}
			}
		}
		return ans;
	}
}
