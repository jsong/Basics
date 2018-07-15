package com.string.index;

public class Solution {
	// 28. Implement strStr()
	public static void main(String[] args) {
		Solution sl = new Solution();
		sl.strStr3("mississippi", "issip");
	}

	public int strStr3(String source, String target) {
		if (source == null) {
			return -1;
		}
		if (target == null) {
			return -1;
		}

		if (target.length() > source.length()) {
			return -1;
		}

		if (target.length() == 0 && target.length() == source.length()) {
			return 0;
		}
		int j = 0;
		int i = 0;
		boolean stepback = false;
		int lastEqualCharIndex = 0;
		for (; i < source.length(); i++) {
			if (j == target.length()) {
				break;
			}
			System.out.println("i:" + source.charAt(i) + "j:" + target.charAt(j));
			if (source.charAt(i) == target.charAt(j)) {
				j++;
				stepback = true;
				lastEqualCharIndex = i;
				continue;
			} else {
				j = 0;
				if (stepback) {
					i = lastEqualCharIndex - 1;
				}
			}
		}

		if (j == target.length()) {
			return i - j;
		}

		return -1;
	}

	public int strStr(String haystack, String needle) {
		if (needle.equals(""))
			return 0;
		int L = needle.length();
		for (int i = 0; i <= haystack.length() - L; i++)
			if (haystack.substring(i, i + L).equals(needle))
				return i;
		return -1;
	}

	public int strStr2(String haystack, String needle) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				if (i + j == haystack.length())
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j))
					break;
			}
		}
	}
}
