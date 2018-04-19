package com.string.index;

public class Solution {
//28. Implement strStr()
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl = new Solution();
		sl.strStr2("hello", "la");
	}
	
	public int strStr(String haystack, String needle) {
		if(needle.equals("")) 
			return 0;
	    int L = needle.length();
	    for(int i=0; i<=haystack.length()-L; i++) 
	        if(haystack.substring(i,i+L).equals(needle)) 
	        	return i;
	    return -1;
    }
	
	public int strStr2(String haystack, String needle) {
		for (int i = 0; ; i++) {
		    for (int j = 0; ; j++) {
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
