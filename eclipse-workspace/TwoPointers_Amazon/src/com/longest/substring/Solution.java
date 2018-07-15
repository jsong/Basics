package com.longest.substring;

import java.util.HashSet;
import java.util.Set;
//#3. Longest Substring Without Repeating Characters
public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		String test = "abcbcamn";
		int length = sl.lengthOfLongestSubstring(test);
		System.out.println("Length:"+length);
	}
	
	public int lengthOfLongestSubstring(String s) {
	    int i = 0, j = 0, max = 0;
	    Set<Character> set = new HashSet<>();
	    
	    while (j < s.length()) {
	        if (!set.contains(s.charAt(j))) {
	            set.add(s.charAt(j++));
	            max = Math.max(max, set.size());
	            System.out.println("set add:"+set+"size:"+set.size());
	        } else {
	            set.remove(s.charAt(i++));
	            System.out.println("set remove:"+set+"size:"+set.size());
	        }
	    }
	    
	    return max;
	}
}
